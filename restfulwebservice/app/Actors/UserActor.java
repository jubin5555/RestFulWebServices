package Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.BalanceDao;
import dao.BtcPreSale;
import scala.compat.java8.FutureConverters;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;



import static akka.pattern.Patterns.ask;

public class UserActor extends AbstractActor {
    //a global variable to store the users intitial value
    //the first value is the balance value & second is the BtcPeopleDao.


    final ActorSystem system = ActorSystem.create("MySystem");
    final ActorRef MarketActor = system.actorOf(Props.create(MarketActor.class), "myactor");

    public static Props getProps() {
        return Props.create(UserActor.class);
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CheckBalance.class, balance -> {
                    sender().tell(balance.amount, getSelf());
                })
                .match(SetBalance.class, balance -> {
                    sender().tell(balance.balanceSucessJson, getSelf());
                })
                .match(buy.class, purchase -> {
                    HashMap<String, List<Integer>> map = purchase.purchaseList;
                    if (map.size() == 0) {
                        ObjectMapper mapper = new ObjectMapper();
                        ObjectNode objectNode1 = mapper.createObjectNode();
                        objectNode1.put("status:", "failure");
                        objectNode1.put("error", "there arent any available offers.");
                        sender().tell(objectNode1, self());
                        return;
                    }
                    if (Boolean.FALSE.equals(BalanceDao.checkUserBalance(map))) {
                        ObjectMapper mapper = new ObjectMapper();
                        ObjectNode objectNode1 = mapper.createObjectNode();
                        objectNode1.put("status:", "failure");
                        objectNode1.put("error", "the user doesnt have enough balance");
                        sender().tell(objectNode1, self());
                        return;
                    }
                    ActorRef tempSender = sender();
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    FutureConverters.toJava(ask(MarketActor, new MarketActor.checkHoldStatus(map), 8000))
                            .thenApply(response -> { Connection c = (Connection) response;
                                    if (c != null) { try { FutureConverters.toJava(ask(MarketActor,
                                            new MarketActor.checkConfirmation(map, c), 5000)).thenApply(
                                                    response2 -> { if (Boolean.TRUE.equals(response2)) {
                                                        //only if confirm is true the database changes value// in the users account
                                                        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar
                                                                        .getInstance().getTime());
                                                        int transactionID = 0;
                                                        try { transactionID = dao.TransactionsDao.getLastTransactionId() + 1;
                                                        } catch (SQLException e) { e.printStackTrace(); }
                                                        try { dao.LOG.insertLog("A success has been " +
                                                                        "received by the userActor at " + timeStamp
                                                                        + "for transactionID: " + transactionID);
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                        }
                                                        ObjectMapper mapper = new ObjectMapper();
                                                        ObjectNode objectNode1 = mapper.createObjectNode();
                                                        objectNode1.put("status:", "success");
                                                        objectNode1.put("Transaction id", transactionID);
                                                        try {
                                                            BalanceDao.updateUserAccount(map);
                                                        } catch (Exception e) {
                                                                }tempSender.tell(objectNode1, getSelf());

                                                    } else {
                                                        String timeStamp = new SimpleDateFormat(
                                                                "yyyy/MM/dd HH:mm:ss").format(Calendar
                                                                        .getInstance().getTime());
                                                        int transactionID = 0;
                                                        try {
                                                            transactionID = dao.TransactionsDao.getLastTransactionId() + 1;
                                                                } catch (SQLException e) {
                                                            e.printStackTrace();
                                                        }
                                                        try {
                                                            dao.LOG.insertLog("A failure has been " +
                                                                            "received by the userActor at " + timeStamp
                                                                            + "for transactionID: " + transactionID);
                                                        } catch (SQLException e) {
                                                                    e.printStackTrace();
                                                        }
                                                        ObjectMapper mapper = new ObjectMapper();
                                                        ObjectNode objectNode1 = mapper.createObjectNode();
                                                        objectNode1.put("status:", "failure");
                                                        objectNode1.put("error", "error in " +
                                                                        "transaction,please try again");
                                                                tempSender.tell(objectNode1, getSelf());
                                                            }return sb; });
                                    }
                                    catch (Exception e) {
                                    } } else {
                                            tempSender.tell("failure due to Hold Timeout", getSelf());
                                        }return sb2;
                            }); }).build(); }



    static public class CheckBalance {
        JsonNode amount;
        public CheckBalance() throws SQLException {
            this.amount = BalanceDao.getBalance();
        }
    }

    public static class SetBalance {
        JsonNode balanceSucessJson;
        public SetBalance(int amount) throws IOException, SQLException
        {
            this.balanceSucessJson = BalanceDao.setBalance(amount);
        }
    }

    static public class buy {
        HashMap<String, List<Integer>> purchaseList;
        public buy(String amount, String maxRate) throws SQLException
        {
            this.purchaseList = BtcPreSale.checkAvailableBtc(Integer.parseInt(amount), Integer.parseInt(maxRate));
        }
    }
}

