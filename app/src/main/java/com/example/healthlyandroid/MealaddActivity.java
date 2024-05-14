package com.example.healthlyandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MealaddActivity extends AppCompatActivity {

    private EditText nameEdit, caloriesEdit, proteinEdit, carbsEdit, fatEdit;
    private Button addButton;

    private DatabaseReference databaseReference;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealadd);


        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(MealaddActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(MealaddActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(MealaddActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(MealaddActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });



        nameEdit = findViewById(R.id.name_edit);
        caloriesEdit = findViewById(R.id.calories_edit);
        proteinEdit = findViewById(R.id.protein_edit);
        carbsEdit = findViewById(R.id.carbs_edit);
        fatEdit = findViewById(R.id.fat_edit);
        addButton = findViewById(R.id.add_button_meal);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMealActivity();
            }
        });


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

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference userIngredientsRef = databaseReference.child("users").child(userId).child("ingredients").child(name);
        userIngredientsRef.child("calories").setValue(calories);
        userIngredientsRef.child("protein").setValue(protein);
        userIngredientsRef.child("carbs").setValue(carbs);
        userIngredientsRef.child("fat").setValue(fat);

        nameEdit.setText("");
        caloriesEdit.setText("");
        proteinEdit.setText("");
        carbsEdit.setText("");
        fatEdit.setText("");

        Toast.makeText(getApplicationContext(), "Your ingredient has been added to the database!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("caloriesToAdd", calories);
        intent.putExtra("proteinToAdd", protein);
        intent.putExtra("carbsToAdd", carbs);
        intent.putExtra("fatToAdd", fat);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    private void navigateBackToMealActivity() {
        onBackPressed();
    }
}