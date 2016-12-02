package cenkgun.com.android_ocr.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cenkgun.com.android_ocr.R;
import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(R.color.colorAccent)
                .withBeforeLogoText("Track Billing");

        //add custom font
        Typeface Roboto = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");

        config.getBeforeLogoTextView().setTypeface(Roboto);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextSize(35);

        //change text color

        //finally create the view
        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);

    }
}
