package com.example.healthlyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.healthlyandroid.HomeActivity;
import com.example.healthlyandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class GoalWeightActivity extends AppCompatActivity {

    private NumberPicker goalWeightPicker;
    private NumberPicker activityLevelPicker;
    private Button nextButton;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight);

        goalWeightPicker = findViewById(R.id.goal_weight);
        activityLevelPicker = findViewById(R.id.activity_level_picker);
        nextButton = findViewById(R.id.next_button4);

        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();

        goalWeightPicker.setMinValue(0);
        goalWeightPicker.setMaxValue(200);
        goalWeightPicker.setValue(70);

        activityLevelPicker.setMinValue(0);
        activityLevelPicker.setMaxValue(2);
        activityLevelPicker.setDisplayedValues(new String[]{"Low", "Medium", "High"});

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoalWeight();
                saveActivityLevel();
            }
        });
    }

    private void saveGoalWeight() {
        int goalWeight = goalWeightPicker.getValue();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference.child("users").child(userId).child("goalWeight").setValue(goalWeight);
        }
    }

    private void saveActivityLevel() {
        String[] activityLevels = {"Low", "Medium", "High"};
        String activityLevel = activityLevels[activityLevelPicker.getValue()];

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference.child("users").child(userId).child("activityLevel").setValue(activityLevel);
        }

        openHomeActivity();
    }

    private void openHomeActivity() {
        Intent intent = new Intent(GoalWeightActivity.this, ProgressActivity.class);
        startActivity(intent);
        finish();
    }
}