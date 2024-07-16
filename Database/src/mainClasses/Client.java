/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author Nick
 */
public class Client {
    int Cid  , Day , Month , Year ;
    String Fname , Sname , Username , Password , Phone ; 
    
    public int getCID() {
        return Cid;
    }

    public String getPHONE() {
        return Phone;
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
    
    public String getFNAME() {
        return Fname;
    }
    
    public String getSNAME() {
        return Sname;
    }
    
    public String getUSERNAME() {
        return Username;
    }
    
    public String getPASSWORD() {
        return Password;
    }


    public void setCID(int Cid) {
        this.Cid = Cid;
    }
    
    public void setPHONE(String Phone ) {
        this.Phone = Phone;
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
    
    public void setFNAME(String Fname ) {
        this.Fname = Fname;
    }
    
    public void setSNAME(String Sname ) {
        this.Sname = Sname;
    }

    public void setUSERNAME(String Username ) {
        this.Username = Username;
    }
    
    public void setPASSWORD(String Password ) {
        this.Password = Password;
    }
}
