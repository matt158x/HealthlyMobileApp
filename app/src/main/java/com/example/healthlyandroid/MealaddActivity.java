package com.example.healthlyandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MealaddActivity extends AppCompatActivity {

    private EditText nameEdit, caloriesEdit, proteinEdit, carbsEdit, fatEdit;
    private Button addButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealadd);


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


        // Initialize EditTexts and Button
        nameEdit = findViewById(R.id.name_edit);
        caloriesEdit = findViewById(R.id.calories_edit);
        proteinEdit = findViewById(R.id.protein_edit);
        carbsEdit = findViewById(R.id.carbs_edit);
        fatEdit = findViewById(R.id.fat_edit);
        addButton = findViewById(R.id.add_button_meal);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Obsługa przycisku "back_button" (strzałka wstecz)
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMealActivity();
            }
        });

        // Obsługa przycisku "addButton"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredient();
            }
        });
    }

    private void addIngredient() {
        String name = nameEdit.getText().toString().trim();
        int calories = Integer.parseInt(caloriesEdit.getText().toString());
        int protein = Integer.parseInt(proteinEdit.getText().toString());
        int carbs = Integer.parseInt(carbsEdit.getText().toString());
        int fat = Integer.parseInt(fatEdit.getText().toString());

        // Get the currently logged-in user's UID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Create a new ingredient entry in the user's ingredients node
        DatabaseReference userIngredientsRef = databaseReference.child("users").child(userId).child("ingredients").child(name);
        userIngredientsRef.child("calories").setValue(calories);
        userIngredientsRef.child("protein").setValue(protein);
        userIngredientsRef.child("carbs").setValue(carbs);
        userIngredientsRef.child("fat").setValue(fat);

        // Clear input fields
        nameEdit.setText("");
        caloriesEdit.setText("");
        proteinEdit.setText("");
        carbsEdit.setText("");
        fatEdit.setText("");

        // Dodaj powiadomienie
        Toast.makeText(getApplicationContext(), "Your ingredient has been added to the database!", Toast.LENGTH_SHORT).show();

        // Przygotuj dane do przekazania z powrotem do MealActivity
        Intent intent = new Intent();
        intent.putExtra("caloriesToAdd", calories);
        intent.putExtra("proteinToAdd", protein);
        intent.putExtra("carbsToAdd", carbs);
        intent.putExtra("fatToAdd", fat);

        // Ustaw wynik jako OK i przekaż dane
        setResult(Activity.RESULT_OK, intent);

        // Zakończ aktywność MealaddActivity
        finish();
    }

    private void navigateBackToMealActivity() {
        onBackPressed(); // Ta metoda powinna zautomatyzować proces powrotu do poprzedniej aktywności
    }
}