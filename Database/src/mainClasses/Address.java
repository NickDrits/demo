/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author Nick
 */
public class Address {
    int Cid , Aid , Number , Postal ;
    String Country , Town , Street  ; 
    
    public int getCID() {
        return Cid;
    }

    public int getAID() {
        return Aid;
    }
    
    public int getNUMBER() {
        return Number;
    }
    
    public int getPOSTAL() {
        return Postal;
    }
    
    
    
    public String getCOUNTRY() {
        return Country;
    }
    
    public String getTOWN() {
        return Town;
    }
    
    public String getSTREET() {
        return Street;
    }



    public void setCID(int Cid) {
        this.Cid = Cid;
    }
    
    public void setAID(int Aid ) {
        this.Aid = Aid;
    }
    
    public void setNUMBER(int Number ) {
        this.Number = Number;
    }
    
    public void setPOSTAL(int Postal ) {
        this.Postal = Postal;
    }
    
    
    public void setCOUNTRY(String Country ) {
        this.Country = Country;
    }
    
    public void setTOWN(String Town ) {
        this.Town = Town;
    }

    public void setSTREET(String Street ) {
        this.Street = Street;
    }
}
