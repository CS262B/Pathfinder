package edu.calvin.cs262.prototype;

/**
 * Destination Activity
 * <p/>
 * Allows user to enter building and (optional) room number
 * in the text fields then brings user to Map Activity
 * upon pressing Go! button. Text field input is used to
 * retrieve building coordinates from database, which are
 * used in Map Activity.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

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


public class DestActivity extends Activity {
    //hardcoded location for now to test
    private static double bLat = 42.931003;
    private static double bLong = -85.588937;
    private static String bName = "Science Building";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);

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
                startActivityForResult(intent, 0);
                MapsActivity.findBuilding(bLat, bLong, bName);
            }
        });

    }


    //not sure if this is necessary
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //some intent action for map activity goes here...
        if (resultCode == MapsActivity.RESULT_OK) {

        }
    }

//    Setting up my call to the web service
//    private static String ADD STUFF HERE

//    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {
//
//        /**
//         * This method extracts text from the HTTP response entity.
//         *
//         * @param entity
//         * @return
//         * @throws IllegalStateException
//         * @throws IOException
//         */
//          protected String getASCIIContentFromEntity(entity) throws IllegalStateException, IOException {
//              InputStream in = entity.getContent();
//                StringBuffer out = new StringBuffer();
//                int n = 1;
//                while (n > 0) {
//                    byte[] b = new byte[4096];
//                    n = in.read(b);
//                    if (n > 0) out.append(new String(b, 0, n));
//                }
//    return out.toString();
//      }
//    }

}
