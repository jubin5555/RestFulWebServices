package dao;

import Actors.MarketActor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dao.DataBaseConnection.connect;

/**This class is used for the Selloffers database Operations.
 * **/

public  class SellOffersDao {
    public static JsonNode getAvailableSellOffers() throws SQLException {
        List<String> list = new ArrayList<String>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        Connection c =connect();
        String sql ="SELECT * from SellOffers";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("offerID"));

            }
            objectNode1.put("status","success");
            objectNode1.put("offers:",list.toString());

        } catch (SQLException e)
        {
            e.printStackTrace();

        }
        finally{
            c.close();

        }
        return objectNode1;
    }



   public static JsonNode getSellOfferById(String offerID) throws SQLException {
       ObjectMapper mapper = new ObjectMapper();
       ObjectNode objectNode1 = mapper.createObjectNode();
       Connection c =connect();
       String sql ="SELECT * from SellOffers WHERE offerID=?";
       try
       {

           PreparedStatement pstmt = c.prepareStatement(sql);
           // set the corresponding param for the sql statements
           pstmt.setString(1,offerID);
           // execute the sql statement
           ResultSet rs= pstmt.executeQuery();
           while(rs.next())
           {
            objectNode1.put("rate",rs.getInt("Rate"));
            objectNode1.put("amount",rs.getInt("Amount"));
           }
          if(objectNode1.get("rate")==null || objectNode1.get("amount")==null){
              ObjectMapper mapper1 = new ObjectMapper();
              ObjectNode objectNode2 = mapper1.createObjectNode();
              objectNode2.put("status:","failure");
              objectNode2.put("message","The SellOffer ID is not present ");
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



}
