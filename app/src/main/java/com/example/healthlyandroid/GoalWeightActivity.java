package com.example.healthlyandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.healthlyandroid.HomeActivity;
import com.example.healthlyandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GoalWeightActivity extends AppCompatActivity {

    private NumberPicker goalWeightPicker;
    private Button nextButton;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight);

        goalWeightPicker = findViewById(R.id.goal_weight);
        nextButton = findViewById(R.id.next_button4);

        // Ustawienie wartości dla NumberPicker
        goalWeightPicker.setMinValue(0);
        goalWeightPicker.setMaxValue(200);
        goalWeightPicker.setValue(70);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoalWeight();
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

        openHomeActivity();
    }

    private void openHomeActivity() {
        Intent intent = new Intent(GoalWeightActivity.this, ProgressActivity.class);
        startActivity(intent);
        finish();
    }
}
