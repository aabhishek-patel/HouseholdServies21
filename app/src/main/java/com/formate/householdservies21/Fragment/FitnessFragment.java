package com.formate.householdservies21.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.formate.householdservies21.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessFragment extends Fragment {


    public FitnessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fitness, container, false);
    }

}
