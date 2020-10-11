package com.formate.householdservies21.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.formate.householdservies21.Adapter.EmpAdapter;
import com.formate.householdservies21.Adapter.UserAdapter;
import com.formate.householdservies21.Model.EmpHelperClass;
import com.formate.householdservies21.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaidFragment extends Fragment {

    RecyclerView recyclerView;
    EmpAdapter empAdapter;
    List<EmpHelperClass> mEmp;

    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maid, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        mEmp = new ArrayList<EmpHelperClass>();

        /*userAdapter = new UserAdapter(getContext(), mUsers);
        recyclerView.setAdapter(userAdapter);

        readUsers();*/

        reference = FirebaseDatabase.getInstance().getReference().child("Employee");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    EmpHelperClass empHelperClass = dataSnapshot1.getValue(EmpHelperClass.class);
                    mEmp.add(empHelperClass);
                }

                empAdapter = new EmpAdapter(getContext(),mEmp);
                recyclerView.setAdapter(empAdapter);
                //progress_bar.setVisibility(View.INVISIBLE);
                empAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Opsss... Somthing is wrong.", Toast.LENGTH_SHORT).show();
                //progress_bar.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }


    /*private void readUsers() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EmpHelperClass empHelperClass = snapshot.getValue(EmpHelperClass.class);
                    mUsers.add(empHelperClass);
                }

                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}