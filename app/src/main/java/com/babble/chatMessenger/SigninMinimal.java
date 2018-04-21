package com.babble.chatMessenger;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.google.firebase.auth.PhoneAuthProvider;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.OnCountryPickerListener;

import java.util.concurrent.TimeUnit;
import io.reactivex.annotations.NonNull;

public class SigninMinimal extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 0;
    private static final int MY_PERMISSIONS_REQUEST_READ_STORAGE_AND_CONTACT = 1;
    private static int sms_permission_state = 0;
    private EditText email=null;
    private  EditText  code=null;
    private TextView verifyInstructions=null;
    private MaterialSpinner spinner=null;
    private Button signin;
    private TextView backup=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_minimal);
        //todo setup media, storage and contacts permissions

        if ((ContextCompat.checkSelfPermission(SigninMinimal.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) &&  (ContextCompat.checkSelfPermission(SigninMinimal.this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)){


            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(SigninMinimal.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_STORAGE_AND_CONTACT);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backmdpi);
        verifyInstructions=(TextView)findViewById(R.id.verifyinst);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/fbold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");

        //Initialize the variables
        email=(EditText)findViewById(R.id.email);
        backup=(TextView)findViewById(R.id.backupinfo);
        spinner=(MaterialSpinner)findViewById(R.id.countrypicker);
        signin=(Button)findViewById(R.id.login_sign_in_button);
        code=(EditText)findViewById(R.id.code);
        email.setTypeface(custom_font2);
        backup.setTypeface(custom_font2);
        email.setSelected(false);
        verifyInstructions.setTypeface(custom_font2);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValidMobile(code.getText().toString()+email.getText().toString())) {
                    new MaterialDialog.Builder(SigninMinimal.this)
                            .title("Hang in there, we will be verifying the phone number:")
                            .content(email.getText())
                            .contentColor(Color.BLACK)
                            .positiveText("OK")
                            .negativeText("EDIT")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    // TODO Show Connecting dialog and Redirect to next page
                                    Intent intent = new Intent(SigninMinimal.this, VerificationActivity.class);
                                    intent.putExtra("CONTACT_NUMBER", email.getText().toString());
                                    intent.putExtra("CONTACT_CODE", code.getText().toString());
                                    startActivity(intent);
                                }
                            }).onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            // TODO focus on the editext
                            email.requestFocus();
                        }
                    })
                            .show();
                }
                else{
                    email.setError(Html.fromHtml("<font color='#2196f3'>Enter the phone number</font>"));
                }



                if ((ContextCompat.checkSelfPermission(SigninMinimal.this,
                        android.Manifest.permission.READ_SMS)
                        != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(SigninMinimal.this,
                        android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)){

                    
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(SigninMinimal.this,
                                new String[]{Manifest.permission.READ_SMS},
                                MY_PERMISSIONS_REQUEST_READ_SMS);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.

                    }



                

            }
        });
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountryPicker countryPicker =
                        new CountryPicker.Builder().with(SigninMinimal.this)
                                .listener(new OnCountryPickerListener() {
                                    @Override public void onSelectCountry(Country country) {
                                        //DO something here
                                        String name = country.getName();
                                        String countryCode=country.getCode();
                                        String dialCode = country.getDialCode();
                                        if(name=="")
                                        {
                                            spinner.setError("SELECT THE COUNTRY FIRST");
                                        }
                                        else {
                                            code.setText(dialCode);
                                            code.clearFocus();
                                            spinner.setText(name);
                                            spinner.setTextColor(Color.BLACK);
                                        }
                                    }
                                })
                                .build();


                countryPicker.showDialog(getSupportFragmentManager());
            }
        });



    }

    
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    sms_permission_state=1;


                } else {

                    sms_permission_state=0;

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_READ_STORAGE_AND_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED &&  grantResults[1] == PackageManager.PERMISSION_GRANTED) {


                } else {


                }
                return;
            }

        }
        
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    
    
}
