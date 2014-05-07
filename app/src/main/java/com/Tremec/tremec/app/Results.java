package com.Tremec.tremec.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Results extends Activity {

    public static final int RESULT_RESET = 305;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final Results myThis = this;
        final ImageButton button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_RESET);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getActionBar().setTitle("Results");

        float angle1 = MainActivity.getInstance().angle1;
        float angle2 = MainActivity.getInstance().angle2;
        float angle3 = MainActivity.getInstance().angle3;

        boolean measure0Valid = false;
        if ((Float.MIN_VALUE != angle1) && (Float.MAX_VALUE != angle1))
            measure0Valid = true;

        boolean measure1Valid = false;
        if ((Float.MIN_VALUE != angle2) && (Float.MAX_VALUE != angle2))
            measure1Valid = true;

        boolean measure2Valid = false;
        if ((Float.MIN_VALUE != angle3) && (Float.MAX_VALUE != angle3))
            measure2Valid = true;


        float angleA = Math.abs(calculateAngleA());
        if (!angleAIsGood()) {
            // display red
            ImageView tensImageView;
            ImageView onesImageView;
            ImageView tenthsImageView;
            ImageView decimalImageView;
            decimalImageView = (ImageView)findViewById(R.id.imageViewDecimal);
            decimalImageView.setImageResource(R.drawable.number_decimal_red);

            tensImageView = (ImageView)findViewById(R.id.imageViewTens);
            onesImageView = (ImageView)findViewById(R.id.imageViewOnes);
            tenthsImageView = (ImageView)findViewById(R.id.imageViewTenths);

            int intAngle = (int)Math.abs(angleA);
            int tens = intAngle / 10;
            int ones = intAngle % 10;
            int tenths = ((int)(angleA * 10.0f)) % 10;

            tensImageView.setImageResource(findRedDigitImageForInt(tens));
            onesImageView.setImageResource(findRedDigitImageForInt(ones));
            tenthsImageView.setImageResource(findRedDigitImageForInt(tenths));

        } else {
            // display angleA as green
            ImageView tensImageView;
            ImageView onesImageView;
            ImageView tenthsImageView;
            ImageView decimalImageView;
            decimalImageView = (ImageView)findViewById(R.id.imageViewDecimal);
            decimalImageView.setImageResource(R.drawable.number_decimal_green);

            tensImageView = (ImageView)findViewById(R.id.imageViewTens);
            onesImageView = (ImageView)findViewById(R.id.imageViewOnes);
            tenthsImageView = (ImageView)findViewById(R.id.imageViewTenths);

            int intAngle = (int)Math.abs(angleA);
            int tens = intAngle / 10;
            int ones = intAngle % 10;
            int tenths = ((int)(angleA * 10.0f)) % 10;

            tensImageView.setImageResource(findGreenDigitImageForInt(tens));
            onesImageView.setImageResource(findGreenDigitImageForInt(ones));
            tenthsImageView.setImageResource(findGreenDigitImageForInt(tenths));
        }

        float angleB = calculateAngleB();
        if (!angleBIsGood()) {
            //display angleb red
            ImageView tensImageView;
            ImageView onesImageView;
            ImageView tenthsImageView;
            ImageView decimalImageView;
            decimalImageView = (ImageView)findViewById(R.id.imageView2Decimal);
            decimalImageView.setImageResource(R.drawable.number_decimal_red);

            tensImageView = (ImageView)findViewById(R.id.imageView2Tens);
            onesImageView = (ImageView)findViewById(R.id.imageView2Ones);
            tenthsImageView = (ImageView)findViewById(R.id.imageView2Tenths);

            int intAngle = (int)Math.abs(angleB);
            int tens = intAngle / 10;
            int ones = intAngle % 10;
            int tenths = ((int)(angleB * 10.0f)) % 10;

            tensImageView.setImageResource(findRedDigitImageForInt(tens));
            onesImageView.setImageResource(findRedDigitImageForInt(ones));
            tenthsImageView.setImageResource(findRedDigitImageForInt(tenths));

        } else {
            //display angleb green
            ImageView tensImageView;
            ImageView onesImageView;
            ImageView tenthsImageView;
            ImageView decimalImageView;
            decimalImageView = (ImageView)findViewById(R.id.imageView2Decimal);
            decimalImageView.setImageResource(R.drawable.number_decimal_green);

            tensImageView = (ImageView)findViewById(R.id.imageView2Tens);
            onesImageView = (ImageView)findViewById(R.id.imageView2Ones);
            tenthsImageView = (ImageView)findViewById(R.id.imageView2Tenths);

            int intAngle = (int)Math.abs(angleB);
            int tens = intAngle / 10;
            int ones = intAngle % 10;
            int tenths = ((int)(angleB * 10.0f)) % 10;

            tensImageView.setImageResource(findGreenDigitImageForInt(tens));
            onesImageView.setImageResource(findGreenDigitImageForInt(ones));
            tenthsImageView.setImageResource(findGreenDigitImageForInt(tenths));
        }

        float operatingAngle = calculateOperatingAngle();
        if (!operatingAngleIsGood()) {
            //display operation angle red
            ImageView tensImageView;
            ImageView onesImageView;
            ImageView tenthsImageView;
            ImageView decimalImageView;
            decimalImageView = (ImageView)findViewById(R.id.imageView3Decimal);
            decimalImageView.setImageResource(R.drawable.number_decimal_red);

            tensImageView = (ImageView)findViewById(R.id.imageView3Tens);
            onesImageView = (ImageView)findViewById(R.id.imageView3Ones);
            tenthsImageView = (ImageView)findViewById(R.id.imageView3Tenths);

            int intAngle = (int)Math.abs(operatingAngle);
            int tens = intAngle / 10;
            int ones = intAngle % 10;
            int tenths = ((int)(operatingAngle * 10.0f)) % 10;

            tensImageView.setImageResource(findRedDigitImageForInt(tens));
            onesImageView.setImageResource(findRedDigitImageForInt(ones));
            tenthsImageView.setImageResource(findRedDigitImageForInt(tenths));

        } else {
            //display operation angle green
            ImageView tensImageView;
            ImageView onesImageView;
            ImageView tenthsImageView;
            ImageView decimalImageView;
            decimalImageView = (ImageView)findViewById(R.id.imageView3Decimal);
            decimalImageView.setImageResource(R.drawable.number_decimal_green);

            tensImageView = (ImageView)findViewById(R.id.imageView3Tens);
            onesImageView = (ImageView)findViewById(R.id.imageView3Ones);
            tenthsImageView = (ImageView)findViewById(R.id.imageView3Tenths);

            int intAngle = (int)Math.abs(operatingAngle);
            int tens = intAngle / 10;
            int ones = intAngle % 10;
            int tenths = ((int)(operatingAngle * 10.0f)) % 10;

            tensImageView.setImageResource(findGreenDigitImageForInt(tens));
            onesImageView.setImageResource(findGreenDigitImageForInt(ones));
            tenthsImageView.setImageResource(findGreenDigitImageForInt(tenths));
        }

        if (angleAIsGood() && angleBIsGood() && operatingAngleIsGood()) {
            Context context = getApplicationContext();
            CharSequence text = "Congratulations!  Your angles are within the acceptable limits. Use the menu for more information about these results.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            MainActivity.getInstance().urlString = "file:///android_asset/helpDetailedArray1.html";

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Sorry!  It appears that one or more of your angles is incorrect or exceeds the recommended limits.  Use the menu for more information about these results.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            MainActivity.getInstance().urlString = "file:///android_asset/helpDetailedArray0.html";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            MainActivity.getInstance().urlTitle = "Help - Results";
            startActivity(new Intent(this, HTMLView.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private int findWhiteDigitImageForInt(int digit) {
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

    private int findGreenDigitImageForInt(int digit) {
        int imageID;
        switch (digit) {
            default:
            case 0:
                imageID = R.drawable.number_0_green;
                break;
            case 1:
                imageID = R.drawable.number_1_green;
                break;
            case 2:
                imageID = R.drawable.number_2_green;
                break;
            case 3:
                imageID = R.drawable.number_3_green;
                break;
            case 4:
                imageID = R.drawable.number_4_green;
                break;
            case 5:
                imageID = R.drawable.number_5_green;
                break;
            case 6:
                imageID = R.drawable.number_6_green;
                break;
            case 7:
                imageID = R.drawable.number_7_green;
                break;
            case 8:
                imageID = R.drawable.number_8_green;
                break;
            case 9:
                imageID = R.drawable.number_9_green;
                break;
        }

        return imageID;
    }

    private int findRedDigitImageForInt(int digit) {
        int imageID;
        switch (digit) {
            default:
            case 0:
                imageID = R.drawable.number_0_red;
                break;
            case 1:
                imageID = R.drawable.number_1_red;
                break;
            case 2:
                imageID = R.drawable.number_2_red;
                break;
            case 3:
                imageID = R.drawable.number_3_red;
                break;
            case 4:
                imageID = R.drawable.number_4_red;
                break;
            case 5:
                imageID = R.drawable.number_5_red;
                break;
            case 6:
                imageID = R.drawable.number_6_red;
                break;
            case 7:
                imageID = R.drawable.number_7_red;
                break;
            case 8:
                imageID = R.drawable.number_8_red;
                break;
            case 9:
                imageID = R.drawable.number_9_red;
                break;
        }

        return imageID;
    }

    private float calculateAngleA() {
        float angle1 = MainActivity.getInstance().angle1;
        float angle2 = MainActivity.getInstance().angle2;

        return Math.abs(angle1 - angle2);
    }

    private float calculateAngleB() {
        float angle2 = MainActivity.getInstance().angle2;
        float angle3 = MainActivity.getInstance().angle3;

        return Math.abs(angle2 - angle3);
    }

    private float calculateOperatingAngle() {
        return Math.abs(calculateAngleA() - calculateAngleB());
    }

    private boolean angleAIsGood() {
        boolean isGood = false;
        float angleA = calculateAngleA();
        if (0.05 <= angleA && angleA <= 3.0) {
            isGood = true;
        }

        return isGood;
    }

    private boolean angleBIsGood() {
        boolean isGood = false;
        float angleB = calculateAngleB();
        if (0.05 <= angleB && angleB <= 3.0) {
            isGood = true;
        }

        return isGood;
    }

    private boolean operatingAngleIsGood() {
        boolean isGood = false;
        float measurement1 = MainActivity.getInstance().angle1;
        float measurement3 = MainActivity.getInstance().angle3;
        if ((measurement1 < 0 && measurement3 < 0) || (measurement1 > 0 && measurement3 > 0)) {
            if (angleAIsGood() && angleBIsGood()) {
                float operatingAngle = calculateOperatingAngle();
                if (0.05 <= operatingAngle && operatingAngle <= 2.0) {
                    isGood = true;
                }
            }
        }
        return  isGood;
    }

}
