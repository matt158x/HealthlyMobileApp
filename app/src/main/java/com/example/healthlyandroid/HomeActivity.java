package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private TextView goalCalorieText;
    private boolean isIngredientListVisible = false;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ListView ingredientsListView;
    private ArrayList<String> ingredientsList;
    private ArrayAdapter<String> adapter;

    private int caloriesValue = 0;
    private int goalCalories;
    private int proteinsValue = 0;
    private int carbsValue = 0;
    private int fatValue = 0;
    private double waterValue =0;

    private TextView waterCountTextView;
    private TextView leftTextView;
    private TextView rightTextView;

    private BottomNavigationView bottomNavigationView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicjalizacja komponentów interfejsu

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

        waterCountTextView = findViewById(R.id.water_count);
        rightTextView = findViewById(R.id.right_text_view);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(HomeActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(HomeActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(HomeActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });





        // Wywołaj funkcję retrieveUserDataFromFirebase() po zalogowaniu użytkownika
        if (mAuth.getCurrentUser() != null) {
            retrieveUserDataFromFirebase();
        }

        Button waterButton = findViewById(R.id.water_button);
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseWaterIntake(0.5);
            }
        });

        ImageButton profileButton = findViewById(R.id.profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        goalCalorieText = findViewById(R.id.goal_calorie_text);
        leftTextView = findViewById(R.id.left_text_view);



        findViewById(R.id.breakfast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MealActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.workout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WorkoutActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        retrieveGoalCalorie();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                double proteinToAdd = data.getDoubleExtra("proteinToAdd", 0);
                double carbsToAdd = data.getDoubleExtra("carbsToAdd", 0);
                double fatToAdd = data.getDoubleExtra("fatToAdd", 0);
                double caloriesToAdd = data.getDoubleExtra("caloriesToAdd", 0);

                proteinsValue += proteinToAdd;
                carbsValue += carbsToAdd;
                fatValue += fatToAdd;
                caloriesValue += caloriesToAdd;

                TextView proteinsTextView = findViewById(R.id.proteins_text_view);
                TextView carbsTextView = findViewById(R.id.carbs_text_view);
                TextView fatTextView = findViewById(R.id.fat_text_view);
                TextView caloriesTextView = findViewById(R.id.left_text_view);

                proteinsTextView.setText(String.valueOf(proteinsValue));
                carbsTextView.setText(String.valueOf(carbsValue));
                fatTextView.setText(String.valueOf(fatValue));
                caloriesTextView.setText(String.valueOf(caloriesValue));

                // Aktualizacja pozostałych kalorii
                updateRemainingCalories();

                // Zapisanie wartości w bazie danych
                saveUserDataToFirebase(proteinsValue, carbsValue, fatValue, caloriesValue);
            }
        }
    }

    private void updateRemainingCalories() {
        String leftCaloriesText = leftTextView.getText().toString();

        // Zamień przecinek na kropkę i uwzględnij poprawne Locale
        double leftCalories = Double.parseDouble(leftCaloriesText.replace(",", "."));

        int remainingCalories = (int) (goalCalories - leftCalories);

        // Ustaw minimalną wartość 0, jeśli wartość wynikowa jest mniejsza
        if (remainingCalories < 0) {
            remainingCalories = 0;
        }

        goalCalorieText.setText(String.valueOf(remainingCalories));
    }

    private void increaseWaterIntake(double amount) {
        TextView waterCountTextView = findViewById(R.id.water_count);
        String waterCountText = waterCountTextView.getText().toString();
        double currentWaterIntake = Double.parseDouble(waterCountText.replaceAll(" L", "").replace(',', '.'));

        double newWaterIntake = currentWaterIntake + amount;
        waterCountTextView.setText(String.format("%.1f L", newWaterIntake));

        // Zapisz nową wartość do bazy danych
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
            userRef.child("water_intake").setValue(newWaterIntake);
        }
    }


    private void retrieveGoalCalorie() {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userReference = mDatabase.child("users").child(userId);

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    goalCalories = snapshot.child("goalCalorie").getValue(Integer.class);
                    goalCalorieText.setText(String.valueOf(goalCalories));
                    updateRemainingCalories();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Obsługa błędu odczytu z bazy danych
            }
        });
    }

    private void retrieveUserDataFromFirebase() {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userReference = mDatabase.child("users").child(userId);

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Integer caloriesEaten = snapshot.child("calories_eaten").getValue(Integer.class);
                    Integer carbsEaten = snapshot.child("carbs_eaten").getValue(Integer.class);
                    Integer proteinsEaten = snapshot.child("proteins_eaten").getValue(Integer.class);
                    Integer fatEaten = snapshot.child("fat_eaten").getValue(Integer.class);
                    Double waterIntake = snapshot.child("water_intake").getValue(Double.class);
                    Double caloriesBurned = snapshot.child("caloriesBurned").getValue(Double.class); // Pobierz wartość jako Double
                    int caloriesBurnedInt = caloriesBurned != null ? caloriesBurned.intValue() : 0; // Konwertuj na int lub 0, jeśli jest null

                    // Sprawdź, czy dane nie są null przed odwołaniem się do intValue()
                    if (caloriesEaten != null && carbsEaten != null && proteinsEaten != null && fatEaten != null && waterIntake != null) {
                        // Aktualizacja interfejsu z nowymi danymi
                        TextView proteinsTextView = findViewById(R.id.proteins_text_view);
                        TextView carbsTextView = findViewById(R.id.carbs_text_view);
                        TextView fatTextView = findViewById(R.id.fat_text_view);
                        TextView caloriesTextView = findViewById(R.id.left_text_view);
                        TextView water_count = findViewById(R.id.water_count);

                        proteinsValue = proteinsEaten;
                        carbsValue = carbsEaten;
                        fatValue = fatEaten;
                        caloriesValue = caloriesEaten;
                        waterValue = waterIntake;

                        proteinsTextView.setText(String.valueOf(proteinsValue));
                        carbsTextView.setText(String.valueOf(carbsValue));
                        fatTextView.setText(String.valueOf(fatValue));
                        caloriesTextView.setText(String.valueOf(caloriesValue));
                        water_count.setText(String.valueOf(waterValue));

                        // Aktualizacja pozostałych kalorii
                        updateRemainingCalories();

                        // Ustawienie wartości caloriesBurned w rightTextView
                        rightTextView.setText(String.valueOf(caloriesBurnedInt)); // Dodaj to
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Obsługa błędu odczytu z bazy danych
            }
        });
    }

    private void saveUserDataToFirebase(int proteins, int carbs, int fat, int calories) {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userReference = mDatabase.child("users").child(userId);

        // Utworzenie obiektu do zapisu
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("calories_eaten", calories);
        userUpdates.put("carbs_eaten", carbs);
        userUpdates.put("proteins_eaten", proteins);
        userUpdates.put("fat_eaten", fat);

        // Aktualizacja danych w bazie
        userReference.updateChildren(userUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Powodzenie zapisu
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Obsługa błędu zapisu
                    }
                });
    }
}