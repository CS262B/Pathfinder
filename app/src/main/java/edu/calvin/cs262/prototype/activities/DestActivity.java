package edu.calvin.cs262.prototype.activities;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

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
        final EditText buildingCodeField = (EditText) findViewById(R.id.buildingCodeField);
        EditText roomNumField = (EditText) findViewById(R.id.roomNumField);

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
                    Building desiredBuilding = client.getBuilding(buildingCodeField.getText().toString());
                    MapsActivity ma = new MapsActivity();
                    //drawing directions to current building
                    ma.directionsToBuilding(desiredBuilding.getLattitude(), desiredBuilding.getLongitude());
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
