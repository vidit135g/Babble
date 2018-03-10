package com.babble.chatMessenger;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SigninMinimal extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView signup;
    private TextView trouble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_minimal);
        pl.droidsonroids.gif.GifImageView imageView = (pl.droidsonroids.gif.GifImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.inboxm);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backmdpi);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");

        //Initialize the variables
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signup=(TextView)findViewById(R.id.signinlink);
        trouble=(TextView)findViewById(R.id.trouble);

        email.setTypeface(custom_font2);
        password.setTypeface(custom_font2);

        //set spannable string




    }
}
