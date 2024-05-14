package com.example.healthlyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class GoalActivity1 extends AppCompatActivity {

    private LinearLayout chooseGoalLayout;
    private Button loseWeightButton;
    private Button gainWeightButton;
    private Button maintainWeightButton;
    private Button nextButton;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal1);


        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();

        chooseGoalLayout = findViewById(R.id.choose_goal_layout);
        loseWeightButton = findViewById(R.id.lose_weight_button);
        gainWeightButton = findViewById(R.id.gain_weight_button);
        maintainWeightButton = findViewById(R.id.maintain_weight_button);
        nextButton = findViewById(R.id.next_button);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();



        loseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGoalButton(loseWeightButton);
            }
        });

        gainWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGoalButton(gainWeightButton);
            }
        });

        maintainWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGoalButton(maintainWeightButton);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedGoal = getSelectedGoal();
                if (selectedGoal != null) {
                    saveSelectedGoal(selectedGoal);
                    openGenderActivity();
                }
            }
        });
    }

    private void selectGoalButton(Button button) {
        loseWeightButton.setSelected(false);
        gainWeightButton.setSelected(false);
        maintainWeightButton.setSelected(false);
        button.setSelected(true);

        int greenColor = getResources().getColor(R.color.green); // Kolor zasobu z pliku colors.xml
        int grayColor = getResources().getColor(R.color.gray); // Kolor zasobu z pliku colors.xml
        loseWeightButton.setBackgroundColor(button == loseWeightButton ? greenColor : grayColor);
        gainWeightButton.setBackgroundColor(button == gainWeightButton ? greenColor : grayColor);
        maintainWeightButton.setBackgroundColor(button == maintainWeightButton ? greenColor : grayColor);
    }
    private String getSelectedGoal() {
        if (loseWeightButton.isSelected()) {
            return "LOSE WEIGHT";
        } else if (gainWeightButton.isSelected()) {
            return "GAIN WEIGHT";
        } else if (maintainWeightButton.isSelected()) {
            return "MAINTAIN WEIGHT";
        }
        return null;
    }

    private void saveSelectedGoal(String selectedGoal) {

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference.child("users").child(userId).child("goal").setValue(selectedGoal);
        }

    }

    private void openGenderActivity() {
        Intent intent = new Intent(GoalActivity1.this, GenderActivity2.class);
        startActivity(intent);
    }
}
