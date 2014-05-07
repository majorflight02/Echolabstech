package com.Tremec.tremec.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.net.Uri;

public class Instructions extends Activity {

    public static final int RESULT_RESET = 305;
    private final Instructions myThis = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        final ImageButton button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(myThis, Step.class), RESULT_RESET);
            }
        });

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(0xf0555555);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webView.loadUrl("file:///android_asset/inst.html");
        webView.setWebViewClient(new CustomWebViewClient());

        MainActivity.getInstance().urlString = "file:///android_asset/drivelinediagram.png";
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(myThis, HTMLView.class);
            startActivityForResult(intent, RESULT_OK);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == RESULT_RESET)&&(resultCode == RESULT_RESET)) {
            setResult(RESULT_RESET);
            finish();
        }
    }
}
