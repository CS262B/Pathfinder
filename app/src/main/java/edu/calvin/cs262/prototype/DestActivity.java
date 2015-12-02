package edu.calvin.cs262.prototype;


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

import java.lang.String;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;

import java.io.InputStream;
import java.io.IOException;

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
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                try {
                    // Get instance of client
                    PathfinderClient client = PathfinderClient.getInstance();
                    // Find the entered building
                    Building desiredBuilding = client.getBuilding(buildingCodeField.getText().toString());
                    // Add a marker to the map at the building's location
                    MapsActivity.findBuilding(desiredBuilding.getLattitude(), desiredBuilding.getLongitude(), desiredBuilding.getName());
                    System.out.println("Marker placed!");
                } catch (NullPointerException n){
                    System.out.println(n.getMessage());
                }
                startActivityForResult(intent, 0);
            }
        });

    }


    //  Not sure if this is necessary
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //some intent action for map activity goes here...
        if (resultCode == MapsActivity.RESULT_OK) {

        }
    }

    //Setting up my call to the web service
    private static String PEOPLE_URI = "http://10.0.2.2:9998/pathfinder/";

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        /**
         * This method extracts text from the HTTP response entity.
         *
         * @param entity
         * @return
         * @throws IllegalStateException
         * @throws IOException
         */
          protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
              InputStream in = entity.getContent();
              StringBuffer out = new StringBuffer();
              int n = 1;
              while (n > 0) {
                  byte[] b = new byte[4096];
                  n = in.read(b);
                  if (n > 0) out.append(new String(b, 0, n));
              }
              return out.toString();
          }

         /**
          * This method issues the HTTP GET request.
          *
          * @param params
          * @return
          */
          @Override
          protected String doInBackground(Void... params) {
              HttpClient httpClient = new DefaultHttpClient();
              HttpContext localContext = new BasicHttpContext();
              HttpGet httpGet = new HttpGet(PEOPLE_URI);
              String text = null;
              try {
                  HttpResponse response = httpClient.execute(httpGet, localContext);
                  HttpEntity entity = response.getEntity();
                  text = getASCIIContentFromEntity(entity);
              } catch (Exception e) {
                  return e.getLocalizedMessage();
              }
              return text;
          }

        /**
         * The method takes the results of the request, when they arrive, and updates the interface.
         *
         * @param results
         */
          protected void onPostExecute(String results) {
              if (results != null) {
                  EditText et = (EditText) findViewById(R.id.my_edit);
                  et.setText(results);
              }
              Button b = (Button) findViewById(R.id.my_button);
              b.setClickable(true);
          }
    }
}
