package com.example.healthlyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Objects;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obsługa kliknięcia przycisku cofania
                onBackPressed();
            }
        });

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


        // Przycisk "bieg"
        Button biegButton = findViewById(R.id.Bieg);
        biegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie i uruchomienie Intent z informacją o rodzaju aktywności
                Intent intent = new Intent(WorkoutActivity.this, BurnActivity.class);
                intent.putExtra("activityType", "bieg");
                startActivity(intent);
            }
        });

        // Przycisk "rolki"
        Button rolkiButton = findViewById(R.id.Rolki);
        rolkiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie i uruchomienie Intent z informacją o rodzaju aktywności
                Intent intent = new Intent(WorkoutActivity.this, BurnActivity.class);
                intent.putExtra("activityType", "rolki");
                startActivity(intent);
            }
        });

        // Przycisk "pływanie"
        Button swimButton = findViewById(R.id.Swim);
        swimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie i uruchomienie Intent z informacją o rodzaju aktywności
                Intent intent = new Intent(WorkoutActivity.this, BurnActivity.class);
                intent.putExtra("activityType", "swim");
                startActivity(intent);
            }
        });

        // Przycisk "rower"
        Button rowerButton = findViewById(R.id.Rower);
        rowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie i uruchomienie Intent z informacją o rodzaju aktywności
                Intent intent = new Intent(WorkoutActivity.this, BurnActivity.class);
                intent.putExtra("activityType", "rower");
                startActivity(intent);
            }
        });

        // Przycisk "silownia"
        Button workoutButton = findViewById(R.id.workout);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie i uruchomienie Intent z informacją o rodzaju aktywności
                Intent intent = new Intent(WorkoutActivity.this, BurnActivity.class);
                intent.putExtra("activityType", "workout");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Możesz dodać dodatkową logikę lub wrócić do poprzedniej aktywności
        // Jeśli chcesz wrócić do poprzedniej aktywności, użyj Intent
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish(); // Opcjonalnie możesz wywołać finish(), aby zamknąć obecną aktywność
    }
}