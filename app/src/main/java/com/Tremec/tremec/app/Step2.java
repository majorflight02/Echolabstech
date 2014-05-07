package com.Tremec.tremec.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Step2 extends Activity implements SensorEventListener {

    public static final int RESULT_RESET = 305;


    private SensorManager mSensorManager;
    Sensor accelerometer;
    Sensor magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        final Step2 myThis = this;
        final ImageButton button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(myThis, Step3.class), 0);
            }
        });

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);

        this.getActionBar().setTitle("Step 2");
        Context context = getApplicationContext();
        CharSequence text = "Place smartphone on flat surface of the driveshaft.  Hold the device steady and press SET to record measurement.";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();

        MainActivity.getInstance().urlTitle = "Help - Step 2";
        MainActivity.getInstance().urlString = "file:///android_asset/step2detail.html";
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_RESET) {
            setResult(RESULT_RESET);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.step2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            MainActivity.getInstance().urlTitle = "Help - Step 2";
            MainActivity.getInstance().urlString = "file:///android_asset/step2detail.html";
            startActivity(new Intent(this, HTMLView.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int val) {

    }

    float[] mGravity;
    float[] mGeomagnetic;
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                float ROut[] = new float[9];
                SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_X, SensorManager.AXIS_Z, ROut);
                SensorManager.getOrientation(ROut, orientation);

                float angleInDegrees2 = orientation[2] * (180.0f / (float)Math.PI);

/*
                float angleInDegrees0 = orientation[0] * (180.0f / (float)Math.PI);
                float angleInDegrees1 = orientation[1] * (180.0f / (float)Math.PI);
                Log.d("deb", "angle0:" + String.format("%05.1f", angleInDegrees0) +
                        "\tangle1:" + String.format("%05.1f", angleInDegrees1) +
                        "\tangle2:" + String.format("%05.1f", angleInDegrees2));
*/

                float angleInDegrees = angleInDegrees2;

                if (angleInDegrees < 0) {
                    angleInDegrees += 360.0f;
                }

                float rotatedAngleInDegrees = 0.0f;
                if (0 < angleInDegrees && angleInDegrees <= 45) {
                    rotatedAngleInDegrees = angleInDegrees;

                } else if (45 < angleInDegrees && angleInDegrees <= 135) {
                    rotatedAngleInDegrees = angleInDegrees - 90;

                } else if (135 < angleInDegrees && angleInDegrees <= 225) {
                    rotatedAngleInDegrees = angleInDegrees - 180;

                } else if (225 < angleInDegrees && angleInDegrees <= 315) {
                    rotatedAngleInDegrees = angleInDegrees - 270;

                } else if (315 < angleInDegrees) {
                    rotatedAngleInDegrees = angleInDegrees - 360;
                }

                if (rotatedAngleInDegrees > 0) {
                    showDown();
                } else {
                    showUp();
                }

                showAngle(rotatedAngleInDegrees);

                MainActivity.getInstance().angle2 = rotatedAngleInDegrees;
            }
        }
    }

    private void showAngle(float rotatedAngleInDegrees) {
        ImageView tensImageView;
        ImageView onesImageView;
        ImageView tenthsImageView;

        tensImageView = (ImageView)findViewById(R.id.imageViewTens);
        onesImageView = (ImageView)findViewById(R.id.imageViewOnes);
        tenthsImageView = (ImageView)findViewById(R.id.imageViewTenths);

        rotatedAngleInDegrees = Math.abs(rotatedAngleInDegrees);

        int intAngle = (int)rotatedAngleInDegrees;
        int tens = intAngle / 10;
        int ones = intAngle % 10;
        int tenths = ((int) (rotatedAngleInDegrees * 10.0f)) % 10;

        tensImageView.setImageResource(findDigitImageForInt(tens));
        onesImageView.setImageResource(findDigitImageForInt(ones));
        tenthsImageView.setImageResource(findDigitImageForInt(tenths));
    }

    private void showUp() {
        ImageView upImageView = (ImageView)findViewById(R.id.imageViewUp);
        ImageView downImageView = (ImageView)findViewById(R.id.imageViewDown);

        downImageView.setVisibility(View.GONE);
        upImageView.setVisibility(View.VISIBLE);
    }

    private void showDown() {
        ImageView upImageView = (ImageView)findViewById(R.id.imageViewUp);
        ImageView downImageView = (ImageView)findViewById(R.id.imageViewDown);

        downImageView.setVisibility(View.VISIBLE);
        upImageView.setVisibility(View.GONE);
    }

    private int findDigitImageForInt(int digit) {
        int imageID;
        switch (digit) {
            default:
            case 0:
                imageID = R.drawable.number_0;
                break;
            case 1:
                imageID = R.drawable.number_1;
                break;
            case 2:
                imageID = R.drawable.number_2;
                break;
            case 3:
                imageID = R.drawable.number_3;
                break;
            case 4:
                imageID = R.drawable.number_4;
                break;
            case 5:
                imageID = R.drawable.number_5;
                break;
            case 6:
                imageID = R.drawable.number_6;
                break;
            case 7:
                imageID = R.drawable.number_7;
                break;
            case 8:
                imageID = R.drawable.number_8;
                break;
            case 9:
                imageID = R.drawable.number_9;
                break;
        }

        return imageID;
    }
}
