/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import static database.DB_Connection.getInitialConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import mainClasses.Deal;
import mainClasses.Furniture;

import database.tables.EditOrdered_ProductsTable;
import database.tables.EditDealTable;
import database.tables.EditAddressTable;
import database.tables.EditClientTable;
import database.tables.EditFurnitureTable;
import database.tables.EditOrderTable;


/*
 *
 * @author sepulchure
 */
public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InitDatabase init = new InitDatabase();
        init.initDatabase();
        init.initTables();
        init.addToDatabaseExamples();
        //init.updateRecords();
        //init.databaseToJSON();

        // init.deleteRecords();
    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE HY360_2023");
        stmt.close();
        conn.close();
    }

    public void initTables() throws SQLException, ClassNotFoundException {
        EditOrdered_ProductsTable eop = new EditOrdered_ProductsTable();
        eop.createOrdered_ProductsTable();
        
        EditDealTable ed = new EditDealTable();
        ed.createDealTable();
        
        EditAddressTable ea = new EditAddressTable();
        ea.createAddressTable();
        
        EditClientTable ec = new EditClientTable();
        ec.createClientTable();

        EditFurnitureTable ef = new EditFurnitureTable();
        ef.createFurnitureTable();

        EditOrderTable eo = new EditOrderTable();
        eo.createOrderTable();
        

    }

    public void addToDatabaseExamples() throws ClassNotFoundException, SQLException {
        EditOrdered_ProductsTable eop = new EditOrdered_ProductsTable();
        eop.addOrdered_ProductsFromJSON(Resources.OrderedJSON);

        
        EditDealTable ed = new EditDealTable();
        ed.addDealFromJSON(Resources.DealJSON);
        Deal mydeal = new Deal();
        mydeal.setDID(2);
        mydeal.setPRICE(100);
        ed.createNewDeal(mydeal);
        
        EditClientTable ec = new EditClientTable();
        ec.addClientFromJSON(Resources.ClientJSON);
        
        EditFurnitureTable ef = new EditFurnitureTable();
        ef.addFurnitureFromJSON(Resources.FurnitureJSON);
        ef.addFurnitureFromJSON(Resources.FurnitureJSON1);
        
        EditOrderTable eo = new EditOrderTable();
        eo.addOrderFromJSON(Resources.OrderJSON);

        EditAddressTable ea = new EditAddressTable();
        ea.addAddressFromJSON(Resources.AddressJSON);
        
        //ef.deleteFurniture(1);        works!
        //ef.updateFurniture(1, "Sett", "procelain"); works
        //Furniture furn = ef.databaseToFurniture(1);works
        //System.out.println(ef.FurnitureToJSON(furn)); works

    }

    public void databaseToJSON() throws ClassNotFoundException, SQLException {

    }

    public void updateRecords() throws ClassNotFoundException, SQLException {

    }

    public void deleteRecords() throws ClassNotFoundException, SQLException {

    }

}
