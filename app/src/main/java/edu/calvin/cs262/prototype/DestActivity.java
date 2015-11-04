package edu.calvin.cs262.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

/**
 * Created by Trevor Edewaard on 10/14/2015.
 */
public class DestActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);

        // Initialize back button
        Button btnMenu= (Button) findViewById(R.id.backmenubutton);
        // Add listener to "Back" button with intent to switch to the main menu
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        // Initialize go button
        Button btnGo= (Button) findViewById(R.id.goButton);
        // Add listener to "Go" button with intent to switch to the map activity
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}
