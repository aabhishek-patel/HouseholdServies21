package com.formate.householdservies21.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.formate.householdservies21.R;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextView title,description;
    private TextInputLayout email;
    private Button nextBtn;
    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        back_button = findViewById(R.id.back_button);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        email = findViewById(R.id.email);
        nextBtn = findViewById(R.id.nextBtn);
        progressBar = findViewById(R.id.login_progess_bar);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this,SignINActivity.class));
                finish();
            }
        });
    }
}
