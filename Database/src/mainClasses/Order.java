/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author Nick
 */
public class Order {
    int Oid ,Price, Trasport , Num_of_prod , Day , Month ,Year ,Cid ;
    String State ; 
    
    public int getOID() {
        return Oid;
    }

    public int getNUM_OF_PROD() {
        return Num_of_prod;
    }
    
    public int getTRASPORT() {
        return Trasport;
    }
    
    public int getDAY() {
        return Day;
    }
    
    public int getMONTH() {
        return Month;
    }
    
    public int getYEAR() {
        return Year;
    }
    
    public int getPRICE() {
        return Price;
    }
    
    public int getCID() {
        return Cid;
    }
    
    public String getSTATE() {
        return State;
    }
    


    public void setOID(int Oid) {
        this.Oid = Oid;
    }
    
    public void setNUM_OF_PROD(int Num_of_prod ) {
        this.Num_of_prod = Num_of_prod;
    }
    
    public void setTRASPORT(int Trasport ) {
        this.Trasport = Trasport;
    }
    
    public void setDAY(int Day ) {
        this.Day = Day;
    }
    
    public void setMONTH(int Month ) {
        this.Month = Month;
    }
    
    public void setYEAR(int Year ) {
        this.Year = Year;
    }
    
    public void setPRICE(int Price ) {
        this.Price = Price;
    }
      
    public void setCID(int Cid ) {
        this.Cid = Cid;
    }
    
    public void setSTATE(String State ) {
        this.State = State;
    }

}
