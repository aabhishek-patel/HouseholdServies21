package com.formate.householdservies21.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.formate.householdservies21.Model.UserHelperClass;
import com.formate.householdservies21.R;
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
public class DashboardFragment extends Fragment {

    ImageView profile_image, housemaid_arro, housemaid_icon, tutor_arrow, tutor_icon, Plum_Ece_Car_arrow,
            Plum_Ece_Car_icon, imageprofile, imagetutor, imageelectrician, imagemaid, imagewater, imagemakeup,
            imageplumber, imagecarpenter, imagefitness, water_arrow, water_icon, makeup_arrow, makeup_icon,
            gym_arrow, gym_icon;
    TextView fullname, username, housemaid_text, housemaid_text_desc, tutor_text, tutor_text_desc,
            Plum_Ece_Car_text, Plum_Ece_Car_text_desc, textprofile, texttutor, textelectrician, textmaid,
            textwater, textmakeup, textplumber, textcarpenter, textfitness, water_text, water_text_desc,
            makeup_text, makeup_text_desc, gym_text, gym_text_desc;

    FirebaseUser firebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //hooks
        fullname = view.findViewById(R.id.fullname);
        username = view.findViewById(R.id.username);
        profile_image = view.findViewById(R.id.profile_image);
        imageprofile = view.findViewById(R.id.imageprofile);
        textprofile = view.findViewById(R.id.textprofile);

        imageelectrician = view.findViewById(R.id.imageelectrician);
        textelectrician = view.findViewById(R.id.textelectrician);

        imageplumber = view.findViewById(R.id.imageplumber);
        textplumber = view.findViewById(R.id.textplumber);

        imagecarpenter = view.findViewById(R.id.imagecarpenter);
        textcarpenter = view.findViewById(R.id.textcarpenter);

        Plum_Ece_Car_text = view.findViewById(R.id.Plum_Ece_Car_text);
        Plum_Ece_Car_text_desc = view.findViewById(R.id.Plum_Ece_Car_text_desc);
        Plum_Ece_Car_arrow = view.findViewById(R.id.Plum_Ece_Car_arrow);
        Plum_Ece_Car_icon = view.findViewById(R.id.Plum_Ece_Car_icon);


        housemaid_text = view.findViewById(R.id.housemaid_text);
        housemaid_text_desc = view.findViewById(R.id.housemaid_text_desc);
        housemaid_arro = view.findViewById(R.id.housemaid_arrow);
        housemaid_icon = view.findViewById(R.id.housemaid_icon);
        imagemaid = view.findViewById(R.id.imagemaid);
        textmaid = view.findViewById(R.id.textmaid);

        tutor_text = view.findViewById(R.id.tutor_text);
        tutor_text_desc = view.findViewById(R.id.tutor_text_desc);
        tutor_arrow = view.findViewById(R.id.tutor_arrow);
        tutor_icon = view.findViewById(R.id.tutor_icon);
        imagetutor = view.findViewById(R.id.imagetutor);
        texttutor = view.findViewById(R.id.texttutor);

        water_text = view.findViewById(R.id.water_text);
        water_text_desc = view.findViewById(R.id.water_text_desc);
        water_arrow = view.findViewById(R.id.water_arrow);
        water_icon = view.findViewById(R.id.water_icon);
        imagewater = view.findViewById(R.id.imagewater);
        textwater = view.findViewById(R.id.textwater);

        makeup_text = view.findViewById(R.id.makeup_text);
        makeup_text_desc = view.findViewById(R.id.makeup_text_desc);
        makeup_arrow = view.findViewById(R.id.makeup_arrow);
        makeup_icon = view.findViewById(R.id.makeup_icon);
        imagemakeup = view.findViewById(R.id.imagemakeup);
        textmakeup = view.findViewById(R.id.textmakeup);

        gym_text = view.findViewById(R.id.gym_text);
        gym_text_desc = view.findViewById(R.id.gym_text_desc);
        gym_arrow = view.findViewById(R.id.gym_arrow);
        gym_icon = view.findViewById(R.id.gym_icon);
        imagefitness = view.findViewById(R.id.imagefitness);
        textfitness = view.findViewById(R.id.textfitness);

        //userInfo();

        //profile
        /*profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = new ProfileFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFragment)
                        .addToBackStack(DashboardFragment.class.getSimpleName())
                        .commit();
            }
        });
        //profile
        textprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = new ProfileFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFragment)
                        .addToBackStack(DashboardFragment.class.getSimpleName())
                        .commit();
            }
        });
        //profile
        imageprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = new ProfileFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFragment)
                        .addToBackStack(DashboardFragment.class.getSimpleName())
                        .commit();
            }
        });

        //maid
        housemaid_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = new MaidFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFragment)
                        .addToBackStack(DashboardFragment.class.getSimpleName())
                        .commit();
            }
        });

        //maid
        imagemaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = new MaidFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFragment)
                        .addToBackStack(DashboardFragment.class.getSimpleName())
                        .commit();
            }
        });*/

        return view;
    }

    /*private void userInfo(){
        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Users").child(profileid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getContext() == null){
                    return;
                }

                UserHelperClass userHelperClass = dataSnapshot.getValue(UserHelperClass.class);

                //Glide.with(getContext()).load(userHelperClass.getImageurl());
                fullname.setText(userHelperClass.getFullname());
                username.setText(userHelperClass.getEmail());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

}
