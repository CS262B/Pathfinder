package edu.calvin.cs262.prototype.activities;



import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Document;

import java.util.ArrayList;

import edu.calvin.cs262.prototype.GMapV2Direction;
import edu.calvin.cs262.prototype.R;
import edu.calvin.cs262.prototype.activities.DestActivity;
import edu.calvin.cs262.prototype.client.PathfinderClient;
import edu.calvin.cs262.prototype.models.Building;

/**
 * MapsActivity models an Android activity to display Google Maps in order to view your
 * location at Calvin.
 *
 * Displays interactive map using Google Maps API.
 * Initially centers on user location, and takes
 * input from Destination Activity in order to
 * highlight a given building based on its location.
 * Also will display indoor floor plan for each academic
 * building.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static GoogleMap mMap;
    private static Building currentDestination;
    private Button btnBlueprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mMap = mapFragment.getMap();
        mMap.clear();
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        LatLng zoomDest = null;
        if (currentDestination != null) {
            mMap.setOnMarkerClickListener(this);
            LatLng currentMarker = new LatLng(currentDestination.getLattitude(), currentDestination.getLongitude());
            zoomDest = currentMarker;
            mMap.addMarker(new MarkerOptions().position(currentMarker).title(currentDestination.getName()));
        } else {
            if (checkLocationPermission()) {
                Location location = locationManager.getLastKnownLocation(provider);
                zoomDest = new LatLng(location.getLatitude(), location.getLongitude());
            }
        }
        if (zoomDest != null){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    zoomDest, 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(zoomDest)      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera
                .tilt(40)                   // Sets the tilt of the camera
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

        // Blueprint view button
        btnBlueprint = (Button) findViewById(R.id.blueprintBttn);
        btnBlueprint.setVisibility(View.INVISIBLE);
        btnBlueprint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                directionsToBuilding();
                Intent intent = new Intent(v.getContext(), BlueprintActivity.class);
                startActivityForResult(intent, 0);

            }
        });

        //back to destination activity to choose destination
        Button btnChooseDest = (Button) findViewById(R.id.chooseDestBttn);
        btnChooseDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DestActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    /**
     * directionsToBuilding takes a latitude and longitude of the specified
     * building in dest activity, creates a new direction object, then sends
     * request to Google API, and receives a response in order to draw a Polyline
     * between the user and the building, utilizing paths when possible.
     *
     */
    public static void directionsToBuilding(){
        if(currentDestination != null) {
            LatLng destBuilding = new LatLng(currentDestination.getLattitude(), currentDestination.getLongitude());
            //creating the directions - currently hardcoded
            try {
                if (mMap != null) {
                    Location current = mMap.getMyLocation();
                    if (current != null) {
                        LatLng currentLoc = new LatLng(current.getLatitude(), current.getLongitude());
                        GMapV2Direction md = new GMapV2Direction();
                        //makes a request to Google API for XML listing Lat and Lang points
                        Document doc = md.getDocument(currentLoc, destBuilding, GMapV2Direction.MODE_WALKING);
                        //receives an ArrayList of LatLngs between which to draw the Polyline
                        ArrayList<LatLng> directionPoint = md.getDirection(doc);
                        PolylineOptions rectLine = new PolylineOptions().width(6).color(Color.GREEN);

                        for (int i = 0; i < directionPoint.size(); i++) {
                            rectLine.add(directionPoint.get(i));
                        }

                        mMap.addPolyline(rectLine);
                    } else {
                        System.out.println("Cannot chart path: Current location not found.");
                    }
                } else {
                    System.out.println("Cannot chart path: Map not initialized.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot chart path: Current destination is null.");
        }
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mMap = googleMap;
        //LatLng calvin = new LatLng(42.932426, -85.587151);
    }

    /* checkLocationPermission determines if the app has been granted permission to access
     * the device's location using GPS
     *
     * Return: true if permission is granted, false otherwise
     */
    private boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        btnBlueprint.setVisibility(View.VISIBLE);
        directionsToBuilding();
        return false;
    }

    // Getters and setters
    public static Building getCurrentDestination() {
        return currentDestination;
    }
    /**
     * setCurrentBuilding() takes a building model and sets it as the current destination of the map
     *
     * @param currentBuilding is the building model to represent the destination
     */
    public static void setCurrentBuilding(Building currentBuilding) {
        currentDestination = currentBuilding;
    }

}
