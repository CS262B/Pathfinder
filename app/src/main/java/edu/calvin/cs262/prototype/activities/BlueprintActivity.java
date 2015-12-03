package edu.calvin.cs262.prototype.activities;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import edu.calvin.cs262.prototype.R;

public class BlueprintActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueprint);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());

        String url = "https://raw.githubusercontent.com/CS262B/Pathfinder/master/materials/Floor%20Plans/SB-1.gif";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
