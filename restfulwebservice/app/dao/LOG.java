package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dao.LogDataBaseConnection.connect;


public class LOG {
    public static  void insertLog(String log) throws SQLException {
        Connection c =connect();
        String sql = "INSERT INTO LOG "
                + "(logs) VALUES"
                + "(?)";
        try
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            pstmt.setString(1, log);
            // execute the sql statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {

        }
        finally{
            c.close();
        }
    }
    }

