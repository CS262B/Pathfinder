package edu.calvin.cs262.prototype;

/**
 * Destination Activity
 *
 * Allows user to enter building and (optional) room number
 * in the text fields then brings user to Map Activity
 * upon pressing Go! button. Text field input is used to
 * retrieve building coordinates from database, which are
 * used in Map Activity.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class DestActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);

        //back to the main menu
        Button btnMenu= (Button) findViewById(R.id.backmenubutton);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //to map activity with eventual display for entered building and room number
        Button btnGo= (Button) findViewById(R.id.goButton);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}
