package com.Tremec.tremec.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class Diagram extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);
    }

    @Override
    protected void onResume() {
        super.onResume();

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/drivelinediagram.png");
        webView.setBackgroundColor(0xf0555555);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

        this.getActionBar().setTitle("Diagram");
    }

}
