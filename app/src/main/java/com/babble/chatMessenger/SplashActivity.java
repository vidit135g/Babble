package com.babble.chatMessenger;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private TextView signinLink;
    private Button loginButton;
    int currentPage = 0;
    int cpage;
    Timer timerText;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    //TODO Setting up ActionBar


    private com.babble.chatMessenger.NonSwipeableViewPager mViewPager;
    private com.babble.chatMessenger.NonSwipeableViewPager mViewPagerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        signinLink=(TextView)findViewById(R.id.signinlink);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        mViewPager=(com.babble.chatMessenger.NonSwipeableViewPager)findViewById(R.id.carouselImage);
        mViewPagerText=(com.babble.chatMessenger.NonSwipeableViewPager)findViewById(R.id.carouselText);
        loginButton=(Button)findViewById(R.id.login_sign_in_button);
// This is just an example. You can use whatever collection of images.
        int[] mResources = {
                R.drawable.themec,
                R.drawable.dndc,
                R.drawable.seriousc,
                R.drawable.bulbc,
                R.drawable.snapc,
                R.drawable.locationc
        };

        String[] mResourcesText={"Switch up themes anytime","Real Time Notifications","Collaborate remotely","Share your ideas anytime","Send pictures and videos","Share your location with others"};
        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, mResources);
        CustomPageAdapterText mCustomPageAdapterText=new CustomPageAdapterText(this,mResourcesText,custom_font1);


        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPagerText.setAdapter(mCustomPageAdapterText);


        //TODO Timer Image Usage
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 6) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        //TODO Timer Text Usage
        final Handler handlerT = new Handler();
        final Runnable UpdateT = new Runnable() {
            public void run() {
                if (cpage == 6) {
                    cpage = 0;
                }
                mViewPagerText.setCurrentItem(cpage++, true);
            }
        };

        timerText = new Timer(); // This will create a new Thread
        timerText .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handlerT.post(UpdateT);
            }
        }, DELAY_MS, PERIOD_MS);


        //register intent

        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplashActivity.this,SigninMinimal.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplashActivity.this,SignupMinimal.class);
                startActivity(intent);

            }
        });
    }
}
