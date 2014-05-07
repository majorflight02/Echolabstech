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

import com.Tremec.tremec.app.Instructions;

public class Introduction extends Activity {

    public static final int RESULT_RESET = 305;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        final Introduction myThis = this;
        final ImageButton button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(myThis, Instructions.class), 0);
            }
        });

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(0xf0555555);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webView.loadUrl("file:///android_asset/info.html");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_RESET) {
            setResult(RESULT_RESET);
            finish();
        }
    }
}
