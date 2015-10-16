package edu.calvin.cs262.prototype;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by bpd4 on 10/15/2015.
 */
public class MapActivity extends Activity
//Called when the activity is first created
{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapactivity);
    }
}
