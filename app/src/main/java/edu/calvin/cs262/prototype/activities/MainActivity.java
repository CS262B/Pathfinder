package edu.calvin.cs262.prototype.activities;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import edu.calvin.cs262.prototype.R;

/**
 * Main Menu Activity
 *
 * Contains buttons for accessing both the
 * Map and Destination Activities along with our help document
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);

        //allows network to run on main thread for now. Not ideal.
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /**
         * This is the button controller for the Map activity.
         *
         * @onClick launches the destination activity and brings user to the Map layout
         */
        Button btnMap = (Button) findViewById(R.id.map_button);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                MapsActivity.markerSwitch = false;
                startActivityForResult(intent, 0);
            }
        });


        /**
         * This is the button for the destination activity
         *
         * @onClick launches destination activity and brings user to destination layout.
         */
        Button btnDest= (Button) findViewById(R.id.dest_button);
        btnDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DestActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        /**
         * This is the button for the help document
         *
         * @onClick launches web page containing the online help document
         */
        final Button helpBtn= (Button) findViewById(R.id.help);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/Calvin+Pathfinder+Help.htm"));
                startActivity(browserIntent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);   // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
