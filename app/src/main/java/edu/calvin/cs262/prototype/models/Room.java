package edu.calvin.cs262.prototype.models;

/**
 * The Room class models a row of the Room table in the Pathfinder database.
 */
public class Room {

    private int myID, myFloorID, myRelativeX, myRelativeY, myRoomNum;

    public Room(int myID, int myFloorID, int myRelativeX, int myRelativeY, int myRoomNum) {
        this.myID = myID;
        this.myFloorID = myFloorID;
        this.myRelativeX = myRelativeX;
        this.myRelativeY = myRelativeY;
        this.myRoomNum = myRoomNum;
    }

    public int getMyID() {
        return myID;
    }

    public int getMyFloorID() {
        return myFloorID;
    }

    public int getMyRelativeX() {
        return myRelativeX;
    }

    public int getMyRelativeY() {
        return myRelativeY;
    }

    public int getMyRoomNum() {
        return myRoomNum;
    }


}
