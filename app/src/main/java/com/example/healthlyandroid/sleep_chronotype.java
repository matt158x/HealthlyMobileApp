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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.Objects;

public class sleep_chronotype extends AppCompatActivity {



    private int scoreLion = 0;
    private int scoreWolf = 0;
    private int scoreDolphin = 0;
    private int scoreBear = 0;
    private Button[] buttons = new Button[32];
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sleep_chronotype);


        setStatusBarTransparent();


        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();


        // Inicjalizacja przycisków
        Button button1_1 = findViewById(R.id.button1_1);
        Button button1_2 = findViewById(R.id.button1_2);
        Button button1_3 = findViewById(R.id.button1_3);
        Button button1_4 = findViewById(R.id.button1_4);

        Button button2_1 = findViewById(R.id.button2_1);
        Button button2_2 = findViewById(R.id.button2_2);
        Button button2_3 = findViewById(R.id.button2_3);
        Button button2_4 = findViewById(R.id.button2_4);

        Button button3_1 = findViewById(R.id.button3_1);
        Button button3_2 = findViewById(R.id.button3_2);
        Button button3_3 = findViewById(R.id.button3_3);
        Button button3_4 = findViewById(R.id.button3_4);

        Button button4_1 = findViewById(R.id.button4_1);
        Button button4_2 = findViewById(R.id.button4_2);
        Button button4_3 = findViewById(R.id.button4_3);
        Button button4_4 = findViewById(R.id.button4_4);

        Button button5_1 = findViewById(R.id.button5_1);
        Button button5_2 = findViewById(R.id.button5_2);
        Button button5_3 = findViewById(R.id.button5_3);
        Button button5_4 = findViewById(R.id.button5_4);

        Button button6_1 = findViewById(R.id.button6_1);
        Button button6_2 = findViewById(R.id.button6_2);
        Button button6_3 = findViewById(R.id.button6_3);
        Button button6_4 = findViewById(R.id.button6_4);

        Button button7_1 = findViewById(R.id.button7_1);
        Button button7_2 = findViewById(R.id.button7_2);
        Button button7_3 = findViewById(R.id.button7_3);
        Button button7_4 = findViewById(R.id.button7_4);

        Button button8_1 = findViewById(R.id.button8_1);
        Button button8_2 = findViewById(R.id.button8_2);

        Button buttonEnter = findViewById(R.id.button_enter);


        // Ustaw nasłuchiwaczy na przyciski
        setButtonClickListeners(button1_1);
        setButtonClickListeners(button1_2);
        setButtonClickListeners(button1_3);
        setButtonClickListeners(button1_4);

        setButtonClickListeners(button2_1);
        setButtonClickListeners(button2_2);
        setButtonClickListeners(button2_3);
        setButtonClickListeners(button2_4);

        setButtonClickListeners(button3_1);
        setButtonClickListeners(button3_2);
        setButtonClickListeners(button3_3);
        setButtonClickListeners(button3_4);

        setButtonClickListeners(button4_1);
        setButtonClickListeners(button4_2);
        setButtonClickListeners(button4_3);
        setButtonClickListeners(button4_4);

        setButtonClickListeners(button5_1);
        setButtonClickListeners(button5_2);
        setButtonClickListeners(button5_3);
        setButtonClickListeners(button5_4);

        setButtonClickListeners(button6_1);
        setButtonClickListeners(button6_2);
        setButtonClickListeners(button6_3);
        setButtonClickListeners(button6_4);

        setButtonClickListeners(button7_1);
        setButtonClickListeners(button7_2);
        setButtonClickListeners(button7_3);
        setButtonClickListeners(button7_4);

        setButtonClickListeners(button8_1);

        setButtonClickListeners(button8_2);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(sleep_chronotype.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(sleep_chronotype.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(sleep_chronotype.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(sleep_chronotype.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });


        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayChronotype();
            }
        });
    }

    private void setButtonClickListeners(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(button);
            }
        });
    }

    private void handleButtonClick(Button button) {
        // Przycisk został kliknięty, zmień jego kolor na zielony
        button.setBackgroundColor(getResources().getColor(R.color.green));


        // Pobierz indeks przycisku i zaktualizuj wynik
        int buttonIndex = Integer.parseInt(button.getTag().toString());
        // Uaktualnij wynik na podstawie przycisku
        switch (buttonIndex % 4) {
            case 0:
                scoreLion++;
                break;
            case 1:
                scoreBear++;
                break;
            case 2:
                scoreWolf++;
                break;
            case 3:
                scoreDolphin++;
                break;
        }
    }

    private void displayChronotype() {
        // Określenie chronotypu na podstawie wyników
        String chronotype;
        if (scoreLion > scoreBear && scoreLion > scoreWolf && scoreLion > scoreDolphin) {
            chronotype = "Lion";
        } else if (scoreBear > scoreLion && scoreBear > scoreWolf && scoreBear > scoreDolphin) {
            chronotype = "Bear";
        } else if (scoreWolf > scoreLion && scoreWolf > scoreBear && scoreWolf > scoreDolphin) {
            chronotype = "Wolf";
        } else {
            chronotype = "Dolphin";
        }

        // Tworzenie intentu
        Intent intent = new Intent(this, sleep_chronotype_result.class);
        intent.putExtra("chronotypeResult", chronotype);

        // Uruchamianie nowej aktywności
        startActivity(intent);
    }




}