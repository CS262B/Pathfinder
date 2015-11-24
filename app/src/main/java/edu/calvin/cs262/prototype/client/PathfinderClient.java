package edu.calvin.cs262.prototype.client;

import edu.calvin.cs262.prototype.models.Building;

/**
 * PathfinderClient is a singleton class which handles communication with the server.
 * It provides methods to get building and room model objects.
 * To instantiate PathfinderClient, call the static method PathfinderClient.getInstance()
 * Created by Derek Dik (drd26) on 11/23/2015.
 */
public class PathfinderClient {
    private static PathfinderClient instance;
    private PathfinderClient(){

    }

    public static PathfinderClient getInstance(){
        if(instance == null){
            instance = new PathfinderClient();
        }
        return instance;
    }

    public Building getBuildingByName(String name){
        // Placeholder method stub returns the science building
        return new Building(0, "Science Building", 42.931003, -85.588937, "picture.gif");
    }


}
