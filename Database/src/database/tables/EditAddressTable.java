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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Address;

/**
 *
 * @author Nick
 */
public class EditAddressTable {
    
    public void addAddressFromJSON(String json) throws ClassNotFoundException {
        Address r = jsonToAddress(json);
        createNewAddress(r);
    }

    public Address databaseToAddress(int Aid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM address WHERE Aid = '" + Aid + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Address bt = gson.fromJson(json, Address.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Address jsonToAddress(String json) {
        Gson gson = new Gson();
        Address r = gson.fromJson(json, Address.class);
        return r;
    }

    public String AddressToJSON(Address r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Address.class);
        return json;
    }
    
    public void updateFurniture(int Aid, String col, String newval) throws SQLException, ClassNotFoundException {
    Connection con = DB_Connection.getConnection();
    String updateQuery = "UPDATE furniture SET " + col + " = ? WHERE Aid = ?";
    
    try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
        pstmt.setString(1, newval);
        pstmt.setInt(2, Aid);
        
        pstmt.executeUpdate();
    }
    
    con.close();
}

    public void deleteAddress(int Aid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM address WHERE Aid='" + Aid + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }



    public void createAddressTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE address "
                    + "(Aid INTEGER not NULL , "
                    + " Country VARCHAR(20) not NULL, "
                    + " Town VARCHAR(20) not NULL, "
                    + " Street VARCHAR(20) not NULL, "
                    + " Number INTEGER not NULL, "
                    + " Postal INTEGER not NULL, "
                    + " Cid INTEGER not NULL, "
                    + " PRIMARY KEY (Aid,Cid))";
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditAddressTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewAddress(Address address) throws ClassNotFoundException {
        try {

            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " address (Aid ,Country,Town,Street,Number,Postal,Cid)"
                    + " VALUES ("
                    + "'" + address.getAID() + "',"
                    + "'" + address.getCOUNTRY() + "',"
                    + "'" + address.getTOWN() + "',"
                    + "'" + address.getSTREET() + "',"
                    + "'" + address.getNUMBER() + "',"
                    + "'" + address.getPOSTAL() + "',"
                    + "'" + address.getCID() + "'"
                    + ")";

            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The Address was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditAddressTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
