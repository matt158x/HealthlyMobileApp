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

public class sleep_chronotype_result extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_chronotype_result);

        setStatusBarTransparent();
        displayChronotypeText();


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(sleep_chronotype_result.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(sleep_chronotype_result.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(sleep_chronotype_result.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(sleep_chronotype_result.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });


        ImageView exitImageView = findViewById(R.id.exit);
        exitImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie intentu do otwarcia aktywności SleepActivity
                Intent intent = new Intent(getApplicationContext(), SleepActivity.class);
                startActivity(intent);
            }
        });




        // Odbierz wynik z intentu
        String chronotypeResult = getIntent().getStringExtra("chronotypeResult");

        // Znajdź ImageView w nowej aktywności
        ImageView chronotypeImageView = findViewById(R.id.chronotype_image);

        // Ustaw tło ImageView na podstawie chronotypeResult
        int imageResource;
        switch (chronotypeResult) {
            case "Wolf":
                imageResource = R.drawable.wolf2;
                break;
            case "Dolphin":
                imageResource = R.drawable.dolphin2;
                break;
            case "Bear":
                imageResource = R.drawable.bear2;
                break;
            case "Lion":
                imageResource = R.drawable.lion2;
                break;
            default:
                // Domyślne tło lub obsługa błędu
                imageResource = R.drawable.bike;
                break;
        }

        chronotypeImageView.setImageResource(imageResource);
    }








    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Odbierz wynik z intentu
        String chronotypeResult = getIntent().getStringExtra("chronotypeResult");

        // Znajdź TextView w nowej aktywności
        TextView resultTextView = findViewById(R.id.result);

        // Ustaw wynik na TextView
        resultTextView.setText("You are " + chronotypeResult + " !");
    }



    private void displayChronotypeText() {
        // Odbierz wynik z intentu
        String chronotypeResult = getIntent().getStringExtra("chronotypeResult");

        // Znajdź TextView w nowej aktywności
        TextView chronotypeTextView = findViewById(R.id.chronotype_text);

        // Ustaw odpowiedni tekst na podstawie chronotypeResult
        String textToDisplay = "";

        switch (chronotypeResult) {
            case "Wolf":
                textToDisplay = "\nYou are one of the 15-20% of people who hate mornings.\nYou go to bed too late, which means you end up hitting snooze endlessly every day.\n\n" +
                        "If you are a wolf, your ideal daily schedule is as follows:\n" +
                        "\n" +
                        "1. Wake up around 7.30 am. To do this, set two alarms separated by a 20-minute break. The first one will wake you up and the second one will tell you to get out of bed.\n\n" +
                        "2. Once you have done this, pour yourself a glass of water and drink it near an open window.\n\n" +
                        "3. Eat your favorite breakfast if you like, drink coffee and go for a walk - preferably walk to school/work.\n\n" +
                        "4. Wolves are most efficient between 12-8 pm., and reach their peak around 2 p.m. The most important duties should be postponed until the afternoon.\n\n" +
                        "5. After work, you are still rested and full of energy, which is why the best time to exercise is between 6 and 7 pm.\n\n" +
                        "6. Now is the perfect time to tackle creative tasks.\n\n" +
                        "7. Wolves have no problem with going to bed late. However, if you really want to optimize your circadian rhythm, go to bed around midnight.\n\n" +
                        "Be sure to put away all your electronic gadgets an hour before falling asleep! This way you will prepare your body for sleep and help it regenerate.\n\n\n";
                break;


            case "Dolphin":
                textToDisplay = "\nDolphins make up 10% of the human population and their sleep is quite restless. They also sometimes suffer from insomnia.\n" +
                        "\n" +
                        "In real dolphins, only one cerebral hemisphere sleeps and the other one is awake. This name perfectly suits people suffering from insomnia, light sleepers and low sleep needs.\n" +
                        "\n" +
                        "To forget forever about sleepless nights, dolphins should follow the following daily schedule:\n" +
                        "\n" +
                        "1. Wake up at 6 am or 6 am. Start your day with exercise. Then take a cold or alternating shower to finally wake up your body.\n\n" +
                        "2. Then eat a breakfast full of protein and carbohydrates.\n\n" +
                        "3. As a dolphin, you are most productive between 10 am and 6 pm. You are at your peak between 10 am and 12 pm. This is the perfect time to work on creative things.\n\n" +
                        "4. You start to feel tired around lunch time. Take a short walk and grab a snack.\n\n" +
                        "5. Work some more. Around 6 pm, energize yourself with exercise or meditation. However, avoid strength exercises because they affect sleep disturbances.\n\n" +
                        "6. At 10 pm, turn off all electronic gadgets, take a hot bath or a long shower, read a book. Calm down, this way you will prepare your body for sleep and help it regenerate.\n\n" +
                        "7. It's best to go to bed around 11 pm, midnight at the latest. If you can't sleep, don't worry. Try to change the position you lie in until you finally find the most comfortable one - then sleep will come completely naturally!\n\n\n";
                break;


            case "Bear":
                textToDisplay = "\nThis is the most popular chronotype, it covers from 50 to 55% of all people in the world.\nYour type is most productive between 10 am and 6 pm - take advantage of this time!\n" +
                        "\n" +
                        "Bears are migrators that adapt to their surroundings. This category includes open-minded, fun-loving people who have a high need for sleep.\n" +
                        "\n" +
                        "If you are a bear, your ideal daily schedule should look like this:\n" +
                        "\n" +
                        "1. Wake up at 7 am. Immediately after waking up, spend a few minutes exercising.\n\n" +
                        "2. Remember about a hearty breakfast, which will help you avoid overeating in the evening, and coffee, which will get you back on your feet.\n\n" +
                        "3. You probably feel tired at lunch. Then go for a brisk walk to help you fight sleepiness.\n\n" +
                        "4. Around 2 pm you may feel tired again, so you'd better make yourself another cup of coffee to prepare you for the afternoon.\n\n" +
                        "5. Now it's good to fulfill your responsibilities.\n\n" +
                        "6. The best time to exercise is between 6 pm and 8 pm. Don't be lazy! Even the simplest training will give you the energy you need.\n\n" +
                        "7. Eat a light dinner after exercise. It should contain a lot of protein and less carbohydrates.\n\n" +
                        "8. Once you are full, spend your evening time as you wish. However, remember that now is the best time to perform creative tasks.\n\n" +
                        "9. A bear's ideal time to go to bed is 11 pm. Be sure to put away all your electronic gadgets an hour before falling asleep! This way you will prepare your body for sleep and help it regenerate.\n\n" +
                        "It is very important that the bear does not sleep more than 8 hours, even on weekends!\nSleeping longer will make you more tired!\n\n\n";
                break;


            case "Lion":
                textToDisplay = "\nYour chronotype covers approximately 11% of all people in the world.\nYou are the type of morning person, you have no problems getting up in the morning and you are able to perform up to 80% of your duties at a time when representatives of other sleep types have not yet woken up!\n\n" +
                        "However, even the kings of the jungle have their flaws. It is difficult for them to maintain high energy levels in the afternoon or evening.\n" +
                        "\n" +
                        "To maintain a healthy lifestyle, lions should follow the following schedule:\n" +
                        "\n" +
                        "1. Wake up around 5.30. After waking up, eat a breakfast with plenty of protein and a small portion of carbohydrates. After breakfast, you can spend some time meditating or exercising and plan your day.\n\n" +
                        "2. Lions are most effective between 10 am and 5 pm. This means that this is when most tasks should be performed.\n\n" +
                        "3. To keep your energy levels high, you should eat a small snack 3 to 4 hours after eating breakfast.\n\n" +
                        "4. Now is the best time to perform creative tasks.\n\n" +
                        "5. You may feel tired at 5 pm, so avoid working after this time. Instead of doing chores, exercise, it will help you stay fit and give you energy.\n\n" +
                        "6. When you are alert, allocate your time as you wish. You finally deserve some rest after a busy day!\n\n" +
                        "7. Go to bed around 10 pm. Be sure to put away all your electronic gadgets an hour before falling asleep! This way you will prepare your body for sleep and help it regenerate.\n\n\n";
                break;
        }

        chronotypeTextView.setText(textToDisplay);
    }




}




