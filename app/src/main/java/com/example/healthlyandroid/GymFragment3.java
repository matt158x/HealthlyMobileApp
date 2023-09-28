package com.example.healthlyandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GymFragment3 extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_gym3, container, false);

            TextView textView = view.findViewById(R.id.gym_text_view);

            // Pobierz tekst na podstawie wyborów z GymActivity
            GymActivity gymActivity = (GymActivity) requireActivity();
            String textForFragment3 = gymActivity.generateTextForGymFragment3();

            // Ustaw tekst w TextView
            textView.setText(textForFragment3);

            return view;
        }
    }