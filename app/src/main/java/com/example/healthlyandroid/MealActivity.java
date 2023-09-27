package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Objects;

public class MealActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<String> ingredientsList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> originalIngredientsList;
    private TextView caloriesTextView;
    private TextView proteinTextView;
    private TextView carbsTextView;
    private TextView fatTextView;
    private TextView ingredientTextView;
    private EditText gramsEditText;
    private int gramsValue = 100;
    private double selectedCalories = 0;
    private double selectedProtein = 0;
    private double selectedCarbs = 0;
    private double selectedFat = 0;
    private ArrayList<String> userIngredientsList;
    private ArrayList<String> combinedIngredientsList;

    private static final int MEALADD_REQUEST_CODE = 1; // Stała do identyfikacji żądania

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

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

        ListView ingredientsListView = findViewById(R.id.ingredients_list_view);

        ingredientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedIngredient = ingredientsList.get(position);
                showSelectedIngredient(selectedIngredient);
            }
        });

        ImageView mealAddButton = findViewById(R.id.mealadd_button);
        mealAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealActivity.this, MealaddActivity.class);
                startActivityForResult(intent, MEALADD_REQUEST_CODE); // Uruchamiamy MealaddActivity z wynikiem
            }
        });

        originalIngredientsList = new ArrayList<>();
        userIngredientsList = new ArrayList<>(); // Initialize user ingredients list
        combinedIngredientsList = new ArrayList<>(); // Initialize combined ingredients list
        retrieveIngredients();

        ingredientsList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientsList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(getResources().getColor(android.R.color.white)); // Ustaw kolor tekstu na biały
                return view;
            }
        };

        ingredientsListView.setAdapter(adapter);



        EditText searchInput = findViewById(R.id.search_input);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterIngredients(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    ingredientsList.clear();
                    ingredientsList.addAll(originalIngredientsList);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        caloriesTextView = findViewById(R.id.calories_text_view);
        proteinTextView = findViewById(R.id.proteins_text_view);
        carbsTextView = findViewById(R.id.carbs_text_view);
        fatTextView = findViewById(R.id.fat_text_view);
        ingredientTextView = findViewById(R.id.ingredient);
        gramsEditText = findViewById(R.id.grams_edit_text);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String caloriesText = caloriesTextView.getText().toString().replace(",", ".");
                    String proteinText = proteinTextView.getText().toString().replace(",", ".");
                    String carbsText = carbsTextView.getText().toString().replace(",", ".");
                    String fatText = fatTextView.getText().toString().replace(",", ".");

                    double caloriesToAdd = Double.parseDouble(caloriesText);
                    double proteinToAdd = Double.parseDouble(proteinText);
                    double carbsToAdd = Double.parseDouble(carbsText);
                    double fatToAdd = Double.parseDouble(fatText);

                    Intent intent = new Intent();
                    intent.putExtra("caloriesToAdd", caloriesToAdd);
                    intent.putExtra("proteinToAdd", proteinToAdd);
                    intent.putExtra("carbsToAdd", carbsToAdd);
                    intent.putExtra("fatToAdd", fatToAdd);

                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Błąd: Nieprawidłowy format liczby", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToHomeActivity();
            }
        });

        Button minusButton = findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseGramsBy5();
                updateMacros();
            }
        });

        Button plusButton = findViewById(R.id.button_plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseGramsBy5();
                updateMacros();
            }
        });

        // Dodajemy nasłuchiwanie zmian w polu tekstowym "gramsEditText"
        gramsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Aktualizujemy wartość gramsValue na podstawie tekstu w polu "gramsEditText"
                try {
                    gramsValue = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    // Błąd w przypadku nieprawidłowego formatu liczby
                    gramsValue = 0;
                }
                updateMacros(); // Wywołujemy aktualizację makroskładników po zmianie gramów
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void decreaseGramsBy5() {
        gramsValue -= 5;
        if (gramsValue < 0) {
            gramsValue = 0;
        }
        updateGramsEditText();
    }

    private void increaseGramsBy5() {
        gramsValue += 5;
        updateGramsEditText();
    }

    private void updateGramsEditText() {
        gramsEditText.setText(String.valueOf(gramsValue));
    }

    private void updateMacros() {
        double percentage = (double) gramsValue / 100.0;
        double calories = selectedCalories * percentage;
        double protein = selectedProtein * percentage;
        double carbs = selectedCarbs * percentage;
        double fat = selectedFat * percentage;

        caloriesTextView.setText(String.format("%.1f", calories));
        proteinTextView.setText(String.format("%.1f", protein));
        carbsTextView.setText(String.format("%.1f", carbs));
        fatTextView.setText(String.format("%.1f", fat));
    }

    private void retrieveIngredients() {
        DatabaseReference ingredientsReference = mDatabase.child("ingredients");

        ingredientsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                originalIngredientsList.clear();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String ingredientName = childSnapshot.getKey();
                    originalIngredientsList.add(ingredientName);
                }

                // Retrieve user-specific ingredients under their node
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference userIngredientsReference = mDatabase.child("users").child(userId).child("ingredients");

                userIngredientsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot userSnapshot) {
                        userIngredientsList.clear();

                        for (DataSnapshot childSnapshot : userSnapshot.getChildren()) {
                            String userIngredientName = childSnapshot.getKey();
                            userIngredientsList.add(userIngredientName);
                        }

                        // Combine original and user ingredients
                        combinedIngredientsList.clear();
                        combinedIngredientsList.addAll(originalIngredientsList);
                        combinedIngredientsList.addAll(userIngredientsList);

                        ingredientsList.clear();
                        ingredientsList.addAll(combinedIngredientsList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private void navigateBackToHomeActivity() {
        Intent intent = new Intent(MealActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Zamknij obecną aktywność, aby wrócić do poprzedniej
    }

    private void showSelectedIngredient(String ingredient) {
        ingredientTextView.setText(ingredient);

        DatabaseReference ingredientReference;

        // Sprawdź, czy to składnik użytkownika
        if (userIngredientsList.contains(ingredient)) {
            // Składnik użytkownika
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            ingredientReference = mDatabase.child("users").child(userId).child("ingredients").child(ingredient);
        } else {
            // Ogólny składnik
            ingredientReference = mDatabase.child("ingredients").child(ingredient);
        }

        ingredientReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Double calories = getDoubleValue(snapshot.child("calories"));
                    Double protein = getDoubleValue(snapshot.child("protein"));
                    Double carbs = getDoubleValue(snapshot.child("carbs"));
                    Double fat = getDoubleValue(snapshot.child("fat"));

                    selectedCalories = calories != null ? calories : 0;
                    selectedProtein = protein != null ? protein : 0;
                    selectedCarbs = carbs != null ? carbs : 0;
                    selectedFat = fat != null ? fat : 0;

                    updateMacros();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MEALADD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Tutaj możesz ponownie pobrać składniki po dodaniu z MealaddActivity
                retrieveIngredients();
            }
        }
    }



    private Double getDoubleValue(DataSnapshot snapshot) {
        if (snapshot.exists()) {
            Double value = snapshot.getValue(Double.class);
            return value != null ? value : 0.0; // Zwracamy 0.0 jako Double w przypadku braku wartości
        }
        return 0.0; // Zwracamy 0.0 jako Double w przypadku braku wartości
    }



    private void filterIngredients(String searchText) {
        ArrayList<String> filteredList = new ArrayList<>();

        if (searchText.isEmpty()) {
            filteredList.addAll(combinedIngredientsList); // Use the combined list
        } else {
            for (String ingredient : combinedIngredientsList) { // Use the combined list
                if (ingredient.toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(ingredient);
                }
            }
        }

        Collections.sort(filteredList, String.CASE_INSENSITIVE_ORDER);

        ingredientsList.clear();
        ingredientsList.addAll(filteredList);
        adapter.notifyDataSetChanged();
    }
}