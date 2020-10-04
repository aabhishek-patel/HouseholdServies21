package com.formate.householdservies21.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.formate.householdservies21.R;
import com.formate.householdservies21.StartActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgetPhoneActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextView title,description;
    private CountryCodePicker countryCodePicker;
    private EditText phoneNo;
    private Button get_otp;
    RelativeLayout progressBar;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_phone);

        back_button = findViewById(R.id.back_button);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        phoneNo = findViewById(R.id.phoneNo);
        get_otp = findViewById(R.id.nextBtn);
        progressBar = findViewById(R.id.login_progess_bar);
    }

    public void callVerifyPhoneNumber(View view) {
        //Check Internet Connection
        if (!isConnected(this)){
            showCustomDailog();
        }

        progressBar.setVisibility(View.VISIBLE);

        //Get data from fields
        final String _phoneNumber = phoneNo.getText().toString().trim();
        if (TextUtils.isEmpty(_phoneNumber)){
            Toast.makeText(ForgetPhoneActivity.this, "Enter No...", Toast.LENGTH_SHORT).show();
        }
        else if (phoneNo.getText().toString().replace("","").length()!=10){
            Toast.makeText(ForgetPhoneActivity.this, "Enter Correct No..", Toast.LENGTH_SHORT).show();
        }
        final String _completePhoneNumber = "+"+ countryCodePicker.getFullNumber() + _phoneNumber;

        //check weather User exists or not
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("phoneNo").equalTo(_phoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //If phoneno exists then call otp to verifytaht it is phoneno
                if (dataSnapshot.exists()){
                    phoneNo.setError(null);
                    phoneNo.setEnabled(false);

                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("phoneNo",_phoneNumber);
                    intent.putExtra("whatToDo","updatedata");
                    startActivity(intent);
                    finish();

                    progressBar.setVisibility(View.GONE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    phoneNo.setError("No such user exists!");
                    phoneNo.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //to check internet connectivity
    private boolean isConnected(ForgetPhoneActivity forgetPhoneActivity) {
        ConnectivityManager connectivityManager  = (ConnectivityManager) forgetPhoneActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn !=null && mobileConn.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }

    private void showCustomDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPhoneActivity.this);
        builder.setMessage("Please connect to the Internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       startActivity(new Intent(getApplicationContext(), StartActivity.class));
                       finish();
                    }
                });
    }
}
