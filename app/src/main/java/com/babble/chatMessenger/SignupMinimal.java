package com.babble.chatMessenger;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupMinimal extends AppCompatActivity {

    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText password;
    private Button signupBut;
    private TextView trouble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_minimal);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backmdpi);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");

        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signupBut=(Button)findViewById(R.id.login_sign_in_button);
        trouble=(TextView)findViewById(R.id.signinlink);

        //set typeface
        trouble.setTypeface(custom_font1);
        fname.setTypeface(custom_font2);
        lname.setTypeface(custom_font2);
        email.setTypeface(custom_font2);
        password.setTypeface(custom_font2);
        signupBut.setTypeface(custom_font1);
    }
}
