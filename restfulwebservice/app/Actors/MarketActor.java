package Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.BtcPreSale;
import dao.SellOffersDao;
import dao.TransactionsDao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static dao.DataBaseConnection.connect;
public class MarketActor extends AbstractActor {

    final ActorSystem system = ActorSystem.create("MySystem");
    final ActorRef UserActor =system.actorOf(Props.create(UserActor.class), "myactor");

    public static Props getProps() {
        return Props.create(MarketActor.class);
    }

    public static Boolean CONFIRM_NO_RESPONSE=false;
    public static Boolean CONFIRM_FAIL=false;
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MarketActor.SellOffers.class, transactions -> {

                    sender().tell(transactions.SellOfferList, self());

                }).match(MarketActor.SellOffersById.class, SellOfferDetailByID
                        -> {
                    sender().tell(SellOfferDetailByID.SellOfferDetailJson, self());
                }).match(MarketActor.checkHoldStatus.class, status -> {

                    sender().tell(status.c,self());
                })
                .match(MarketActor.checkConfirmation.class, statusConfirmation -> {
                    if(Boolean.FALSE.equals(CONFIRM_NO_RESPONSE)) {
                        sender().tell(statusConfirmation.confirm, self());
                    }
                    else{
                        statusConfirmation.c.rollback();
                        statusConfirmation.c.setAutoCommit(true);
                        statusConfirmation.c.close();
                    }
                })
                .match(MarketActor.GetAllTransactions.class, transactionsList -> {
                    sender().tell(transactionsList.Transactions, self());
                })
                .match(MarketActor.ConfirmFail.class, debug -> {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode objectNode1 = mapper.createObjectNode();
                    objectNode1.put("status:","success");
                    sender().tell(objectNode1, self());
                })
                .match(MarketActor.ConfirmNoResponse.class, transactionById -> {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode objectNode1 = mapper.createObjectNode();
                    objectNode1.put("status:","success");
                    sender().tell(objectNode1, self());
                })
                .match(MarketActor.ConfirmReset.class, transactionById -> {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode objectNode1 = mapper.createObjectNode();
                    objectNode1.put("status:","success");
                    sender().tell(objectNode1, self());
                })
                .match(MarketActor.GetDetailOfTransaction.class, transactionById -> {
                    sender().tell(transactionById.TransactionDetail, self());
                })

                .build()
                ;
    }

    static public class SellOffers
    {
        public JsonNode SellOfferList;

        public SellOffers() throws IOException, SQLException {
           this.SellOfferList = SellOffersDao.getAvailableSellOffers();
        }
        }

    static public class SellOffersById{

        public JsonNode SellOfferDetailJson;
        public SellOffersById(String inputID) throws IOException, SQLException {
            SellOfferDetailJson=SellOffersDao.getSellOfferById(inputID);
    }}

    static public class checkHoldStatus{
        Boolean holdConfirmation;
        Connection c ;
        public checkHoldStatus(HashMap<String, List<Integer>> map) throws SQLException {
            //the tempBoolean is used to check if all the hold requests are true
            Boolean tempBoolean =true;
            c=connect();
            this.c=c;
            c.setAutoCommit(false);
            Iterator it = map.entrySet().iterator();
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            int transactionID =dao.TransactionsDao.getLastTransactionId() +1;
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String tempOfferID = pair.getKey().toString();
                List<Integer> tempList = (List<Integer>) pair.getValue();
                Boolean x= BtcPreSale.btcMarketConfirmation(tempOfferID, tempList.get(0));

                tempBoolean =tempBoolean && x;
                if(Boolean.TRUE.equals(tempBoolean))
                {
                    dao.LOG.insertLog("Hold request is accepted at "+timeStamp+" for Transaction ID: "+transactionID );
                    BtcPreSale.updateBtcOnPurchase(tempList.get(0),tempOfferID,c);
                }
                else{
                    dao.LOG.insertLog("Hold request is not accepted at "+timeStamp+" for Transaction ID: "+transactionID );
                    c.rollback();
                    c.setAutoCommit(true);
                    c.close();
                }
            }
            this.holdConfirmation=tempBoolean;
        }

    }
    static public class checkConfirmation{
        Boolean confirm=false;
        Connection c;

        public checkConfirmation(HashMap<String,List<Integer>> map,Connection c ) throws SQLException {
            this.c = c;
            if(Boolean.FALSE.equals(CONFIRM_NO_RESPONSE)) {
                if (Boolean.FALSE.equals(CONFIRM_FAIL)) {
                    int transactionID =dao.TransactionsDao.getLastTransactionId() +1;
                    this.confirm = true;
                    c.commit();
                    c.setAutoCommit(true);
                    c.close();
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        String tempOfferID = pair.getKey().toString();
                        List<Integer> tempList = (List<Integer>) pair.getValue();
                        TransactionsDao.insertTransaction(tempList.get(1), tempList.get(0));
                        String timeStamp = new SimpleDateFormat(
                                "yyyy/MM/dd HH:mm:ss").format(Calendar
                                .getInstance().getTime());
                        dao.LOG.insertLog("A confirm has been " +
                                " received by the marketActor at "+timeStamp
                                +"for Transaction ID: "+transactionID
                        );
                    }
                } else {
                    c.rollback();
                    c.setAutoCommit(true);
                    c.close();
                    this.confirm = false;
                }

            }

        }
    }
    static public  class GetAllTransactions{
        public JsonNode Transactions;
        public   GetAllTransactions() throws SQLException {
            Transactions = TransactionsDao.getAllTransactionID();
        }
    }
    static public class GetDetailOfTransaction{
        public JsonNode TransactionDetail;
        public GetDetailOfTransaction(int id) throws SQLException{
            TransactionDetail=TransactionsDao.getTransactionDetail(id);
        }
    }
    static public class ConfirmFail{
        public ConfirmFail( ) throws SQLException{
           CONFIRM_FAIL=true;
        }
    }
    static public class ConfirmNoResponse{
        public ConfirmNoResponse() throws SQLException{
            CONFIRM_NO_RESPONSE=true;
        }
    }
    static public class ConfirmReset{
        public ConfirmReset() throws SQLException{
            CONFIRM_FAIL=false;
            CONFIRM_NO_RESPONSE=false;
        }
    }
    }





