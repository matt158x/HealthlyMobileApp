package com.example.healthlyandroid;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.healthlyandroid.GymActivity;
import com.example.healthlyandroid.R;

public class GymFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gym1, container, false);

        Button buttonChoice1 = view.findViewById(R.id.button1);
        Button buttonChoice2 = view.findViewById(R.id.button2);
        Button buttonChoice3 = view.findViewById(R.id.button3);

        buttonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zapisz wybór użytkownika w GymActivity
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice1(1);
                // Przełącz się na fragment GymFragment2
                gymActivity.switchToGymFragment2();

            }
        });

        buttonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zapisz wybór użytkownika w GymActivity
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice1(2);

                // Przełącz się na fragment GymFragment2
                gymActivity.switchToGymFragment2();

            }
        });

        buttonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zapisz wybór użytkownika w GymActivity
                GymActivity gymActivity = (GymActivity) requireActivity();
                gymActivity.setSelectedChoice1(3);

                // Przełącz się na fragment GymFragment2
                gymActivity.switchToGymFragment2();

            }
        });

        return view;
    }
}