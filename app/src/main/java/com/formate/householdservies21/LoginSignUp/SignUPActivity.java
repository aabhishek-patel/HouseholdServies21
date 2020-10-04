package com.formate.householdservies21.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.ui.phone.PhoneActivity;
import com.formate.householdservies21.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUPActivity extends AppCompatActivity {

    //variables
    TextInputLayout regName, regUsername, regEmail, regPassword , regPhoneNo;
    Button next, regTolognBtn;
    TextView logoText, sloganText;
    ImageView backbutton;

    FirebaseAuth auth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hooks
        logoText = findViewById(R.id.logotext);
        sloganText = findViewById(R.id.slogantext);
        regName = findViewById(R.id.regName);
        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regPhoneNo = findViewById(R.id.regPhoneNo);
        regPassword = findViewById(R.id.regPassword);
        next = findViewById(R.id.regBtn);
        regTolognBtn = findViewById(R.id.regTolognBtn);
        backbutton = findViewById(R.id.back_button);


        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String passwordPattern = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 4 characters
                "$";


        auth = FirebaseAuth.getInstance();

        regTolognBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUPActivity.this, SignINActivity.class));
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUPActivity.this, SignINActivity.class));
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(SignUPActivity.this);
                pd.setMessage("Please Wait...");
                pd.show();

                //get all the values
                String str_fullname = regName.getEditText().getText().toString();
                String str_username = regUsername.getEditText().getText().toString();
                String str_email = regEmail.getEditText().getText().toString();
                String str_phoneNo = regPhoneNo.getEditText().getText().toString();
                String str_password = regPassword.getEditText().getText().toString();


                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_fullname) ||
                        TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_phoneNo) || TextUtils.isEmpty(str_password)) {
                    Toast.makeText(SignUPActivity.this, "All fileds are required.!", Toast.LENGTH_SHORT).show();

                } else if (str_password.length() < 6) {
                    Toast.makeText(SignUPActivity.this, "Password must have 6 characters.", Toast.LENGTH_SHORT).show();
                } else if (str_password.matches(passwordPattern)) {
                    Toast.makeText(getApplicationContext(), "Valid email", Toast.LENGTH_SHORT).show();
                } else if (!str_email.matches(emailPattern)){
                    Toast.makeText(SignUPActivity.this, "enter valid email", Toast.LENGTH_SHORT).show();
                } else if (str_phoneNo.length()>=10){
                        regBtn(str_username, str_fullname, str_email, str_phoneNo,str_password);

                    }
                }

        });
    }

    private void regBtn(final String regUsername, final String regName, final String regEmail,final String regPhoneNo, final String regPassword) {
        auth.createUserWithEmailAndPassword(regEmail, regPassword)
                .addOnCompleteListener(SignUPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = regUsername;


                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", regUsername.toLowerCase());
                            hashMap.put("fullname", regName);
                            hashMap.put("email", regEmail);
                            hashMap.put("password", regPassword);
                            hashMap.put("phoneNo", regPhoneNo);
                            hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/householdservies21.appspot.com/o/placeholder.png?alt=media&token=4ea9734d-d2e6-460e-aecf-f5e0e15dd44b");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        pd.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), com.formate.householdservies21.LoginSignUp.PhoneActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                        //Pass all fields to the next activity
                                        intent.putExtra("fullname", regName);
                                        intent.putExtra("username", regUsername);
                                        intent.putExtra("email", regEmail);
                                        intent.putExtra("phoneNo", regPhoneNo);
                                        intent.putExtra("password", regPassword);

                                    }
                                }
                            });
                        } else {
                            pd.dismiss();
                            Toast.makeText(SignUPActivity.this, "You can't register with this email and password", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void callNextSignupScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);

        //Add Shared animation
        Pair[] pairs = new Pair[11];

        pairs[0] = new Pair<View, String>(backbutton, "trans_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "trans_next_btn");
        pairs[2] = new Pair<View,String>(logoText,"logo_name");
        pairs[3] = new Pair<View,String>(sloganText,"logo_desc");
        pairs[4] = new Pair<View,String>(regName,"fullname_tran");
        pairs[5] = new Pair<View,String>(regUsername,"username_tran");
        pairs[6] = new Pair<View,String>(regEmail,"email_tran");
        pairs[7] = new Pair<View,String>(regPhoneNo,"phone_tran");
        pairs[8] = new Pair<View,String>(regPassword,"password_tran");
        pairs[9] = new Pair<View,String>(next,"button_tran");
        pairs[10] = new Pair<View, String>(regTolognBtn, "login_signup_tran");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUPActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }


}

