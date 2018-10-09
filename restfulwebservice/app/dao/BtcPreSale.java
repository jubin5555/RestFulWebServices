package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dao.DataBaseConnection.connect;

/***
 * This class is mainly used to check the btc before the sales.
 * This class is separate to keep the backend business and person account different.
 * **/
public class BtcPreSale {
    //the method is used by Market to check if the btc proposed by the UserActor is avaialble in the SellOffers
    public static boolean btcMarketConfirmation(String offerID,int amount) throws SQLException {

        Connection c =connect();
        String sql ="SELECT * from SellOffers WHERE OfferID=?";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,offerID);
            // set the corresponding param for the sql statements
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("Amount")<amount){return false;}
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            c.close();
        }
        return true;
    }


    //the method is used by the UserActor to check the btcs avaiable for the
    //given amount and the maxrate.
    //amount -the number of btc the user wants to buy
    //maxrate -the maximum a user is ready to pay for btc
    public static HashMap<String, List<Integer>> checkAvailableBtc(int amount,int maxrate ) throws SQLException {
        Connection c =connect();
        HashMap<String,List<Integer>> map =new HashMap<>();
        int count=amount;
        String sql ="SELECT * from SellOffers ORDER BY Rate ASC";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next() && count>0)
            {
               if( rs.getInt("rate")<=maxrate){
                   if((rs.getInt("amount")>=count &&rs.getInt("amount")!=0) && count !=0)
                   {
                       List<Integer> tempList = new ArrayList<>();
                       tempList.add(count);
                       tempList.add(rs.getInt("rate"));
                       map.put(rs.getString("offerID"),tempList);
                       return map;
                    }
                   else if((rs.getInt("amount")<count &&rs.getInt("amount")!=0) && count !=0){
                       List<Integer> tempList = new ArrayList<>();
                       tempList.add(rs.getInt("amount"));
                       tempList.add(rs.getInt("rate"));
                       map.put(rs.getString("offerID"),tempList);
                       count=count-rs.getInt("amount");
                   }
               }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            c.close();
        }
        if(count>0){return new HashMap<String,List<Integer>>();}
        return map;
    }
    //the method is used by the MarketActor to update the SellOffers table on purchase by a user.
    public static void updateBtcOnPurchase(int btc,String offerID,Connection c) throws SQLException {

        int initialBtcCount=0;
        String sql ="SELECT * from SellOffers WHERE OfferID=?";
        String sql2=" Update SellOffers SET amount=? " +
                "Where OfferID=?";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            PreparedStatement pstmt2 = c.prepareStatement(sql2);
            pstmt.setString(1,offerID);


            // set the corresponding param for the sql statements
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next() )
            {
                initialBtcCount=rs.getInt("amount");
            }
            int finalBtcCount =initialBtcCount-btc;
            pstmt2.setInt(1,finalBtcCount);
            pstmt2.setString(2,offerID);
            pstmt2.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }



}
