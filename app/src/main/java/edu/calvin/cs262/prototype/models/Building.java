package edu.calvin.cs262.prototype.models;

/**
 * The Building class models a row of the Building table in the database.
 */
public class Building {
    private int myID;
    private String myName, myURL;
    private double myLat, myLong;

    /**
     *
     * @param id the ID assinged in the database
     * @param name the name of the building itself
     * @param lattitude latitude of building's coords
     * @param longitude longitude of building's coords
     * @param URL
     */
    public Building(int id, String name, double lattitude, double longitude, String URL){
        myID = id;
        myName = name;
        myURL = URL;
        myLat = lattitude;
        myLong = longitude;
    }

    //Accessors
    public int getID(){
        return myID;
    }
    public String getName(){
        return myName;
    }
    public String myURL(){
        return myURL;
    }
    public double getLattitude(){
        return myLat;
    }
    public double getLongitude(){
        return myLong;
    }
}
