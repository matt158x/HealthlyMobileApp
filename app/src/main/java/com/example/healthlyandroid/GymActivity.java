package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class GymActivity extends AppCompatActivity {


    private int selectedChoice1 = -1; // Wybór z fragmentu 1
    private int selectedChoice2 = -1; // Wybór z fragmentu 2

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(GymActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(GymActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(GymActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(GymActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        // Ustaw GymFragment1 jako początkowy fragment
        loadFragment(new GymFragment1());
    }

    // Metoda do przełączania między fragmentami
    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    // Metoda do przełączania z GymFragment1 do GymFragment2
    public void switchToGymFragment2() {
        loadFragment(new GymFragment2());
    }

    // Metoda do przełączania z GymFragment2 do GymFragment3
    public void switchToGymFragment3() {
        loadFragment(new GymFragment3());
    }


    // Metoda do uzyskiwania tekstu na podstawie wyborów z fragmentów 1 i 2
    public String generateTextForGymFragment3() {
        String text = "";

        // Generowanie tekstu w zależności od wyborów
        if (selectedChoice1 != -1 && selectedChoice2 != -1) {
            if (selectedChoice1 == 2 && selectedChoice2 == 4) {
                // Jeśli selectedChoice1 to 2 i selectedChoice2 to 2, ustaw "drugi drugi wybór"
                text = "1. Bench Press with Barbell:\n" +
                        "\t4 sets x 6-8 reps\n" +
                        "\n" +
                        "2. Incline Dumbell Press:\n" +
                        "\t3 sets x 8-10 reps\n" +
                        "\n" +
                        "3. Dumbbell Flyes:\n" +
                        "\t3 sets x 10-12 reps\n" +
                        "\n" +
                        "4. Machine Chest Press:\n" +
                        "\t3 sets x 6-8 reps\n" +
                        "\n" +
                        "5. Dips on Parallel Bars:\n" +
                        "\t3 sets x maximum reps\n" +
                        "\n" +
                        "6. Dumbbell Pull-over:\n" +
                        "\t3 sets x 10-12 reps";
            } else {
                // Inaczej, ustaw inny tekst zależnie od wyborów
                text = "Tekst zależny od innych wyborów";
            }
        }

        return text;
    }


    // Metoda do przejścia z GymFragment2 do kolejnego fragmentu lub wykonania innych działań
    public void switchToNextFragment() {
        // Tutaj możesz przejść do kolejnego fragmentu lub wykonać inne działania
    }

    // Metoda do ustawiania wyboru z fragmentu 1
    public void setSelectedChoice1(int choice) {
        selectedChoice1 = choice;
    }

    // Metoda do ustawiania wyboru z fragmentu 2
    public void setSelectedChoice2(int choice) {
        selectedChoice2 = choice;
    }



    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();





    }
}