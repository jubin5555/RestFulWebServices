package dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static dao.DataBaseConnection.connect;
/**
 * This class is used to check the balance and get the balance of each user using database operations.
 * **/
public class BalanceDao {
public static JsonNode getBalance() throws SQLException {
    {
        int balance=0;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        Connection c =connect();
        String sql ="SELECT * from people WHERE "

                +" people.PeopleID=? ";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            pstmt.setInt(1, 1);
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                objectNode1.put("balance in USD",rs.getInt("balance"));
                objectNode1.put("balance in Btc",rs.getInt("btc"));

            }
            objectNode1.put("status","success");

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            c.close();
        }
        return objectNode1;
    }
}
/**The setBalance can be used to add as well as reduce the user balance*/
public static JsonNode setBalance(int amount) throws SQLException {
    {
        Connection c =connect();
        String sql ="SELECT * from people WHERE "
                +" people.PeopleID=? ";
        String sql2=" Update People SET balance=? " +
                "Where PeopleID=?";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();

        try
        {
            int balance=0;
            PreparedStatement pstmt = c.prepareStatement(sql);
            PreparedStatement pstmt2 = c.prepareStatement(sql2);
            // set the corresponding param for the sql statements
            pstmt.setInt(1, 1);
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                balance=rs.getInt("balance");

            }
            int totalBalance =amount+balance;
            pstmt2.setInt(1,totalBalance);
            pstmt2.setInt(2,1);
            pstmt2.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            c.close();
        }
        return objectNode1.put("status","success");
    }
}
/*
* The input hashmap has a string and list,the string is used to show the offerID
* The List has 2 values,the first value is the BTC purchased and the second is the
* cost per BTC.
* */
public static void updateUserAccount(HashMap<String,List<Integer>> map) throws SQLException {
    int btcCount=0;
    int calculatedExpense=0;
    Iterator it = map.entrySet().iterator();
    while(it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        List<Integer> tempList = (List<Integer>) pair.getValue();
        btcCount=btcCount+tempList.get(0);
        calculatedExpense=calculatedExpense+tempList.get(0)*tempList.get(1);
}
    Connection c =connect();
    String sql ="SELECT * from people WHERE "
            +" PeopleID=? ";
    String sql2=" Update People SET balance=? , btc=? " +
            "Where PeopleID=?";
    try
    {
        int balance=0;
        int currentBTCCount=0;
        PreparedStatement pstmt = c.prepareStatement(sql);
        PreparedStatement pstmt2 = c.prepareStatement(sql2);
        // set the corresponding param for the sql statements
        pstmt.setInt(1, 1);
        // execute the sql statement
        ResultSet rs= pstmt.executeQuery();
        while(rs.next())
        {
            balance=rs.getInt("balance");
            currentBTCCount=rs.getInt("btc");

        }
        int totalBalance =balance-calculatedExpense;
        int totalBtc =currentBTCCount+btcCount;
        pstmt2.setInt(1,totalBalance);
        pstmt2.setInt(2,totalBtc);
        pstmt2.setInt(3,1);
        pstmt2.executeUpdate();

    } catch (SQLException e)
    {
        e.printStackTrace();
    }
    finally{
        c.close();

    }
}
//the method is used to check if the user has enough balance in his account,if not send a false
public static Boolean checkUserBalance(HashMap<String,List<Integer>> map) throws SQLException {
    int calculatedExpense=0;
    int userCurrentBalance=0;
    Iterator it = map.entrySet().iterator();
    while(it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        List<Integer> tempList = (List<Integer>) pair.getValue();
        calculatedExpense=calculatedExpense+tempList.get(0)*tempList.get(1);
    }
    Connection c =connect();
    String sql ="SELECT * from people WHERE "
            +" PeopleID=? ";


    try
    {
        PreparedStatement pstmt = c.prepareStatement(sql);
                // set the corresponding param for the sql statements
        pstmt.setInt(1, 1);
        // execute the sql statement
        ResultSet rs= pstmt.executeQuery();
        while(rs.next())
        {
            userCurrentBalance=rs.getInt("balance");
        }
      if(calculatedExpense>userCurrentBalance){return false;}
    } catch (SQLException e)
    {
        e.printStackTrace();
    }
    finally{
        c.close();

    }

return true;

}
}
