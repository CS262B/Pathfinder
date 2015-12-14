package edu.calvin.cs262.prototype.activities;

/**
 * BlueprintActivity
 *
 * Utilizes a web view to retrieve and display images of the floorplans
 * that exist on an external server. Includes the use of Radio buttons
 * to alternate between the available floors.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import edu.calvin.cs262.prototype.R;
import edu.calvin.cs262.prototype.client.PathfinderClient;
import edu.calvin.cs262.prototype.models.Floor;

public class BlueprintActivity extends Activity {

    private String currentImageURL;
    private String[] imageURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueprint);
        imageURLs = getImageURLs();
        currentImageURL = imageURLs[1];
        resetImage();
        Button mapButton = (Button) findViewById(R.id.mapBttn);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                MapsActivity.markerSwitch = true;
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * getImageURLs utilizes radio buttons, showing only the number of floors
     * that exist for a given building.
     *
     * @return the URLs for the images of the floorplans to the given building
     */
    private String[] getImageURLs() {
        PathfinderClient client = PathfinderClient.getInstance();
        //HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        String[] stringArray = new String[6];
        RadioButton[] radioButtons = {(RadioButton) findViewById(R.id.floor0bttn),
                (RadioButton) findViewById(R.id.floor1bttn),
                (RadioButton) findViewById(R.id.floor2bttn),
                (RadioButton) findViewById(R.id.floor3bttn),
                (RadioButton) findViewById(R.id.floor4bttn),
                (RadioButton) findViewById(R.id.floor5bttn)
        };
        for(int i = 0; i < 6; i++){
            Floor floor = client.getFloor(DestActivity.getSelectedBuiding().getName(), i);
            if(floor == null){
                stringArray[i] = "";
                radioButtons[i].setVisibility(View.INVISIBLE);
            } else {
                stringArray[i] = floor.getURL();
            }
        }
        //hashMap.put(0, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-0.gif");
        //hashMap.put(1, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-1.gif");
        //hashMap.put(2, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-2.gif");
        //hashMap.put(3, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-3.gif");
        //hashMap.put(4, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-4.gif");
        //hashMap.put(5, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-5.gif");
        return stringArray;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.floor0bttn:
                if (checked) {
                    currentImageURL = imageURLs[0];
                }
                    break;
            case R.id.floor1bttn:
                if (checked) {
                    currentImageURL = imageURLs[1];
                }
                break;
            case R.id.floor2bttn:
                if (checked) {
                    currentImageURL = imageURLs[2];
                }
                break;
            case R.id.floor3bttn:
                if (checked) {
                    currentImageURL = imageURLs[3];
                }
                break;
            case R.id.floor4bttn:
                if (checked) {
                    currentImageURL = imageURLs[4];
                }
                break;
            case R.id.floor5bttn:
                if (checked) {
                    currentImageURL = imageURLs[5];
                }
                break;
        }

        resetImage();

    }

    /**
     * Method to get an image from a URL stored in the Activity.
     * Taken from: // http://stackoverflow.com/questions/14867278/android-app-display-image-from-url
     */
    private void resetImage() {
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(75);
        webView.loadUrl(currentImageURL);
        //WebView img = (WebView) findViewById(R.id.webView);
        //try {
            //URL url = new URL(currentImageURL);

            //HttpGet httpRequest = null;

            //httpRequest = new HttpGet(url.toURI());

            //HttpClient httpclient = new DefaultHttpClient();
            //HttpResponse response = (HttpResponse) httpclient
            //        .execute(httpRequest);

            //HttpEntity entity = response.getEntity();
            //BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            //InputStream input = b_entity.getContent();

            //Bitmap bitmap = BitmapFactory.decodeStream(input);

            //img.setImageBitmap(bitmap);


        //} catch (Exception ex) {

        //}
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
