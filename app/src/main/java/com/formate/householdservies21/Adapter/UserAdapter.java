package com.formate.householdservies21.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.formate.householdservies21.Fragment.ProfileFragment;
import com.formate.householdservies21.Model.EmpHelperClass;
import com.formate.householdservies21.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder>{

    private Context mContext;
    private List<EmpHelperClass> mUsers;

    private FirebaseUser firebaseUser;

    public UserAdapter(Context mContext, List<EmpHelperClass> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,viewGroup,false);
        return new UserAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final EmpHelperClass empHelperClass = mUsers.get(i);


        viewHolder.fullname_emp.setText(empHelperClass.getName());
        viewHolder.phoneNo_emp.setText(empHelperClass.getPhoneNo());

        Glide.with(mContext).load(empHelperClass.getImageurl()).into(viewHolder.image_profile_emp);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
                editor.putString("profileid", empHelperClass.getPhoneNo());
                editor.apply();



                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        public TextView phoneNo_emp;
        public TextView fullname_emp;
        public CircleImageView image_profile_emp;
        public ImageView about_icon_emp;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            phoneNo_emp = itemView.findViewById(R.id.phone_emp);
            fullname_emp = itemView.findViewById(R.id.fullname_emp);
            image_profile_emp = itemView.findViewById(R.id.image_profile_emp);
            about_icon_emp = itemView.findViewById(R.id.about_icon_emp);
        }
    }

}
