package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Objects;

public class RecipeActivity extends AppCompatActivity {

    private TextView mealNameTextView;
    private ImageView fullImage;
    private TextView caloriesTextView;
    private TextView proteinsTextView;
    private TextView carbsTextView;
    private TextView fatTextView;
    private TextView ingredientsTextView;
    private TextView recipeTextView;
    private Button addMealButton;

    private BottomNavigationView bottomNavigationView;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        caloriesTextView = findViewById(R.id.calories_text_view);
        proteinsTextView = findViewById(R.id.proteins_text_view);
        carbsTextView = findViewById(R.id.carbs_text_view);
        fatTextView = findViewById(R.id.fat_text_view);
        ingredientsTextView = findViewById(R.id.ingredients_list);
        recipeTextView = findViewById(R.id.recipe_text);

        mealNameTextView = findViewById(R.id.meal_name);

        fullImage = findViewById(R.id.full_image);

        addMealButton = findViewById(R.id.add_meal);


        // Odbieranie informacji z poprzedniej aktywności
        int imageId = getIntent().getIntExtra("imageId", R.drawable.full_pancakes);



        // Ustawianie obrazka w ImageView
        fullImage.setImageResource(imageId);


        // Odbieranie przekazanego tekstu z poprzedniej aktywności
        String mealName = getIntent().getStringExtra("mealName");

        // Ustawianie tekstu w TextView
        mealNameTextView.setText(mealName);

        String calories = getIntent().getStringExtra("calories");
        caloriesTextView.setText(calories);

        String proteins = getIntent().getStringExtra("proteins");
        proteinsTextView.setText(proteins);

        String carbs = getIntent().getStringExtra("carbs");
        carbsTextView.setText(carbs);

        String fat = getIntent().getStringExtra("fat");
        fatTextView.setText(fat);

        String ingredients = getIntent().getStringExtra("ingredients");
        ingredientsTextView.setText(ingredients);

        String recipe = getIntent().getStringExtra("recipe");
        recipeTextView.setText(recipe);


        // Inicjalizacja Firebase Authentication
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid(); // Pobieranie UID zalogowanego użytkownika


        // Inicjalizacja bazy danych Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid).child("ingredients"); // Użyj UID jako klucza użytkownika.child("ingredients");


        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToDietActivity();
            }
        });



        // Obsługa kliknięcia przycisku "add_meal"
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobieranie wartości z TextView
                int calories = Integer.parseInt(caloriesTextView.getText().toString());
                int carbs = Integer.parseInt(carbsTextView.getText().toString());
                int fat = Integer.parseInt(fatTextView.getText().toString());
                int proteins = Integer.parseInt(proteinsTextView.getText().toString());



                // Tworzenie obiektu do zapisu w bazie danych
                // Tutaj zapisujemy wartości bez tworzenia oddzielnej klasy
                databaseReference.child(mealName).child("calories").setValue(calories);
                databaseReference.child(mealName).child("carbs").setValue(carbs);
                databaseReference.child(mealName).child("fat").setValue(fat);
                databaseReference.child(mealName).child("protein").setValue(proteins);
                // Dodaj powiadomienie Toast
                Toast.makeText(RecipeActivity.this, "Meal added to database", Toast.LENGTH_SHORT).show();

                // Otwórz DietActivity
                Intent intent = new Intent(RecipeActivity.this, DietActivity.class);
                startActivity(intent);
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


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(RecipeActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(RecipeActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(RecipeActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(RecipeActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });



    }



    private void navigateBackToDietActivity() {
        onBackPressed(); // Ta metoda powinna zautomatyzować proces powrotu do poprzedniej aktywności
    }
}