package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BurnActivity extends AppCompatActivity {

    private EditText editTimeRoute;
    private TextView caloriesBurnedTextView;
    private DatabaseReference userDatabase;

    private double weight = 0.0; // Wartość wagi użytkownika
    private double metValue = 0.0; // Wartość MET dla wybranej aktywności

    private BottomNavigationView bottomNavigationView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn);

        editTimeRoute = findViewById(R.id.edit_time_route);
        caloriesBurnedTextView = findViewById(R.id.calories_burned);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obsługa kliknięcia przycisku cofania
                onBackPressed();
            }
        });



        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(BurnActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(BurnActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(BurnActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(BurnActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });




        // Odbierz informację o rodzaju aktywności z Intent
        String activityType = getIntent().getStringExtra("activityType");

        // Znajdź przyciski w widoku
        Button lowButton = findViewById(R.id.low);
        Button midButton = findViewById(R.id.mid);
        Button highButton = findViewById(R.id.high);
        Button additionalButton = findViewById(R.id.addictional);

        String additionalButtonText = null;

        // Ustaw odpowiednie teksty na przyciskach w zależności od rodzaju aktywności
        if ("bieg".equals(activityType)) {
            lowButton.setText("Walking");
            midButton.setText("Jogging");
            highButton.setText("Sprint");
        } else if ("rolki".equals(activityType)) {
            lowButton.setText("Slow roller skating");
            midButton.setText("Medium speed roller skating");
            highButton.setText("Fast speed roller skating");
        } else if ("rower".equals(activityType)) {
            lowButton.setText("Slow road riding");
            midButton.setText("Medium speed road riding");
            highButton.setText("High speed road riding");
            additionalButton.setText("Mountain riding (MTB)");
        } else if ("swim".equals(activityType)) {
            lowButton.setText("Calm swimming");
            midButton.setText("Intense swimming (crawl)");
            highButton.setText("Water gymnastic");
        } else if("workout".equals(activityType)) {
            lowButton.setText("Light strength training");
            midButton.setText("Medium strength training");
            highButton.setText("Intense strength training");
        }


        // Inicjalizacja referencji do bazy danych Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            userDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

            // Pobierz wagę użytkownika z Firebase
            userDatabase.child("weight").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        weight = dataSnapshot.getValue(Double.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Obsłuż błąd pobierania danych z Firebase
                }
            });
        }


        Button addButton = findViewById(R.id.add_burned_calories); // Przycisk do dodawania kalorii



        // Inicjalizacja wartości MET na podstawie rodzaju aktywności
        double lowMet, midMet, highMet;
        double additionalMet = 0.0;

        if ("bieg".equals(activityType)) {
            lowMet = 3.0;
            midMet = 8.0;
            highMet = 12.0;
            additionalButton.setVisibility(View.GONE); // Ukryj dodatkowy przycisk
        } else if ("rolki".equals(activityType)) {
            lowMet = 4.5;
            midMet = 7.0;
            highMet = 12.0;
            additionalButton.setVisibility(View.GONE); // Ukryj dodatkowy przycisk
        } else if ("swim".equals(activityType)) {
            lowMet = 4.5;
            midMet = 7.5;
            highMet = 3.0;
            additionalButton.setVisibility(View.GONE); //Ukryj dodatkowy przycisk
        } else if ("workout".equals(activityType)) {
            lowMet = 3.0;
            midMet = 4.5;
            highMet = 6.5;
            additionalButton.setVisibility(View.GONE); //Ukryj dodatkowy
        } else if ("rower".equals(activityType)) {
            lowMet = 4.0;
            midMet = 6.0;
            highMet = 9.0;
            additionalMet = 13.0; // Dodatkowa wartość MET
            additionalButton.setVisibility(View.VISIBLE); // Wyświetl dodatkowy przycisk
            additionalButtonText = "Mountain riding (MTB)";
        } else {
            // Domyślne wartości MET, jeśli rodzaj aktywności nie jest rozpoznawany
            lowMet = 0.0;
            midMet = 0.0;
            highMet = 0.0;
            additionalMet = 0.0;
            additionalButton.setVisibility(View.GONE); // Ukryj dodatkowy przycisk
            additionalButtonText = "";
        }

        // Ustaw odpowiednie wartości MET w zależności od rodzaju aktywności
        lowButton.setOnClickListener(view -> {
            metValue = lowMet;
            calculateCaloriesBurned();
        });

        midButton.setOnClickListener(view -> {
            metValue = midMet;
            calculateCaloriesBurned();
        });

        highButton.setOnClickListener(view -> {
            metValue = highMet;
             calculateCaloriesBurned();
        });

        // Ustaw dodatkowy przycisk
        additionalButton.setText(additionalButtonText);
        double finalAdditionalMet = additionalMet;
        additionalButton.setOnClickListener(view -> {
            metValue = finalAdditionalMet;
            calculateCaloriesBurned();
        });

        // Nasłuchuj zmian w polu czasu i oblicz kalorie po zmianach
        editTimeRoute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateCaloriesBurned();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Dodaj obsługę przycisku do dodawania kalorii
        addButton.setOnClickListener(view -> {
            addCaloriesBurnedToFirebase();
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Możesz dodać dodatkową logikę lub wrócić do poprzedniej aktywności
        // Jeśli chcesz wrócić do poprzedniej aktywności, użyj Intent
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
        finish(); // Opcjonalnie możesz wywołać finish(), aby zamknąć obecną aktywność
    }





private void calculateCaloriesBurned() {
        try {
            double timeMinutes = Double.parseDouble(editTimeRoute.getText().toString());

            // Przelicz czas na godziny
            double timeHours = timeMinutes / 60.0;

            // Oblicz spalone kalorie na podstawie MET, czasu i wagi
            double caloriesBurned = metValue * weight * timeHours;

            // Wyświetl wynik w TextView "calories_burned"
            caloriesBurnedTextView.setText(String.format("%.2f kcal", caloriesBurned));
        } catch (NumberFormatException e) {
            // Obsłuż błąd parsowania, np. gdy wartość czasu nie jest liczbą
            caloriesBurnedTextView.setText("Calories Burned: ");
        }
    }

    private void addCaloriesBurnedToFirebase() {
        try {
            double timeMinutes = Double.parseDouble(editTimeRoute.getText().toString());

            // Przelicz czas na godziny
            double timeHours = timeMinutes / 60.0;

            // Oblicz spalone kalorie na podstawie MET, czasu i wagi
            double caloriesBurned = metValue * weight * timeHours;

            // Dodaj spalone kalorie do Firebase
            userDatabase.child("caloriesBurned").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    double currentCaloriesBurned = 0.0;
                    if (dataSnapshot.exists()) {
                        currentCaloriesBurned = dataSnapshot.getValue(Double.class);
                    }

                    // Zaktualizuj wartość w Firebase dodając do siebie
                    double updatedCaloriesBurned = currentCaloriesBurned + caloriesBurned;
                    userDatabase.child("caloriesBurned").setValue(updatedCaloriesBurned);

                    // Przejście do HomeActivity
                    Intent intent = new Intent(BurnActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Obsłuż błąd zapisu do Firebase
                }
            });
        } catch (NumberFormatException e) {
            // Obsłuż błąd parsowania, np. gdy wartość czasu nie jest liczbą
        }
    }
}