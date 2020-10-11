package com.formate.householdservies21.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.formate.householdservies21.DashboardActivity;
import com.formate.householdservies21.ProfileActivity;
import com.formate.householdservies21.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignINActivity extends AppCompatActivity {

    Button callSignUp, login_btn ,forgot_password;
    ImageView logoimage,back_btn;
    TextView logoText, sloganText;
    TextInputLayout email, password;

    FirebaseAuth auth;
    ProgressDialog pd;

    String get_fullname, get_username, get_email, get_password, get_phoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //hooks
        callSignUp = findViewById(R.id.callSignUp);
        login_btn = findViewById(R.id.login_btn);
        logoText = findViewById(R.id.logotext);
        sloganText = findViewById(R.id.slogantext);
        back_btn = findViewById(R.id.back_button);
        forgot_password = findViewById(R.id.forgot_password);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        auth = FirebaseAuth.getInstance();

        get_fullname = getIntent().getStringExtra("fullname");
        get_email = getIntent().getStringExtra("email");
        get_username = getIntent().getStringExtra("username");
        get_password = getIntent().getStringExtra("password");
        get_phoneNo = getIntent().getStringExtra("phoneNo");


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignINActivity.this,SignUPActivity.class));
                finish();
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(SignINActivity.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_email = email.getEditText().getText().toString();
                String str_password = password.getEditText().getText().toString();

                if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)) {
                    Toast.makeText(SignINActivity.this, "All fields are required.!", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(str_email, str_password)
                            .addOnCompleteListener(SignINActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(auth.getCurrentUser().getUid());

                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                pd.dismiss();
                                                Intent intent = new Intent(SignINActivity.this, PinActivity.class);
                                                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                //Pass all fields to the next activity
                                                intent.putExtra("fullname", get_fullname);
                                                intent.putExtra("username", get_username);
                                                intent.putExtra("email", get_email);
                                                intent.putExtra("phoneNo", get_phoneNo);
                                                intent.putExtra("password", get_password);
                                                startActivity(intent);
                                                //finish();



                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                pd.dismiss();

                                            }
                                        });
                                    } else {
                                        pd.dismiss();
                                        Toast.makeText(SignINActivity.this, "Authentication Failed.!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }



    public void callSignUpScreen(View view){

        Intent intent =  new Intent(SignINActivity.this, SignUPActivity.class);

        Pair[] pairs = new  Pair[7];

        pairs[0] = new Pair<View, String>(back_btn, "trans_back_arrow_btn");
        pairs[1] = new Pair<View,String>(logoText,"logo_name");
        pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
        pairs[3] = new Pair<View,String>(email,"email_tran");
        pairs[4] = new Pair<View,String>(password,"password_tran");
        pairs[5] = new Pair<View,String>(callSignUp,"button_tran");
        pairs[6] = new Pair<View,String>(login_btn,"login_signup_tran");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignINActivity.this,pairs);

            startActivity(intent, options.toBundle());
        }


    }

    public void callForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgetPhoneActivity.class));

    }
    //****onClick on  back button ****//*

    /*public void onBackPressed(){
        AlertDialog.Builder alertDailogBuilder=new AlertDialog.Builder(this);
        alertDailogBuilder.setTitle("Confirm Exit...!!!");

        alertDailogBuilder.setIcon(R.drawable.exit);

        alertDailogBuilder.setMessage("Are you sure want to EXIT");

        alertDailogBuilder.setCancelable(false);

        alertDailogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDailogBuilder.setNegativeButton("Login First", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SignINActivity.this,"You clicked on Login First",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog =alertDailogBuilder.create();
        alertDialog.show();*/
    }




