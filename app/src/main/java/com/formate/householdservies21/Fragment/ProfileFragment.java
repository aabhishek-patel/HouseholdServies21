package com.formate.householdservies21.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.formate.householdservies21.Model.UserHelperClass;
import com.formate.householdservies21.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Button logout;
    ImageView profile_image;
    TextView fullname_field , email_field;
    TextInputLayout full_name_profile,username_profile,phone_no_profile,password_profile;

    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    String profileid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid","none");

        profile_image = view.findViewById(R.id.profile_image);
        fullname_field = view.findViewById(R.id.fullname_field);
        email_field = view.findViewById(R.id.email_field);
        full_name_profile = view.findViewById(R.id.full_name_profile);
        username_profile = view.findViewById(R.id.username_profile);
        phone_no_profile = view.findViewById(R.id.phone_no_profile);
        password_profile = view.findViewById(R.id.password_profile);
        logout = view.findViewById(R.id.logout);

        userInfo();


        return view;
    }

    private void userInfo(){
        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Users").child(profileid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getContext() == null){
                    return;
                }

                UserHelperClass userHelperClass = dataSnapshot.getValue(UserHelperClass.class);

                Glide.with(getContext()).load(userHelperClass.getImageurl());
                fullname_field.setText(userHelperClass.getFullname());
                email_field.setText(userHelperClass.getEmail());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
