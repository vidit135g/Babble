package com.babble.chatMessenger;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class LoginActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private FirebaseAuth mAuth;
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mSigninButton;
    //private Button mSignupButton;
    private  TextView mService1;
    private  TextView mService2;
    private  TextView appTitle;
    private  TextView desco;
    private  TextView desct;
    private  TextView descth;
    private TextView acc1;
    private TextView acc2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        mEmailView = (EditText) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        mSigninButton=(Button)findViewById(R.id.login_sign_in_button);
        //mSignupButton=(Button)findViewById(R.id.login_register_button);
        mService1=(TextView)findViewById(R.id.servtext1);
        mService2=(TextView)findViewById(R.id.servtext2);
        desco=(TextView)findViewById(R.id.appdesco);
        desct=(TextView)findViewById(R.id.appdesct);
        descth=(TextView)findViewById(R.id.appdescth);
        appTitle=(TextView)findViewById(R.id.apphead);
        acc1=(TextView)findViewById(R.id.acc1);
        acc2=(TextView)findViewById(R.id.acc2);
        //TODO: Set the font to the textviewa and buttons
        mEmailView.setTypeface(custom_font);
        mPasswordView.setTypeface(custom_font);
        //mSignupButton.setTypeface(custom_font);
        mSigninButton.setTypeface(custom_font2);
        mService1.setTypeface(custom_font);
        mService2.setTypeface(custom_font);
        acc1.setTypeface(custom_font);
        acc2.setTypeface(custom_font2);

        acc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth
        mAuth=FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        // TODO: Call attemptLogin() here
        attemptLogin();
    }

    // Executed when Register button pressed
    /*public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.babble.chatMessenger.RegisterActivity.class);
        finish();
        startActivity(intent);
    }*/

    // TODO: Complete the attemptLogin() method
    private void attemptLogin() {

        String email=mEmailView.getText().toString();
        String password=mPasswordView.getText().toString();
        if (email.equals("") || password.equals(""))
            return;
        Toast.makeText(this,"Login in progress",Toast.LENGTH_SHORT)
                .show();

        // TODO: Use FirebaseAuth to sign in with email & password
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()){
                    Log.d("Babble","Error"+task.getException());
                    showErrorDialog("Signin failed");
                }else{
                    Intent intent=new Intent(LoginActivity.this,MainChatActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    // TODO: Show error on screen with an alert dialog
    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}