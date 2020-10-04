package com.formate.householdservies21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.formate.householdservies21.Fragment.CartFragment;
import com.formate.householdservies21.Fragment.ContactFragment;
import com.formate.householdservies21.Fragment.DashboardFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = DashboardActivity.class.getSimpleName();
    ChipNavigationBar bottonNav;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottonNav = findViewById(R.id.bottonNav);

        if (savedInstanceState==null){
            bottonNav.setItemSelected(R.id.bottom_nav_dashboard,true);
            fragmentManager = getSupportFragmentManager();
            DashboardFragment dashboardFragment = new DashboardFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,dashboardFragment)
                    .commit();
        }

        bottonNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.bottom_nav_dashboard:
                        fragment = new DashboardFragment();
                        break;
                    case R.id.bottom_nav_contact:
                        fragment = new ContactFragment();
                        break;
                    case R.id.bottom_nav_cart:
                        fragment = new CartFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .commit();
                }else {
                    Log.e(TAG,"Error in creatong fragment");
                }
            }
        });
    }
}
