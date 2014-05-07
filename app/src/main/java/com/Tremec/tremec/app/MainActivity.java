package com.Tremec.tremec.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.Tremec.tremec.app.Introduction;

public class MainActivity extends Activity {

    public static MainActivity instance;

    public static final int RESULT_RESET = 305;

    public float angle1 = Float.MIN_VALUE;
    public float angle2 = Float.MIN_VALUE;
    public float angle3 = Float.MIN_VALUE;

    public String urlString;
    public String urlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;
        final MainActivity myThis = this;

        final ImageButton button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(myThis, Introduction.class));
            }
        });

    }

    public static MainActivity getInstance()
    {
        return instance;
    }
}
