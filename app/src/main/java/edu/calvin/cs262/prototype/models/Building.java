package edu.calvin.cs262.prototype.models;

/**
 * The Building class models a row of the Building table in the database.
 */
public class Building {
    private int myID;
    private String myName;
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
        myLat = lattitude;
        myLong = longitude;
    }

    public Building(String buildingString) {
        String[] words = buildingString.split(" ");
        myID = Integer.parseInt(words[0]);
        myName = words[1];
        myLat = Float.parseFloat(words[2]);
        myLong = Float.parseFloat(words[3]);
    }

    //Accessors
    public int getID(){
        return myID;
    }
    public String getName(){
        return myName;
    }
    public double getLattitude(){
        return myLat;
    }
    public double getLongitude(){
        return myLong;
    }
}
