package com.formate.householdservies21.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.formate.householdservies21.DashboardActivity;
import com.formate.householdservies21.Fragment.DashboardFragment;
import com.formate.householdservies21.Fragment.ProfileFragment;
import com.formate.householdservies21.Model.UserHelperClass;
import com.formate.householdservies21.ProfileActivity;
import com.formate.householdservies21.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PinActivity extends AppCompatActivity {

    ImageView back_button;
    Button get_area;
    Spinner spinner;
    //getDSpin
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        spinner = findViewById(R.id.myspinner);
        back_button = findViewById(R.id.back_button);
        spinner = findViewById(R.id.myspinner);
        get_area = findViewById(R.id.get_area);
        /*List<String> categories = new ArrayList<>();
        categories.add(0,"Choose your area...");*/

        databaseReference = FirebaseDatabase.getInstance().getReference("Areas");

        //getDSpin
        spinnerDataList = new ArrayList<>();
        spinnerDataList.add(0, "Select Area from the list");
        adapter = new ArrayAdapter<String>(PinActivity.this,
                R.layout.support_simple_spinner_dropdown_item,spinnerDataList);

        spinner.setAdapter(adapter);
        retrieveData();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                finish();
            }
        });
    }

    private void retrieveData() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot item:dataSnapshot.getChildren()){

                    spinnerDataList.add(item.getValue().toString());

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getArea(View view) {
        String area = spinner.getSelectedItem().toString();

       //UserHelperClass userHelperClass = new UserHelperClass(area);

        /*getSupportFragmentManager().beginTransaction()
                   .add(android.R.id.content, new DashboardFragment()).commit();
*/

    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
