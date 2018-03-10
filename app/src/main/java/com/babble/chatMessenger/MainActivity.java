package com.babble.chatMessenger;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {

    private TextView appName;
    private TextView appQuote;
    private TextView noAccount;
    private TextView signinLink;
    private TextView serv1;
    private TextView serv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appName=(TextView)findViewById(R.id.appname);
        appQuote=(TextView)findViewById(R.id.appquote);
        noAccount=(TextView)findViewById(R.id.noAccount);
        signinLink=(TextView)findViewById(R.id.signinlink);
        serv1=(TextView)findViewById(R.id.servtext1);
        serv2=(TextView)findViewById(R.id.servtext2);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");
        appName.setTypeface(custom_font1);
        appQuote.setTypeface(custom_font2);
        noAccount.setTypeface(custom_font2);
        signinLink.setTypeface(custom_font1);
        serv1.setTypeface(custom_font2);

        //TODO Adding Google Singin functionality


        //TODO Redirect to login

        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}
