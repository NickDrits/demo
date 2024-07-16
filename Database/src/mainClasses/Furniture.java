/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author Nick
 */
public class Furniture {
    int Fid , Assemblage , Width , Depth , Height ,Weight,Price , Did ;
    String Name , Sett , Material , Color ,Description, Room ,Type; 
    
    public int getFID() {
        return Fid;
    }
    
    public int getDID() {
        return Did;
    }

    public int getASSEMBLAGE() {
        return Assemblage;
    }
    
    public int getHEIGHT() {
        return Height;
    }
    
    public int getWIDTH() {
        return Width;
    }
    
    public int getDEPTH() {
        return Depth;
    }
    
    public int getWEIGHT() {
        return Weight;
    }
    
    public int getPRICE() {
        return Price;
    }
    
    public String getNAME() {
        return Name;
    }
    
    public String getTYPE() {
        return Type;
    }   
    
    public String getSET() {
        return Sett;
    }
    
    public String getMATERIAL() {
        return Material;
    }
    
    public String getCOLOR() {
        return Color;
    }
    
    public String getDESCRIPTION() {
        return Description;
    }
    
    public String getROOM() {
        return Room;
    }


    public void setFID(int Fid) {
        this.Fid = Fid;
    }
    
    public void setDID(int Did) {
        this.Did = Did;
    }
    
    public void setASSEMBLAGE(int Assemblage ) {
        this.Assemblage = Assemblage;
    }
    
    public void setHEIGHT(int Height ) {
        this.Height = Height;
    }
    
    public void setWIDTH(int Width ) {
        this.Width = Width;
    }
    
    public void setDEPTH(int Depth ) {
        this.Depth = Depth;
    }
    
    public void setWEIGHT(int Weight ) {
        this.Weight = Weight;
    }
    
    public void setPRICE(int Price ) {
        this.Price = Price;
    }
      
    public void setNAME(String Name ) {
        this.Name = Name;
    }
    
    public void setSET(String Sett ) {
        this.Sett = Sett;
    }

    public void setMATERIAL(String Material ) {
        this.Material = Material;
    }
    
    public void setCOLOR(String Color ) {
        this.Color = Color;
    }
    
    public void setDESCRIPTION(String Description ) {
        this.Description = Description;
    }
    
    public void setROOM(String Room ) {
        this.Room = Room;
    }
    
    public void setTYPE(String Type ) {
        this.Type = Type;
    }
}
