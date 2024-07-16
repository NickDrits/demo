/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import mainClasses.Ordered_Products;

/**
 *
 * @author Nick
 */
public class EditOrdered_ProductsTable {
       public void addOrdered_ProductsFromJSON(String json) throws ClassNotFoundException{
         Ordered_Products r=jsonToOrdered_Products(json);
         createNewOrdered_Products(r);
    }
    
    
    public Ordered_Products databaseToOrdered_Products(int oid) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM ordered WHERE oid = '" + oid + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Ordered_Products bt = gson.fromJson(json, Ordered_Products.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public Ordered_Products jsonToOrdered_Products(String json) {
        Gson gson = new Gson();
        Ordered_Products r = gson.fromJson(json, Ordered_Products.class);
        return r;
    }
     
         
    public String Ordered_ProductsToJSON(Ordered_Products r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Ordered_Products.class);
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

    public void deleteOrdered_Products(int oid) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM ordered WHERE oid='" + oid + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createOrdered_ProductsTable() throws SQLException, ClassNotFoundException {
        try (Connection con = DB_Connection.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE ordered "
                    + "(Oid INTEGER not NULL unique, "
                    + " Ordered INTEGER not NULL, "
                    + " PRIMARY KEY (oid))";
            stmt.execute(sql);
            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(EditOrdered_ProductsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewOrdered_Products(Ordered_Products ordered) throws ClassNotFoundException {
        try {

            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " ordered (Oid ,Ordered)"
                    + " VALUES ("
                    + "'" + ordered.getOID() + "',"
                    + "'" + ordered.getORDERED() + "'"
                    + ")";

            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The order was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();


        } catch (SQLException ex) {
            Logger.getLogger(EditOrdered_ProductsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
