package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class GymActivity extends AppCompatActivity {


    private int selectedChoice1 = -1; // Wybór z fragmentu 1
    private int selectedChoice2 = -1; // Wybór z fragmentu 2

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);


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
                        startActivity(new Intent(GymActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(GymActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(GymActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(GymActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });




        // Ustaw GymFragment1 jako początkowy fragment
        loadFragment(new GymFragment1());
    }

    // Metoda do przełączania między fragmentami
    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    // Metoda do przełączania z GymFragment1 do GymFragment2
    public void switchToGymFragment2() {
        loadFragment(new GymFragment2());
    }

    // Metoda do przełączania z GymFragment2 do GymFragment3
    public void switchToGymFragment3() {
        loadFragment(new GymFragment3());
    }


    // Metoda do uzyskiwania tekstu na podstawie wyborów z fragmentów 1 i 2
    public String generateTextForGymFragment3() {
        String text = "";

        // Generowanie tekstu w zależności od wyborów
        if (selectedChoice1 != -1 && selectedChoice2 != -1) {
            if (selectedChoice1 == 2 && selectedChoice2 == 4) {
                // Jeśli selectedChoice1 to 2 i selectedChoice2 to 2, ustaw "drugi drugi wybór"
                text = "1. Bench Press with Barbell:\n\n" +
                        "\t4 sets x 6-8 reps\n" +
                        "Begin with a warm-up using a light barbell, then gradually increase the weight.\n" +
                        "\n" +
                        "2. Incline Dumbell Press:\n\n" +
                        "\t3 sets x 8-10 reps\n" +
                        "Adjust the bench to an incline to focus on the upper part of the chest.\n" +
                        "\n" +
                        "3. Dumbbell Flyes:\n\n" +
                        "\t3 sets x 10-12 reps\n" +
                        "This exercise helps stretch the chest muscles.\n" +
                        "\n" +
                        "4. Machine Chest Press:\n\n" +
                        "\t3 sets x 6-8 reps\n" +
                        "Using machines can aid in isolating the chest muscles.\n" +
                        "\n" +
                        "5. Dips on Parallel Bars:\n\n" +
                        "\t3 sets x maximum reps\n" +
                        "Dips on parallel bars can be an excellent addition to your chest workout.\n" +
                        "\n" +
                        "6. Dumbbell Pull-over:\n\n" +
                        "\t3 sets x 10-12 reps\n" +
                        "Control the movement to isolate the chest.";
            } else {
                if (selectedChoice1 == 1 && selectedChoice2 == 4) {
                    // Inaczej, ustaw inny tekst zależnie od wyborów
                    text = "1. Bench Press with Barbell: \n\n" +
                            "\t3 sets x 10-12 reps\n" +
                            "Begin with a warm-up using a light barbell, then gradually increase the weight.\n" +
                            "\n" +
                            "2. Incline Dumbbell Press: \n\n" +
                            "\t3 sets x 10-12 reps\n" +
                            "Adjust the bench to an incline to focus on the upper part of the chest.\n" +
                            "\n" +
                            "3. Dumbbell Flyes: \n\n" +
                            "\t3 sets x 12-15 reps\n" +
                            "This exercise helps stretch the chest muscles.\n" +
                            "\n" +
                            "4. Machine Chest Press: \n\n" +
                            "\t3 sets x 10-12 reps\n" +
                            "Using machines can aid in isolating the chest muscles.\n" +
                            "\n" +
                            "5. Dips on Parallel Bars: \n\n" +
                            "\t2 sets x maximum reps\n" +
                            "Dips on parallel bars can be an excellent addition to your chest workout.";
                } else {
                    if (selectedChoice1 == 3 && selectedChoice2 == 4) {
                        text = "1. Bench Press with Barbell:\n\n" +
                                "\t4 sets x 4-6 reps\n" +
                                "Begin with a warm-up using a light barbell, then gradually increase the weight.\n" +
                                "\n" +
                                "2. Incline Dumbell Press:\n\n" +
                                "\t4 sets x 6-8 reps\n" +
                                "Adjust the bench to an incline to focus on the upper part of the chest.\n" +
                                "\n" +
                                "3. Dumbbell Flyes:\n\n" +
                                "\t4 sets x 8-10 reps\n" +
                                "This exercise helps stretch the chest muscles.\n" +
                                "\n" +
                                "4. Machine Chest Press:\n\n" +
                                "\t3 sets x 6-8 reps\n" +
                                "Using machines can aid in isolating the chest muscles.\n" +
                                "\n" +
                                "\n" +
                                "5. Dips on Parallel Bars:\n\n" +
                                "\t3 sets x maximum reps\n" +
                                "Dips on parallel bars can be an excellent addition to your chest workout.\n" +
                                "\n" +
                                "\n" +
                                "6. Dumbell Pull-over:\n\n" +
                                "\t4 sets x 8-10 reps\n" +
                                "Control the movement to isolate the chest.\n" +
                                "\n" +
                                "\n" +
                                "7. Flat Bench Dumbbell Raises:\n\n" +
                                "\t3 sets x 8-10 repetitions\n" +
                                "Use heavy dumbbells and control the movements.";
                    } else {
                        if (selectedChoice1 == 1 && selectedChoice2 == 5) {
                            text = "1. Dumbbell Shoulder Press:\t\n\n" +
                                    "\t3 sets x 10-12 reps\n" +
                                    "Focus on controlling the movement and maintaining stability.\n" +
                                    "\n" +
                                    "2. Standing Barbell Upright Rows:\n\n" +
                                    "\t3 sets x 10-12 reps\n" +
                                    "Perform this exercise in a standing position.\n" +
                                    "\n" +
                                    "3. Lateral Raises:\n\n" +
                                    "\t3 sets x 12-15 reps\n" +
                                    "Use light dumbbells.\n" +
                                    "\n" +
                                    "4. Machine Lateral Raises:\n\n" +
                                    "\t3 sets x 10-12 reps\n" +
                                    "Adjust the weight according to your strength.\n" +
                                    "\n" +
                                    "5. Front Raises with Dumbbells:\n\n" +
                                    "\t3 sets x 10-12 reps\n" +
                                    "Use a controlled motion to lift the dumbbells.";
                        } else {
                            if (selectedChoice1 == 2 && selectedChoice2 == 5) {
                                text = "1. Barbell Shoulder Press:\n\n" +
                                        "\t4 sets x 8-10 reps\n" +
                                        "Use a shoulder stabilizer.\n" +
                                        "\n" +
                                        "2. Standing Barbell Upright Rows:\n\n" +
                                        "\t4 sets x 8-10 reps\n" +
                                        "Increase the weight.\n" +
                                        "\n" +
                                        "3. Dumbbell Lateral Raises on an Incline Bench:\n\n" +
                                        "\t4 sets x 10-12 reps\n" +
                                        "Perform this exercise on an inclined bench.\n" +
                                        "\n" +
                                        "4. Incline Bench Dumbbell Shoulder Press:\n\n" +
                                        "\t4 sets x 8-10 reps\n" +
                                        "Focus on a full range of motion.\n" +
                                        "\n" +
                                        "5. Cable Lateral Raises:\n\n" +
                                        "\t4 sets x 10-12 reps\n" +
                                        "Set the cable at shoulder level.";
                            } else {
                                if (selectedChoice1 == 3 && selectedChoice2 == 5) {
                                    text = "1. Overhead Barbell Press with Squat:\n\n" +
                                            "\t5 sets x 6-8 reps\n" +
                                            "Requires stability and strength.\n" +
                                            "\n" +
                                            "2. Dumbbell Shrugs on a Decline Bench:\n\n" +
                                            "\t5 sets x 6-8 reps\n" +
                                            "Use heavy dumbbells.\n" +
                                            "\n" +
                                            "3. Incline Dumbbell Lateral Raises:\n\n" +
                                            "\t5 sets x 8-10 reps\n" +
                                            "Perform this exercise on an inclined bench.\n" +
                                            "\n" +
                                            "4. Flat Bench Dumbbell Shoulder Press:\n\n" +
                                            "\t5 sets x 6-8 reps\n" +
                                            "Use heavy dumbbells and control the movements.\n" +
                                            "\n" +
                                            "5. Cable Lateral Raises:\n\n" +
                                            "\t5 sets x 8-10 reps\n" +
                                            "Set the cable at shoulder level.";
                                } else {
                                    if (selectedChoice1 == 1 && selectedChoice2 == 6) {
                                        text = "1. Crunches:\n\n" +
                                                "\t3 sets x 12-15 reps\n" +
                                                "Keep your hands gently behind your head and focus on contracting your abdominal muscles.\n" +
                                                "\n" +
                                                "2. Leg Raises:\n\n" +
                                                "\t3 sets x 10-12 reps\n" +
                                                "Lie flat on your back and lift your legs to a 90-degree angle, engaging your lower abs.\n" +
                                                "\n" +
                                                "3. Planks:\n\n" +
                                                "\t3 sets x 20-30 seconds\n" +
                                                "Start with shorter durations and gradually increase the time you hold the plank position.\n" +
                                                "\n" +
                                                "4. Russian Twists:\n\n" +
                                                "\t3 sets x 12-15 reps (each side)\n" +
                                                "Use a light weight or a water bottle to twist and engage your obliques.";
                                    } else {
                                        if (selectedChoice1 == 2 && selectedChoice2 == 6) {
                                            text = "1. Bicycle Crunches:\n\n" +
                                                    "\t4 sets x 15-20 reps (each side)\n" +
                                                    "Perform this exercise with controlled and deliberate movements.\n" +
                                                    "\n" +
                                                    "2. Hanging Leg Raises:\n\n" +
                                                    "\t4 sets x 10-12 reps\n" +
                                                    "If possible, use a pull-up bar to hang and raise your legs.\n" +
                                                    "\n" +
                                                    "3. Planks with Leg Lifts:\n\n" +
                                                    "\t4 sets x 20-30 sec (each side)\n" +
                                                    "In plank position, lift one leg at a time while maintaining stability.\n" +
                                                    "\n" +
                                                    "4. Side Planks:\n\n" +
                                                    "\t4 sets x 20-30 sec (each side)\n" +
                                                    "Focus on keeping your body in a straight line during side planks.";
                                        } else {
                                            if (selectedChoice1 == 3 && selectedChoice2 == 6) {
                                                text = "1. Dragon Flags:\n\n" +
                                                        "\t5 sets x 6-8 reps\n" +
                                                        "Lie on a bench with your hands gripping the bench's edge and raise your legs and hips.\n" +
                                                        "\n" +
                                                        "2. Windshield Wipers:\n\n" +
                                                        "\t5 sets x 10-12 reps (each side)\n" +
                                                        "Hang from a pull-up bar and move your legs from side to side.\n" +
                                                        "\n" +
                                                        "3. Hanging Leg Raises with Twist:\n\n" +
                                                        "\t5 sets x 8-10 reps (each side)\n" +
                                                        "Perform hanging leg raises but add a twist to engage obliques.\n" +
                                                        "\n" +
                                                        "4. Plank to Push-Up:\n\n" +
                                                        "\t5 sets x 10-12 reps\n" +
                                                        "Start in a plank position and transition to a push-up position.";
                                            } else {
                                                if (selectedChoice1 == 1 && selectedChoice2 == 7) {
                                                    text = "1. Squats:\n\n" +
                                                            "\t3 sets x 10-12 reps\n" +
                                                            "Use your body weight or light dumbbells.\n" +
                                                            "\n" +
                                                            "2. Lunges:\n\n" +
                                                            "\t3 sets x 10-12 reps (each leg)\n" +
                                                            "Take a step forward and bend both knees.\n" +
                                                            "\n" +
                                                            "3. Calf Raises:\n\n" +
                                                            "\t3 sets x 15-20 reps\n" +
                                                            "\tStand on your toes, lifting your heels.\n" +
                                                            "\n" +
                                                            "4. Plank:\n\n" +
                                                            "\t3 sets x 20-30 sec\n" +
                                                            "Strengthen your core and lower back.";
                                                } else {
                                                    if (selectedChoice1 == 2 && selectedChoice2 == 7) {
                                                        text = "1. Squats:\n\n" +
                                                                "\t4 sets x 10-12 reps\n" +
                                                                "Use a barbell or challenging weights.\n" +
                                                                "\n" +
                                                                "2. Step-Ups:\n\n" +
                                                                "\t4 sets x 10-12 reps (each leg)\n" +
                                                                "Utilize a bench or platform.\n" +
                                                                "\n" +
                                                                "3. Leg Press:\n\n" +
                                                                "\t4 sets x 10-12 reps\n" +
                                                                "Use a leg press machine.\n" +
                                                                "\n" +
                                                                "4. Calf Raises:\n\n" +
                                                                "\t4 sets x 15-20 reps\n" +
                                                                "You can use additional weight.";
                                                    } else {
                                                        if (selectedChoice1 == 3 && selectedChoice2 == 7) {
                                                            text = "1. Squats:\n\n" +
                                                                    "\t5 sets x 8-10 reps\n" +
                                                                    "Use heavy barbells or weights.\n" +
                                                                    "\n" +
                                                                    "2. Deadlifts:\n\n" +
                                                                    "\t5 sets x 6-8 reps\n" +
                                                                    "Focus on proper deadlift technique.\n" +
                                                                    "\n" +
                                                                    "3. Step-Ups:\n\n" +
                                                                    "\t5 sets x 10-12 reps (each leg)\n" +
                                                                    "Increase the weight or use a higher platform.\n" +
                                                                    "\n" +
                                                                    "4. Leg Press:\n\n" +
                                                                    "\t5 sets x 8-10 reps\n" +
                                                                    "Challenge yourself on the leg press machine.\n" +
                                                                    "\n" +
                                                                    "5. Seated Calf Raises:\n\n" +
                                                                    "\t4 sets x 15-20 reps\n" +
                                                                    "Utilize a machine or dumbbells.";
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return text;
    }


    // Metoda do przejścia z GymFragment2 do kolejnego fragmentu lub wykonania innych działań
    public void switchToNextFragment() {
        // Tutaj możesz przejść do kolejnego fragmentu lub wykonać inne działania
    }

    // Metoda do ustawiania wyboru z fragmentu 1
    public void setSelectedChoice1(int choice) {
        selectedChoice1 = choice;
    }

    // Metoda do ustawiania wyboru z fragmentu 2
    public void setSelectedChoice2(int choice) {
        selectedChoice2 = choice;
    }




}