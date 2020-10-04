package com.formate.householdservies21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.formate.householdservies21.LoginSignUp.PhoneActivity;
import com.formate.householdservies21.LoginSignUp.SignINActivity;
import com.formate.householdservies21.LoginSignUp.SignUPActivity;

public class StartActivity extends AppCompatActivity {

    Button callSignUp,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        callSignUp = findViewById(R.id.callSignUp);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SignUPActivity.class);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SignINActivity.class);
                startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, PhoneActivity.class);
                startActivity(intent);
            }
        });

    }
}
