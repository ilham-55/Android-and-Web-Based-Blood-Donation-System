package com.app.blood_donation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.app.blood_donation.WelcomePage.WelcomeActivity;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {
    int splashTimeOut = 2500;            //splash window time in mini secound
    GifImageView gifImageView;

    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        tv = (ShimmerTextView) findViewById(R.id.txt_title);

        Typeface tf = Typeface.createFromAsset(getAssets(), "AsapCondensed-Regular.ttf");

        tv.setTypeface(tf);

        shimmer = new Shimmer();
        shimmer.start(tv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, splashTimeOut);
    }

}
