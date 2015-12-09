package edu.calvin.cs262.prototype.models;

/**
 * The Floor class models a row of the Floor table in the Pathfinder database.
 */
public class Floor {
    private int myID;
    private int myBuildingID;
    private int myFloorNum;
    private String myURL;

    /**
     *
     * @param myID the ID number assigned in database
     * @param myBuildingID the ID number relating the floor to its building
     * @param myFloorNum the level of the floor in the building
     * @param myURL URL of the floor's floorplan image
     */
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
