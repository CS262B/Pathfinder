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

public class SB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sb);

        //button controller for Basement button
        Button btnBasement= (Button) findViewById(R.id.basement_button);
        btnBasement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/SB-0.gif"));
                startActivity(browserIntent);
            }
        });

        //button controller for Basement button
        Button btnFirst= (Button) findViewById(R.id.first_button);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/SB-1.gif"));
                startActivity(browserIntent);
            }
        });

        //button controller for Basement button
        Button btnSecond= (Button) findViewById(R.id.second_button);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/SB-2.gif"));
                startActivity(browserIntent);
            }
        });

        //button controller for Basement button
        Button btnThird= (Button) findViewById(R.id.third_button);
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-2.amazonaws.com/blakedg/SB-3.gif"));
                startActivity(browserIntent);
            }
        });
    }
}
