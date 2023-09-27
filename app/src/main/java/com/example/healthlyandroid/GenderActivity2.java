package com.example.healthlyandroid;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class GenderActivity2 extends AppCompatActivity {

    private Button maleButton;
    private Button femaleButton;
    private Button nextButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender2);


        // Ustaw pasek stanu na transparentny
        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();


        maleButton = findViewById(R.id.male_button);
        femaleButton = findViewById(R.id.female_button);
        nextButton = findViewById(R.id.next_button1);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGenderButton(maleButton);
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGenderButton(femaleButton);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedGender = getSelectedGender();
                if (selectedGender != null) {
                    saveSelectedGender(selectedGender);
                    openDateActivity();
                }
            }
        });
    }

    private void selectGenderButton(Button button) {
        maleButton.setSelected(false);
        femaleButton.setSelected(false);
        button.setSelected(true);
        // Zmień kolor tła przycisku na zielony dla wybranego przycisku
        int greenColor = getResources().getColor(R.color.green); // Kolor zasobu z pliku colors.xml
        int grayColor = getResources().getColor(R.color.gray); // Kolor zasobu z pliku colors.xml
        maleButton.setBackgroundColor(button == maleButton ? greenColor : grayColor);
        femaleButton.setBackgroundColor(button == femaleButton ? greenColor : grayColor);

    }

    private String getSelectedGender() {
        if (maleButton.isSelected()) {
            return "Male";
        } else if (femaleButton.isSelected()) {
            return "Female";
        }
        return null;
    }

    private void saveSelectedGender(String selectedGender) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference.child("users").child(userId).child("gender").setValue(selectedGender);
        }
    }

    private void openDateActivity() {
        Intent intent = new Intent(GenderActivity2.this, DateActivity3.class);
        startActivity(intent);
    }
}