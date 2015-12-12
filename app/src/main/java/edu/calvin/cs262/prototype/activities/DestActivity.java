package edu.calvin.cs262.prototype.activities;


import android.app.Activity;
import android.graphics.Path;
import android.os.AsyncTask;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.util.HashMap;

import edu.calvin.cs262.prototype.R;
import edu.calvin.cs262.prototype.client.PathfinderClient;
import edu.calvin.cs262.prototype.models.Building;

/**
 * Destination Activity
 * <p/>
 * Allows user to enter building and (optional) room number
 * in the text fields then brings user to Map Activity
 * upon pressing Go! button. Text field input is used to
 * retrieve building coordinates from database, which are
 * used in Map Activity.
 */
public class DestActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);

        // Initialize fields
        final Spinner dropdown = (Spinner)findViewById(R.id.buildingSpinner);

        // Create a dictionary to store Buildings models by name
        final HashMap<String, Building> buildingHashMap = new HashMap<String, Building>();

        // Grab an instance of the client
        PathfinderClient client = PathfinderClient.getInstance();

        // Get all buildings from client
        Building[] buildings = client.getAllBuildings();
        // Create an array of Strings to hold the keys used in the drop-down. It will have the same
        // length as the array of buildings.
        String[] items = new String[buildings.length];
        // For each building from the client...
        for(int i = 0; i < buildings.length; i++){
            // Store the building in a temporary variable
            Building thisBuilding = buildings[i];
            // Add the building to the Dictionary of Building models
            buildingHashMap.put(thisBuilding.getName(), thisBuilding);
            // Add the name of the building to the drop-down menu item array
            items[i] = thisBuilding.getName();
        }

        // Enter values into dropdown menu
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        // Initialize back button
        Button btnMenu = (Button) findViewById(R.id.backmenubutton);
        // Add listener to "Back" button with intent to switch to the main menu
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        // Initialize go button
        Button btnGo = (Button) findViewById(R.id.goButton);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get instance of client
                    PathfinderClient client = PathfinderClient.getInstance();
                    // Find the entered building
                    String drdownContents = dropdown.getSelectedItem().toString();
                    Building desiredBuilding = buildingHashMap.get(drdownContents);
                    // Add a marker to the map at the building's location
                    MapsActivity.setCurrentBuilding(desiredBuilding);

                } catch (NullPointerException n){
                    System.out.println(n.getMessage());
                }
                // Create an intent to start MapActivity
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                // Start activity
                startActivityForResult(intent, 0);
            }
        });

    }


}
