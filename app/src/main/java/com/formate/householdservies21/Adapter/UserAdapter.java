package com.formate.householdservies21.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.formate.householdservies21.Model.UserHelperClass;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.formate.householdservies21.Fragment.ProfileFragment;
import com.formate.householdservies21.Model.EmpHelperClass;
import com.formate.householdservies21.R;
import com.squareup.picasso.Picasso;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder>{

    private Context mContext;
    private List<UserHelperClass> mUsers;

    private FirebaseUser firebaseUser;

    public UserAdapter(Context mContext, List<UserHelperClass> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_profile,viewGroup,false);
        return new UserAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final UserHelperClass userHelperClass = mUsers.get(i);


        /*viewHolder.fullname_field.setText(userHelperClass.getFullname());
        viewHolder.email_field.setText(userHelperClass.getEmail());*/
        viewHolder.full_name_profile.getEditText().setText(userHelperClass.getFullname());
        viewHolder.username_profile.getEditText().setText(userHelperClass.getUsername());
        viewHolder.phone_no_profile.getEditText().setText(userHelperClass.getPhoneNo());
        viewHolder.password_profile.getEditText().setText(userHelperClass.getPassword());

        Picasso.get()
                .load(mUsers.get(i).getImageurl())
                .placeholder(R.drawable.boy)
                .fit()
               // .centerCrop()
                .into(viewHolder.profile_image);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        //public TextView fullname_field,email_field;
        public TextInputLayout full_name_profile,username_profile,
                phone_no_profile,password_profile;
        public ImageView profile_image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            //fullname_field = itemView.findViewById(R.id.fullname_field);
            //email_field = itemView.findViewById(R.id.email_field);
            full_name_profile = itemView.findViewById(R.id.full_name_profile);
            username_profile = itemView.findViewById(R.id.username_profile);
            phone_no_profile = itemView.findViewById(R.id.phone_no_profile);
            password_profile = itemView.findViewById(R.id.password_profile);
        }
    }

}
