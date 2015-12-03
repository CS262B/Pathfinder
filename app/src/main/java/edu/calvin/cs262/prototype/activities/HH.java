package edu.calvin.cs262.prototype.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.calvin.cs262.prototype.R;

public class HH extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hh);

        //button controller for Basement button
        Button btnHH1 = (Button) findViewById(R.id.HH1);
        btnHH1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/HH-2.gif.gif"));
                startActivity(browserIntent);
            }
        });

        //button controller for Basement button
        Button btnHH2 = (Button) findViewById(R.id.HH2);
        btnHH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/HH-3.gif"));
                startActivity(browserIntent);
            }
        });

        //button controller for Basement button
        Button btnHH3 = (Button) findViewById(R.id.HH3);
        btnHH3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/HH-4.gif"));
                startActivity(browserIntent);
            }
        });
    }
}
