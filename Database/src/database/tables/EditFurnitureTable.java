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
import mainClasses.Furniture;

/**
 *
 * @author Nick
 */
public class EditFurnitureTable {
    public void addFurnitureFromJSON(String json) throws ClassNotFoundException{
         Furniture r=jsonToFurniture(json);
         createNewFurniture(r);
    }
    
    
    public Furniture databaseToFurniture(int Fid) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM furniture WHERE Fid = '" + Fid + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Furniture bt = gson.fromJson(json, Furniture.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public Furniture jsonToFurniture(String json) {
        Gson gson = new Gson();
        Furniture r = gson.fromJson(json, Furniture.class);
        return r;
    }
     
         
    public String FurnitureToJSON(Furniture r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Furniture.class);
        return json;
    }
    
public void updateFurniture(int Fid, String col, String newval) throws SQLException, ClassNotFoundException {
    Connection con = DB_Connection.getConnection();
    String updateQuery = "UPDATE furniture SET " + col + " = ? WHERE Fid = ?";
    
    try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
        pstmt.setString(1, newval);
        pstmt.setInt(2, Fid);
        
        pstmt.executeUpdate();
    }
    
    con.close();
}

    public void deleteFurniture(int Fid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM furniture WHERE Fid='" + Fid + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createFurnitureTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE furniture "
                    + "(Fid INTEGER not NULL unique, "
                    + " Name VARCHAR(20) not NULL, "
                    + " Type VARCHAR(20) not NULL, "
                    + " Sett VARCHAR(20) not NULL, "
                    + " Material VARCHAR(20) not NULL, "
                    + " Assemblage Integer not NULL, "
                    + " Color VARCHAR(20) not NULL, "
                    + " Description VARCHAR(200) not NULL, "
                    + " Width INTEGER not NULL, "
                    + " Depth INTEGER not NULL, "
                    + " Height INTEGER not NULL, "
                    + " Weight INTEGER not NULL, "
                    + " Room VARCHAR(20) not NULL, "
                    + " Price INTEGER not NULL, "
                    + " Did INTEGER not NULL, "
                    + " PRIMARY KEY (Fid))";
            stmt.execute(sql);
            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(EditFurnitureTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewFurniture(Furniture plateno) throws ClassNotFoundException {
        try {

            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " furniture (Fid ,Name,Type,Sett,Material,Assemblage,Color,Description,Width,Depth,Height,Weight,Room,Price,Did)"
                    + " VALUES ("
                    + "'" + plateno.getFID() + "',"
                    + "'" + plateno.getNAME() + "',"
                    + "'" + plateno.getTYPE() + "',"
                    + "'" + plateno.getSET() + "',"
                    + "'" + plateno.getMATERIAL() + "',"
                    + "'" + plateno.getASSEMBLAGE() + "',"
                    + "'" + plateno.getCOLOR() + "',"
                    + "'" + plateno.getDESCRIPTION() + "',"
                    + "'" + plateno.getWIDTH() + "',"
                    + "'" + plateno.getDEPTH() + "',"
                    + "'" + plateno.getHEIGHT() + "',"
                    + "'" + plateno.getWEIGHT() + "',"
                    + "'" + plateno.getROOM() + "',"
                    + "'" + plateno.getPRICE() + "',"
                    + "'" + plateno.getDID() + "'"
                    + ")";

            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The furniture was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();


        } catch (SQLException ex) {
            Logger.getLogger(EditFurnitureTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
