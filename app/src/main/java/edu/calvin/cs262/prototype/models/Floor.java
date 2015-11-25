package edu.calvin.cs262.prototype.models;

/**
 * The Floor class models a row of the Floor table in the Pathfinder database.
 *
 * Created by Derek Dik (drd26) on 11/23/2015.
 */
public class Floor {
    private int myID;
    private int myBuildingID;
    private int myFloorNum;
    private String myURL;

    public Floor(int myID, int myBuildingID, int myFloorNum, String myURL) {
        this.myID = myID;
        this.myBuildingID = myBuildingID;
        this.myFloorNum = myFloorNum;
        this.myURL = myURL;
    }

    public int getID() {
        return myID;
    }

    public int getBuildingID() {
        return myBuildingID;
    }

    public int getFloorNum() {
        return myFloorNum;
    }

    public String getURL() {
        return myURL;
    }
}
