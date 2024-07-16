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
import mainClasses.Client;

/**
 *
 * @author Nick
 */
public class EditClientTable {
           public void addClientFromJSON(String json) throws ClassNotFoundException{
         Client r=jsonToClient(json);
         createNewClient(r);
    }
    
    
    public Client databaseToClient(int Cid) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM client WHERE Cid = '" + Cid + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Client bt = gson.fromJson(json, Client.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public Client jsonToClient(String json) {
        Gson gson = new Gson();
        Client r = gson.fromJson(json, Client.class);
        return r;
    }
     
         
    public String ClientToJSON(Client r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Client.class);
        return json;
    }
    
    public void updateFurniture(int Cid, String col, String newval) throws SQLException, ClassNotFoundException {
    Connection con = DB_Connection.getConnection();
    String updateQuery = "UPDATE furniture SET " + col + " = ? WHERE Cid = ?";
    
    try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
        pstmt.setString(1, newval);
        pstmt.setInt(2, Cid);
        
        pstmt.executeUpdate();
    }
    
    con.close();
}

    public void deleteClient(int Cid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM client WHERE Cid='" + Cid + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createClientTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE client "
                    + "(Cid INTEGER not NULL unique, "
                    + " Fname VARCHAR(20) not NULL, "
                    + " Sname VARCHAR(20) not NULL, "
                    + " Username VARCHAR(20) not NULL, "
                    + " Password VARCHAR(20) not NULL, "
                    + " Phone VARCHAR(10) not NULL, "
                    + " Day INTEGER not NULL, "
                    + " Month INTEGER not NULL, "
                    + " Year INTEGER not NULL, "
                    + " PRIMARY KEY (Cid))";
            stmt.execute(sql);
            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(EditClientTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewClient(Client plateno) throws ClassNotFoundException {
        try {

            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " client (Cid ,Fname,Sname,Username,Password,Phone,Day,Month,Year)"
                    + " VALUES ("
                    + "'" + plateno.getCID() + "',"
                    + "'" + plateno.getFNAME() + "',"
                    + "'" + plateno.getSNAME() + "',"
                    + "'" + plateno.getUSERNAME() + "',"
                    + "'" + plateno.getPASSWORD() + "',"
                    + "'" + plateno.getPHONE() + "',"
                    + "'" + plateno.getDAY() + "',"
                    + "'" + plateno.getMONTH() + "',"
                    + "'" + plateno.getYEAR() + "'"
                    + ")";

            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The client was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();


        } catch (SQLException ex) {
            Logger.getLogger(EditClientTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
