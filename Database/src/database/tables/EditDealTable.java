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
import mainClasses.Deal;

/**
 *
 * @author Nick
 */
public class EditDealTable {
        public void addDealFromJSON(String json) throws ClassNotFoundException{
         Deal r=jsonToDeal(json);
         createNewDeal(r);
    }
    
    
    public Deal databaseToDeal(int vid) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM deal WHERE vid = '" + vid + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Deal bt = gson.fromJson(json, Deal.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
     public Deal jsonToDeal(String json) {
        Gson gson = new Gson();
        Deal r = gson.fromJson(json, Deal.class);
        return r;
    }
     
         
      public String DealToJSON(Deal r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Deal.class);
        return json;
    }
      
    public void updateFurniture(int Did, String col, String newval) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        String updateQuery = "UPDATE furniture SET " + col + " = ? WHERE Did = ?";

        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
            pstmt.setString(1, newval);
            pstmt.setInt(2, Did);

            pstmt.executeUpdate();
    }
    
    con.close();
}

    public void deleteDeal(int vid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM deal WHERE vid='" + vid + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createDealTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE deal "
                    + "(did INTEGER not NULL unique , "
                    + " price INTEGER not NULL, "
                    + " PRIMARY KEY (did))";
            stmt.execute(sql);
            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(EditDealTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewDeal(Deal deal) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " deal (did ,price)"
                    + " VALUES ("
                    + "'" + deal.getDID() + "',"
                    + "'" + deal.getPRICE() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The deal was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditDealTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
