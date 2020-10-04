package com.formate.householdservies21.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.formate.householdservies21.R;
import com.hbb20.CountryCodePicker;

public class PhoneActivity extends AppCompatActivity {

    //Varables
    ScrollView scrollView;
    EditText phoneNo;
    CountryCodePicker countryCodePicker;
    Button get_otp;

    String fullname, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_phone);

        //Hooks
        scrollView = findViewById(R.id.scrollview);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        phoneNo = findViewById(R.id.regPhoneNo);
        get_otp = findViewById(R.id.get_otp);
        countryCodePicker.registerCarrierNumberEditText(phoneNo);

        //Get all values passed from previous screens using Intent
        fullname = getIntent().getStringExtra("fullname");
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_getUserPhoneNumber = phoneNo.getText().toString().trim();//Get Phone Number
                String str_phoneNo = "+"+countryCodePicker.getFullNumber()+str_getUserPhoneNumber;
                if (TextUtils.isEmpty(str_phoneNo)){
                    Toast.makeText(PhoneActivity.this, "Enter No...", Toast.LENGTH_SHORT).show();
                }
                else if (phoneNo.getText().toString().replace("","").length()!=10){
                    Toast.makeText(PhoneActivity.this, "Enter Correct No..", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("phoneNo",countryCodePicker.getFullNumberWithPlus().replace("",""));
                    startActivity(intent);
                    finish();

                    //Pass all fields to the next activity
                    intent.putExtra("fullname", fullname);
                    intent.putExtra("username", username);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                }


            }

        });

    }

    /*private Boolean validatePhoneNo(){
        String val =regPhoneNo.getEditText().getText().toString();
        String noWhiteSpace = "(?=\\s+$)";

        if (val.isEmpty()){
            regPhoneNo.setError("field cannot be empty");
            return false;
        }
        else if (val.length() >= 11){
            regPhoneNo.setError("Phone Cannot be more than 10 digit");
            return false;
        }
        *//*else if (!val.matches(noWhiteSpace)){
                regPhoneNo.setError("White Spaces are not allowed");
                return false;
        }*//*
        else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    public void callVerifyOTPScreen(View view){
        //Validate fields
        if (!validatePhoneNo()){
            return;
        }//Validation succeeded and now move to next screen to verify phone number and save data

        //Get all values passed from previous screens using Intent
        String str_fullname = getIntent().getStringExtra("fullname");
        String str_username = getIntent().getStringExtra("username");
        String str_email = getIntent().getStringExtra("email");
        String str_password = getIntent().getStringExtra("password");

        String str_getUserPhoneNumber = regPhoneNo.getEditText().getText().toString().trim();//Get Phone Number
        String str_phoneNo = "+"+countryCodePicker.getFullNumber()+str_getUserPhoneNumber;

        Intent intent = new Intent(getApplicationContext(), OtpActivity.class);

        //Pass all fields to the next activity
        intent.putExtra("fullname", str_fullname);
        intent.putExtra("username", str_username);
        intent.putExtra("email", str_email);
        intent.putExtra("password", str_password);
        intent.putExtra("phoneNo", str_phoneNo);

        //Add Transition
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(scrollView, "transition_OTP_screen");
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP);
    }*/
}
