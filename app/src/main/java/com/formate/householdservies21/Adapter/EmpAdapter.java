package com.formate.householdservies21.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.formate.householdservies21.Model.EmpHelperClass;
import com.formate.householdservies21.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.ImageViewHolder> {
    private Context mContext;
    private List<EmpHelperClass> mEmp;

    private FirebaseUser firebaseUser;

    public EmpAdapter(Context mContext, List<EmpHelperClass> mEmp){
        this.mContext = mContext;
        this.mEmp = mEmp;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.emp_item,viewGroup,false);
        return new EmpAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int i) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final EmpHelperClass empHelperClass = mEmp.get(i);

        holder.name_emp.setText(mEmp.get(i).getName());
        holder.phone_emp.setText(mEmp.get(i).getPhoneNo());
        Picasso.get()
                .load(mEmp.get(i).getImageurl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                //.centerCrop()
                .into(holder.image_profile_emp);

    }

    @Override
    public int getItemCount() {
        return mEmp.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image_profile_emp;
        TextView name_emp,phone_emp;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            image_profile_emp = itemView.findViewById(R.id.image_profile_emp);
            name_emp = itemView.findViewById(R.id.name_emp);
            phone_emp = itemView.findViewById(R.id.phone_emp);
        }
    }
}
