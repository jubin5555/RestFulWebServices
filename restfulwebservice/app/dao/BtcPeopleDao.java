package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.DataBaseConnection.connect;

/**
 * The class is mainly used for the activities related to the BtcPeopleDao operations
 * **/
public  class BtcPeopleDao {
    //the method is used to add the purchased BtcPeopleDao count to the user account
    public static void addBTCToAccount(int currentBtcPurchase) throws SQLException {
        Connection c =connect();
        String sql ="SELECT * from people WHERE "
                +" people.PeopleID=? ";
        String sql2=" Update People SET btc=? " +
                "Where PeopleID=?";
        try
        {
            int currentBtcCount=0;
            PreparedStatement pstmt = c.prepareStatement(sql);
            PreparedStatement pstmt2 = c.prepareStatement(sql2);
            // set the corresponding param for the sql statements
            pstmt.setInt(1, 1);
            // execute the sql statement
            ResultSet rs= pstmt.executeQuery();
            while(rs.next()) {
                currentBtcCount = rs.getInt("btc");

            }
            int totalBalance =currentBtcPurchase +currentBtcCount;
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

    }


}

