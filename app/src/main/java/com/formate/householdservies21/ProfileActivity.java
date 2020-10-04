package com.formate.householdservies21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.formate.householdservies21.LoginSignUp.SignUPActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    Button logout;
    ImageView profile_image;
    TextView fullname_field , email_field;
    TextInputLayout full_name_profile,username_profile,phone_no_profile,password_profile;

    DatabaseReference reff;
    FirebaseAuth auth;
    FirebaseUser user;
    String profileid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*SharedPreferences prefs = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid","none");*/

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        profile_image = findViewById(R.id.profile_image);
        fullname_field = findViewById(R.id.fullname_field);
        email_field = findViewById(R.id.email_field);
        full_name_profile = findViewById(R.id.full_name_profile);
        username_profile = findViewById(R.id.username_profile);
        phone_no_profile = findViewById(R.id.phone_no_profile);
        password_profile = findViewById(R.id.password_profile);
        logout = findViewById(R.id.logout);


        //Show All Data
        showAllUserData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, SignUPActivity.class));
                finish();
            }
        });
    }

    private void showAllUserData() {

        reff = FirebaseDatabase.getInstance().getReference().child("Users").child(profileid);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullname = dataSnapshot.child("fullname").getValue().toString();
                String username = dataSnapshot.child("username").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String phoneNo = dataSnapshot.child("phoneNo").getValue().toString();
                String password = dataSnapshot.child("password").getValue().toString();

                fullname_field.setText(fullname);
                email_field.setText(email);
                /*full_name_profile.getEditText().setText(fullname);
                username_profile.getEditText().setText(username);
                phone_no_profile.getEditText().setText(phoneNo);
                password_profile.getEditText().setText(password);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //*Intent intent = getIntent();
       /* String fullname = intent.getStringExtra("fullname");
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        String phoneNo = getIntent().getStringExtra("phoneNo");
        String password = getIntent().getStringExtra("password");


        fullname_field.setText(fullname);
        email_field.setText(email);
        full_name_profile.getEditText().setText(fullname);
        username_profile.getEditText().setText(username);
        phone_no_profile.getEditText().setText(phoneNo);
        password_profile.getEditText().setText(password);*/
    }
}
