package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class DietActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);





        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();




        ImageView imageView1_1 = findViewById(R.id.imageView1_1);
        imageView1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_grannola); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Granola with Fruits");
                intent.putExtra("calories", "412");
                intent.putExtra("proteins", "9");
                intent.putExtra("carbs", "77");
                intent.putExtra("fat", "12");
                startActivity(intent);
            }
        });

        ImageView imageView1_2 = findViewById(R.id.imageView1_2);
        imageView1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_burrito); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Breakfast Burrito");
                intent.putExtra("calories", "248");
                intent.putExtra("proteins", "20");
                intent.putExtra("carbs", "18");
                intent.putExtra("fat", "12");
                startActivity(intent);
            }
        });

        ImageView imageView1_3 = findViewById(R.id.imageView1_3);
        imageView1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_smoothie); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Green Smoothie");
                intent.putExtra("calories", "75");
                intent.putExtra("proteins", "2");
                intent.putExtra("carbs", "17");
                intent.putExtra("fat", "1");
                startActivity(intent);
            }
        });

        ImageView imageView2_1 = findViewById(R.id.imageView2_1);
        imageView2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_kurczak); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Vietnamese Chicken");
                intent.putExtra("calories", "186");
                intent.putExtra("proteins", "12");
                intent.putExtra("carbs", "21");
                intent.putExtra("fat", "6");
                startActivity(intent);
            }
        });

        ImageView imageView2_2 = findViewById(R.id.imageView2_2);
        imageView2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_pasta); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Pasta with Vegetables");
                intent.putExtra("calories", "148");
                intent.putExtra("proteins", "4");
                intent.putExtra("carbs", "23");
                intent.putExtra("fat", "3");
                startActivity(intent);
            }
        });

        ImageView imageView2_3 = findViewById(R.id.imageView2_3);
        imageView2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_pancakes); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Pancakes with Fruits");
                intent.putExtra("calories", "210");
                intent.putExtra("proteins", "6");
                intent.putExtra("carbs", "26");
                intent.putExtra("fat", "9");
                startActivity(intent);
            }
        });

        ImageView imageView3_1 = findViewById(R.id.imageView3_1);
        imageView3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_airfryer); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Air-Fryer Sweet Potato");
                intent.putExtra("calories", "87");
                intent.putExtra("proteins", "2");
                intent.putExtra("carbs", "10");
                intent.putExtra("fat", "5");
                startActivity(intent);
            }
        });

        ImageView imageView3_2 = findViewById(R.id.imageView3_2);
        imageView3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_steakcajun); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Steak with Cajun & Potatoes");
                intent.putExtra("calories", "122");
                intent.putExtra("proteins", "8");
                intent.putExtra("carbs", "6");
                intent.putExtra("fat", "8");
                startActivity(intent);
            }
        });

        ImageView imageView3_3 = findViewById(R.id.imageView3_3);
        imageView3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_fishpie); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Cheesy Fish Pie");
                intent.putExtra("calories", "76");
                intent.putExtra("proteins", "6");
                intent.putExtra("carbs", "4");
                intent.putExtra("fat", "5");
                startActivity(intent);
            }
        });



        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(DietActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(DietActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(DietActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(DietActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

}