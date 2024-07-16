/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Order;

/**
 *
 * @author Nick
 */
public class EditOrderTable {
        public void addOrderFromJSON(String json) throws ClassNotFoundException{
         Order r=jsonToOrder(json);
         createNewOrder(r);
    }
    
    
    public Order databaseToOrder(int oid) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM orders WHERE oid = '" + oid + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Order bt = gson.fromJson(json, Order.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public Order jsonToOrder(String json) {
        Gson gson = new Gson();
        Order r = gson.fromJson(json, Order.class);
        return r;
    }
     
         
    public String OrderToJSON(Order r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Order.class);
        return json;
    }
    
    public void updateFurniture(int Oid, String col, String newval) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        String updateQuery = "UPDATE furniture SET " + col + " = ? WHERE Oid = ?";

        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
            pstmt.setString(1, newval);
            pstmt.setInt(2, Oid);

            pstmt.executeUpdate();
        }
    
    con.close();
}

    public void deleteOrder(int oid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM orders WHERE oid='" + oid + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createOrderTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE orders "
                    + "(Oid INTEGER not NULL unique, "
                    + " Price INTEGER not NULL, "
                    + " Transport INTEGER not NULL, "
                    + " State VARCHAR(20) not NULL, "
                    + " Num_of_prod INTEGER not NULL, "
                    + " Day INTEGER not NULL, "
                    + " Month INTEGER not NULL, "
                    + " Year INTEGER not NULL, "
                    + " Cid INTEGER not NULL, "
                    + " PRIMARY KEY (Oid,Cid))";
            stmt.execute(sql);
            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(EditOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewOrder(Order plateno) throws ClassNotFoundException {
        try {

            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

           String insertQuery = "INSERT INTO "
                    + " orders (Oid ,Price,Transport,State,Num_of_prod,Day,Month,Year,Cid)"
                    + " VALUES ("
                    + "'" + plateno.getOID() + "',"
                    + "'" + plateno.getPRICE() + "',"
                    + "'" + plateno.getTRASPORT() + "',"
                    + "'" + plateno.getSTATE() + "',"
                    + "'" + plateno.getNUM_OF_PROD() + "',"
                    + "'" + plateno.getDAY() + "',"
                    + "'" + plateno.getMONTH() + "',"
                    + "'" + plateno.getYEAR() + "',"
                    + "'" + plateno.getCID() + "'"
                    + ")";
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The order was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();


        } catch (SQLException ex) {
            Logger.getLogger(EditOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
