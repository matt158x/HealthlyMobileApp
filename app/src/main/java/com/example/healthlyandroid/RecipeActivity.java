package com.example.healthlyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class RecipeActivity extends AppCompatActivity {

    private TextView mealNameTextView;
    private ImageView fullImage;
    private TextView caloriesTextView;
    private TextView proteinsTextView;
    private TextView carbsTextView;
    private TextView fatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        caloriesTextView = findViewById(R.id.calories_text_view);
        proteinsTextView = findViewById(R.id.proteins_text_view);
        carbsTextView = findViewById(R.id.carbs_text_view);
        fatTextView = findViewById(R.id.fat_text_view);

        mealNameTextView = findViewById(R.id.meal_name);

        fullImage = findViewById(R.id.full_image);

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




    }
}