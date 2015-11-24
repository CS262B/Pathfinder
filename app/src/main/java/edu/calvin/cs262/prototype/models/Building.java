package edu.calvin.cs262.prototype.models;

/**
 * The Building class models a row of the Building table in the database.
 *
 * Created by Derek Dik (drd26) on 11/23/2015.
 */
public class Building {
    private String myName, myURL;
    private double myLat, myLong;

    public Building(String name, double lattitude, double longitude, String URL){
        myName = name;
        myURL = URL;
        myLat = lattitude;
        myLong = longitude;
    }

    //Accessors
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
