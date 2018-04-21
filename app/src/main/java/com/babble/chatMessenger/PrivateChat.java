package com.babble.chatMessenger;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.MultiContactPicker;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PrivateChat extends AppCompatActivity {

    private TextView signinLink;
    int currentPage = 0;
    int cpage;
    private Button connectButton=null;
    private static final int CONTACT_PICKER_REQUEST = 991;
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
        setContentView(R.layout.activity_private_chat);
        connectButton=(Button)findViewById(R.id.connect_button);
        signinLink=(TextView)findViewById(R.id.signinlink);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");

        //todo creating the alert dialog

        mViewPager=(com.babble.chatMessenger.NonSwipeableViewPager)findViewById(R.id.carouselImage);
        mViewPagerText=(com.babble.chatMessenger.NonSwipeableViewPager)findViewById(R.id.carouselText);

        int[] mResources = {
                R.drawable.privacy1,
                R.drawable.privacy2,
                R.drawable.privacy3,
                R.drawable.privacy4,
        };

        String[] mResourcesText={"Share your best ones only with the best ones!","Real time message transmission","Pattern Lock option","Strong chat encryption against breaches"};
        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, mResources);
        CustomPageAdapterText mCustomPageAdapterText=new CustomPageAdapterText(this,mResourcesText,custom_font1);


        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPagerText.setAdapter(mCustomPageAdapterText);


        //TODO Timer Image Usage
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4) {
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
                if (cpage == 4) {
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

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PrivateChat.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    new MultiContactPicker.Builder(PrivateChat.this) //Activity/fragment context
                            .hideScrollbar(false) //Optional - default: false
                            .showTrack(true) //Optional - default: true
                            .searchIconColor(Color.WHITE) //Optional - default: White
                            .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
                            .handleColor(ContextCompat.getColor(PrivateChat.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                            .bubbleColor(ContextCompat.getColor(PrivateChat.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                            .bubbleTextColor(Color.WHITE) //Optional - default: White
                            .showPickerForResult(CONTACT_PICKER_REQUEST);
                }else{
                    Toast.makeText(PrivateChat.this, "Remember to go into settings and enable the contacts permission.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                List<ContactResult> results = MultiContactPicker.obtainResult(data);
                //redirect the contact data to the secret chat window activity.
                Toast.makeText(PrivateChat.this, results.get(0).getDisplayName().toString(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                System.out.println("User closed the picker without selecting items.");
            }
        }
    }

}
