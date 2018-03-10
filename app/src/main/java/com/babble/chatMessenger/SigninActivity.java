package com.babble.chatMessenger;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private TextView appName;
    private TextView appQuote;
    private TextView noAccount;
    private TextView signupLink;
    private TextView serv1;
    private TextView serv2;
    private ImageButton email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Initialization
        appName=(TextView)findViewById(R.id.appname);
        appQuote=(TextView)findViewById(R.id.appquote);
        noAccount=(TextView)findViewById(R.id.noAccount);
        signupLink=(TextView)findViewById(R.id.signuplink);
        serv1=(TextView)findViewById(R.id.servtext1);
        serv2=(TextView)findViewById(R.id.servtext2);
        email=(ImageButton)findViewById(R.id.emailogin);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");
        appName.setTypeface(custom_font1);
        appQuote.setTypeface(custom_font2);
        noAccount.setTypeface(custom_font2);
        signupLink.setTypeface(custom_font1);
        serv1.setTypeface(custom_font2);
        serv2.setTypeface(custom_font2);

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SigninActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
