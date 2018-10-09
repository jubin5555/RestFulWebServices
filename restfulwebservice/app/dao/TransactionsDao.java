package dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.DataBaseConnection.connect;

/**
 * TransactionsDao is used to maintian all the ids related to the transactions
 * It has 3 values,transaction id-->a unique value for each successull transaction
 * rate-->in USD per BtcPeopleDao
 * amount-->in BtcPeopleDao
 * **/
public class TransactionsDao {
    public static JsonNode getAllTransactionID() throws SQLException {
        List<Integer> list = new ArrayList<Integer>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        Connection c =connect();
        String sql ="SELECT * from Transactions";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                list.add(rs.getInt("ID"));
            }
            objectNode1.put("list",list.toString());
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

    public static JsonNode  getTransactionDetail(int id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        Connection c =connect();
        String sql ="SELECT * from Transactions WHERE ID=?";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            pstmt.setInt(1,id);
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                objectNode1.put("rate",rs.getInt("rate"));
                objectNode1.put("amount",rs.getInt("amount"));

            }
            if(objectNode1.get("rate")==null || objectNode1.get("amount")==null){
                ObjectMapper mapper1 = new ObjectMapper();
                ObjectNode objectNode2 = mapper1.createObjectNode();
                objectNode2.put("status:","failure");
                objectNode2.put("message","The Transaction ID is not present ");
                return objectNode2;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            c.close();
        }
        return objectNode1;
    }

    public static void insertTransaction(int rate,int amount) throws SQLException {
        {
            Connection c =connect();
            String sql = "INSERT INTO Transactions "
                    + "(amount,rate) VALUES"
                    + "(?,?)";
            try
            {
                PreparedStatement pstmt = c.prepareStatement(sql);
                // set the corresponding param for the sql statements
                pstmt.setInt(1, amount);
                pstmt.setInt(2,rate);
                // execute the sql statement
                pstmt.executeUpdate();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally{
                c.close();
            }
        }
    }

    public static  int getLastTransactionId() throws SQLException {
        {
            Connection c =connect();
            String sql = " select ID from Transactions Order by ID Desc LIMIT 1";
            int transactionID=0;
            try
            {
                PreparedStatement pstmt = c.prepareStatement(sql);
                // execute the sql statement
               ResultSet rs =pstmt.executeQuery();
               while(rs.next())
               {
             transactionID=rs.getInt("ID");
               }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally{
                c.close();
            }
            return transactionID;
        }
    }
}
