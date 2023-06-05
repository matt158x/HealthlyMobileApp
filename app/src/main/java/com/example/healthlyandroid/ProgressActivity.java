package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import com.example.healthlyandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.util.Calendar;



public class ProgressActivity extends AppCompatActivity {

    private TextView resultTextView;
    private TextView weightTextView;
    private TextView weightGoalTextView;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private int weight;
    private int height;
    private int age;
    private String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        resultTextView = findViewById(R.id.resultTextView);
        weightTextView = findViewById(R.id.weight);
        weightGoalTextView = findViewById(R.id.weight_goal);
        View chartContainer = findViewById(R.id.chart_container);
        View chart2Container = findViewById(R.id.chart2_container);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
            DatabaseReference userReference = databaseReference.child("users").child(userId);
            userReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        weight = snapshot.child("weight").getValue(Integer.class);
                        height = snapshot.child("height").getValue(Integer.class);
                        String gender = snapshot.child("gender").getValue(String.class);
                        int goalWeight = snapshot.child("goalWeight").getValue(Integer.class);
                        String goal = snapshot.child("goal").getValue(String.class);
                        age = snapshot.child("age").getValue(Integer.class);
                        if ("GAIN WEIGHT".equals(goal)) {
                            displayGainWeightResult(goalWeight);
                            chartContainer.setVisibility(View.VISIBLE); // Ustawienie widoczności kontenera dla celu "GAIN WEIGHT"
                            chart2Container.setVisibility(View.INVISIBLE); // Ustawienie niewidoczności kontenera dla celu "GAIN WEIGHT"
                        } else if ("LOSE WEIGHT".equals(goal)) {
                            displayLoseWeightResult(goalWeight);
                            chartContainer.setVisibility(View.INVISIBLE); // Ustawienie niewidoczności kontenera dla celu "LOSE WEIGHT"
                            chart2Container.setVisibility(View.VISIBLE); // Ustawienie widoczności kontenera dla celu "LOSE WEIGHT"
                        }
                        weightTextView.setText(String.valueOf(weight) + " kg");
                        weightGoalTextView.setText(String.valueOf(goalWeight) + " kg");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Obsługa błędu odczytu z bazy danych
                }
            });
        }

        Button nextButton = findViewById(R.id.next_button4);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoalDateToDatabase();
                openHomeActivity();
            }
        });
    }

    private void displayGainWeightResult(int goalWeight) {
        // Obliczenie przewidywanego czasu osiągnięcia celowej wagi na podstawie tempo przybierania na wadze

        double weeklyWeightGain = 0.5; // Tempo przybierania na wadze w kilogramach na tydzień

        int daysToGoalWeight = (int) Math.ceil((goalWeight - weight) / (weeklyWeightGain / 7)); // Obliczenie liczby dni do osiągnięcia celowej wagi

        // Obliczenie daty osiągnięcia celowej wagi
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysToGoalWeight);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String month = getMonthName(calendar.get(Calendar.MONTH));
        int year = calendar.get(Calendar.YEAR);

        String resultMessage = dayOfMonth + " " + month + " " + year;

        resultTextView.setText(resultMessage);
    }

    private void displayLoseWeightResult(int goalWeight) {
        // Obliczenie przewidywanego czasu osiągnięcia celowej wagi na podstawie tempa tracenia wagi

        double weeklyWeightLoss = 0.5; // Tempo tracenia wagi w kilogramach na tydzień

        int daysToGoalWeight = (int) Math.ceil((weight - goalWeight) / (weeklyWeightLoss / 7)); // Obliczenie liczby dni do osiągnięcia celowej wagi

        // Obliczenie daty osiągnięcia celowej wagi
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysToGoalWeight);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String month = getMonthName(calendar.get(Calendar.MONTH));
        int year = calendar.get(Calendar.YEAR);

        String resultMessage = dayOfMonth + " " + month + " " + year;

        resultTextView.setText(resultMessage);
    }

    private String getMonthName(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    private void saveGoalDateToDatabase() {
        String goalDate = resultTextView.getText().toString();

        DatabaseReference userReference = databaseReference.child("users").child(userId);
        userReference.child("goalDate").setValue(goalDate);
    }

    private void openHomeActivity() {
        Intent intent = new Intent(ProgressActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}