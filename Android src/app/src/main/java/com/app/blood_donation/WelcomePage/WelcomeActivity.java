package com.app.blood_donation.WelcomePage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.blood_donation.R;
import com.app.blood_donation.WelcomePage.RegistrationInfo.LoginActivity;
import com.app.blood_donation.WelcomePage.RegistrationInfo.SignupActivity;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import es.dmoral.toasty.Toasty;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imageView,imgwelcome;
    private Animation smalltobig, btta, btta2;
    private TextView textView, subtitle_header,subtitle_login,subtitle_signup,textViewwelcome;
    private Button buttonlogin,buttonsignup;

    LinearLayout layout;
    //for double back press to exit
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    ShimmerTextView tv;
    Shimmer shimmer;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        // load this animation
     /*   smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        btta = AnimationUtils.loadAnimation(this, R.anim.btta);
        btta2 = AnimationUtils.loadAnimation(this, R.anim.btta2);
*/
        imageView = findViewById(R.id.imageView);
        imgwelcome = findViewById(R.id.imageView2_welcome);

        //textView = findViewById(R.id.textView);
        textViewwelcome = findViewById(R.id.textView_welcome);
        subtitle_header = findViewById(R.id.subtitle_header);
        subtitle_login = findViewById(R.id.subtitle_login);
        subtitle_signup = findViewById(R.id.subtitle_signup);

        buttonlogin = findViewById(R.id.buttonlogin);
        buttonsignup = findViewById(R.id.buttonsignup);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Milkshake.ttf");
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.otf");
        //txtTitle.setTypeface(tf);
        textViewwelcome.setTypeface(tf);
        subtitle_header.setTypeface(tf1);
        subtitle_login.setTypeface(tf1);
        subtitle_signup.setTypeface(tf1);
        buttonsignup.setTypeface(tf1);
        buttonlogin.setTypeface(tf1);
      //  textView.setTypeface(tf1);

        /*tv = (ShimmerTextView) findViewById(R.id.textView_welcome);
        shimmer = new Shimmer();
        shimmer.start(tv);*/

        // passing animation and start it
       /* imageView.startAnimation(smalltobig);

        textView.startAnimation(btta);
        subtitle_header.startAnimation(btta);
        subtitle_login.startAnimation(btta);

        buttonlogin.startAnimation(btta2);
        subtitle_signup.startAnimation(btta);

        buttonsignup.startAnimation(btta2);
*/

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();

            finishAffinity();

        } else {
            Toasty.warning(this, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();

        }
        back_pressed = System.currentTimeMillis();
    }
}
