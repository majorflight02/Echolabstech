package com.Tremec.tremec.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.Tremec.tremec.app.MainActivity;

public class HTMLView extends Activity {
    public static final int RESULT_RESET = 305;
    private final HTMLView myThis = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        String urlString = MainActivity.getInstance().urlString;

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(urlString);
        webView.setBackgroundColor(0xf0555555);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

        String titleString = MainActivity.getInstance().urlTitle;
        this.getActionBar().setTitle(titleString);
        webView.setWebViewClient(new CustomWebViewClient());
        */
        /*
        04/28/14
        Echolabstech
        Major Sapp III
         */
        String titleString = MainActivity.getInstance().urlTitle;
        this.getActionBar().setTitle(titleString);

    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(myThis, Diagram.class);
            startActivity(intent);
            return true;
        }
    }
}
