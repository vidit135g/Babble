package com.babble.chatMessenger;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.io.IOException;
import java.net.InetAddress;

import dmax.dialog.SpotsDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {
//todo declare the fields

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;
    private String otpreceived;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    private static final String TAG = "VerificationActivity";
    // [START declare_auth]
    private TextView resendotpview;
    private EditText otpbox;

    //todo end of declaration
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        final String code= getIntent().getStringExtra("CONTACT_CODE");
        final String number= getIntent().getStringExtra("CONTACT_NUMBER");
        resendotpview=findViewById(R.id.resend_code);
        otpbox=findViewById(R.id.otp);
        //TODO SET THE DIALOG
        /**/
        boolean connectstate=false;
        try {
            connectstate=isConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(connectstate){
            final AlertDialog dialog = new SpotsDialog(VerificationActivity.this, R.style.CustomDialog);
            dialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            }, 5000);

            mAuth = FirebaseAuth.getInstance();
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                    Log.d("", "onVerificationCompleted:" + credential);
                    signInWithPhoneAuthCredential(credential);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Log.w("", "onVerificationFailed", e);
                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        new MaterialDialog.Builder(VerificationActivity.this)
                                .title("Connection Error")
                                .iconRes(R.drawable.iconc2)
                                .content("Invalid Phone Number")
                                .contentColor(Color.BLACK)
                                .positiveText("OK")
                                .show();
                    } else if (e instanceof FirebaseTooManyRequestsException) {

                    }
                }

                @Override
                public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                    Log.d("", "onCodeSent:" + verificationId);
                    mVerificationId = verificationId;
                    mResendToken = token;
                }
            };
            getOtp(code+number);
            final AlertDialog dialogotp = new SpotsDialog(VerificationActivity.this, R.style.CustomDialog);
            dialogotp.show();
            dialogotp.dismiss();

            otpbox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String otp=s.toString();
                    if (TextUtils.isEmpty(otp)) {
                        otpbox.setError("Cannot be empty.");
                        return;
                    }
                    verifyPhoneNumberWithCode(mVerificationId, otp);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            resendotpview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resendVerificationCode(code+number, mResendToken);
                }
            });



        }
        else{

            new MaterialDialog.Builder(VerificationActivity.this)
                    .title("Connection Problem")
                    .content("Your are not connected to the internet.")
                    .contentColor(Color.BLACK)
                    .positiveText("DISMISS")
                    .show();
        }

        //TODO END OF DIALOG
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        TextView toolbarTitle=findViewById(R.id.toolbar_title);

        toolbarTitle.setText("Verify  "+code+" "+number);
        TextView verifyText=findViewById(R.id.verifyinst);
        TextView numberv=findViewById(R.id.inputNumber);
        TextView backAnchor=findViewById(R.id.registerlink);
        verifyText.setText("Waiting to automatically detect an SMS sent to");
        numberv.setText(code+" "+number+". ");

        backAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VerificationActivity.this,SigninMinimal.class);
                startActivity(intent);
            }
        });
    }
    //method to check internet connection
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConnected() throws InterruptedException, IOException
    {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec (command).waitFor() == 0);
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            otpbox.setVisibility(View.GONE);
                            //TODO DISPLAY MATERIAL DIALOG BEFORE REDIRECTION
                            final AlertDialog dialogsuccess = new SpotsDialog(VerificationActivity.this, R.style.CustomDialog2);
                            dialogsuccess.show();
                            dialogsuccess.dismiss();
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(VerificationActivity.this, PostLoginActivity.class).putExtra("phone", user.getPhoneNumber()));
                            finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                otpbox.setError("Invalid code.");
                            }
                        }
                    }
                });
    }
    private void getOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        otpreceived=code;
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }




}
