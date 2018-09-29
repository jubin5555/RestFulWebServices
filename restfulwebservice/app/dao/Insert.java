package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import static dao.DataBaseConnection.connect;


public class Insert{
    public static void updateTable ( ) throws SQLException

    {
        Connection c =connect();
        String sql = "INSERT INTO name"

                + "(n) VALUES"

                + "(?)";

        try

        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            // set the corresponding param for the sql statements
            pstmt.setString(1, "Jubin");
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
    public static void main(String[] args) throws SQLException {
    updateTable();
    }









}
