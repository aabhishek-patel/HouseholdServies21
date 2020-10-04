package com.formate.householdservies21.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.formate.householdservies21.Model.UserHelperClass;
import com.formate.householdservies21.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.concurrent.TimeUnit;

public class ForgetOtpActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    String fullname, username, email, password, phoneNo, otp_id,whatToDo;
    //Variables
    private Button verify_btn;
    private PinView otp;
    private TextView resend;

    private FirebaseAuth mAuth;

    /*String codeBySystem;*/
    private MKLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_otp);

        //hooks
        verify_btn = findViewById(R.id.verify_btn);
        otp = findViewById(R.id.code_verification);
        resend = findViewById(R.id.resend);
        loader = findViewById(R.id.loader);

        mAuth = firebaseAuth.getInstance();


        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(otp.getText().toString())) {
                    Toast.makeText(ForgetOtpActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else if (otp.getText().toString().replace("", "").length() != 6) {
                    Toast.makeText(ForgetOtpActivity.this, "Entwer Right OTP", Toast.LENGTH_SHORT).show();
                } else {
                    loader.setVisibility(View.VISIBLE);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otp_id, otp.getText().toString().replace("", ""));
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        fullname = getIntent().getStringExtra("fullname");
        email = getIntent().getStringExtra("email");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        phoneNo = getIntent().getStringExtra("phoneNo");
        whatToDo = getIntent().getStringExtra("whatToDo");
        sendVerificationCode();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });

        /*sendVerificationCodeToUser(phoneNo);*/
    }
    private void sendVerificationCode() {

        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                resend.setText("" + l / 1000);
                resend.setEnabled(false);
            }

            @Override
            public void onFinish() {
                resend.setText("Resend");
                resend.setEnabled(true);
            }
        }.start();


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(String otp_id, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        ForgetOtpActivity.this.otp_id = otp_id;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(ForgetOtpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });        // OnVerificationStateChangedCallbacks

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loader.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                            DatabaseReference reference = rootNode.getReference("Users").child(userid);

                            UserHelperClass addNewUser = new UserHelperClass(fullname, username, email, password, phoneNo);
                            reference.child(userid).setValue(addNewUser);
                            //FirebaseUser user = task.getResult().getUser();

                            Intent intent = new Intent(getApplicationContext(), SetNewPasswordActivity.class);
                            startActivity(intent);
                            finish();
                            // ...
                        } else {
                            Toast.makeText(ForgetOtpActivity.this, "Verificatio Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
