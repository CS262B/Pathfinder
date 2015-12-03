package edu.calvin.cs262.prototype.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
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

public class BlueprintActivity extends Activity {

    private String currentImageURL;
    private HashMap<Integer, String> imageURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueprint);
        imageURLs = getImageURLs();
        resetImage();


    }

    private HashMap<Integer, String> getImageURLs() {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(0, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-0.gif");
        hashMap.put(1, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-1.gif");
        hashMap.put(2, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-2.gif");
        hashMap.put(3, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-3.gif");
        hashMap.put(4, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-4.gif");
        hashMap.put(5, "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-5.gif");
        return hashMap;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.floor0bttn:
                if (checked) {
                    currentImageURL = imageURLs.get(0);
                }
                    break;
            case R.id.floor1bttn:
                if (checked) {
                    currentImageURL = imageURLs.get(1);
                }
                break;
            case R.id.floor2bttn:
                if (checked) {
                    currentImageURL = imageURLs.get(2);
                }
                break;
            case R.id.floor3bttn:
                if (checked) {
                    currentImageURL = imageURLs.get(3);
                }
                break;
            case R.id.floor4bttn:
                if (checked) {
                    currentImageURL = imageURLs.get(4);
                }
                break;
            case R.id.floor5bttn:
                if (checked) {
                    currentImageURL = imageURLs.get(5);
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
        ImageView img = (ImageView) findViewById(R.id.imageView);
        try {
            URL url = new URL(currentImageURL);
            HttpGet httpRequest = null;

            httpRequest = new HttpGet(url.toURI());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) httpclient
                    .execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(input);

            img.setImageBitmap(bitmap);

        } catch (Exception ex) {

        }
    }

}
