package com.example.healthlyandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class GymFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gym2, container, false);

        Button buttonChoice4 = view.findViewById(R.id.button_choice4);
        Button buttonChoice5 = view.findViewById(R.id.button_choice5);
        Button buttonChoice6 = view.findViewById(R.id.button_choice6);
        Button buttonChoice7 = view.findViewById(R.id.button_choice7);

        buttonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice2(4);

                gymActivity.switchToGymFragment3();

            }
        });

        buttonChoice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice2(5);

                gymActivity.switchToGymFragment3();

            }
        });

        buttonChoice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice2(6);

                gymActivity.switchToGymFragment3();

            }
        });

        buttonChoice7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice2(7);

                gymActivity.switchToGymFragment3();

            }
        });

        return view;
    }
}