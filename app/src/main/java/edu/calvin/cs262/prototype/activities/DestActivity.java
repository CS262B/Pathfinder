package edu.calvin.cs262.prototype.activities;


import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
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
public class DestActivity extends Activity {

    private static Building currentDestination;
    private Spinner dropdown;
    private HashMap<String, Building> buildingHashMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);

        /**
         * This sets the current destination based on the selection in the drop down menu.
         */
        dropdown = (Spinner) findViewById(R.id.buildingSpinner);
        try {
            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setCurrentDestination();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing?
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Create a dictionary to store Buildings models by name
        buildingHashMap = new HashMap<String, Building>();

        // Grab an instance of the client
        PathfinderClient client = PathfinderClient.getInstance();

        // Get all buildings from client
        Building[] buildings = client.getAllBuildings();

        // Create an array of Strings to hold the keys used in the drop-down. It will have the same
        // length as the array of buildings.
        String[] items = new String[buildings.length];

        // For each building from the client...
        for (int i = 0; i < buildings.length; i++) {
            Building thisBuilding = buildings[i];    // Store the building in a temporary variable
            buildingHashMap.put(thisBuilding.getName(), thisBuilding);    // Add the building to the Dictionary of Building models
            items[i] = thisBuilding.getName();   // Add the name of the building to the drop-down menu item array
        }

        // Enter values into dropdown menu
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        // If the currently selected destination is not null, select it from the spinner
        if(currentDestination != null){
            try {
                int selectionID = 0;
                for(int i = 0; i < items.length; i++){
                    if(items[i] == currentDestination.getName()){
                        selectionID = i;
                        break;
                    }
                }
                dropdown.setSelection(selectionID);
            } catch (Exception e){
                System.err.println("Error: Could not restore last selected destination in dropdown menu.");
                e.printStackTrace();
            }
        }

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

        /**
         * This initializes the go button which then starts the MapActivity and placing a marker on the destination.
         */
        Button btnGo = (Button) findViewById(R.id.goButton);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);    // Create an intent to start MapActivity
                MapsActivity.markerSwitch = true;   // Switch the marker on
                startActivityForResult(intent, 0);  // Start activity
            }
        });

        /**
         * This initializes the floor button and starts the blueprint activity
         */
        Button btnFloor = (Button) findViewById(R.id.floorBtn);
        btnFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BlueprintActivity.class);    // Create an intent to start Blueprint Activity
                startActivityForResult(intent, 0);   // Start activity
            }

        });

    }

    /**
     * This method sets the current destination to whichever selection was made in the drop down menu
     */
    private void setCurrentDestination() {
        String drdownContents = dropdown.getSelectedItem().toString();
        Building desiredBuilding = buildingHashMap.get(drdownContents);
        currentDestination = desiredBuilding;
    }

    /**
     * This returns the destination
     * @return the Selected destination
     */
    public static Building getSelectedBuiding() {
        return currentDestination;
    }
}
