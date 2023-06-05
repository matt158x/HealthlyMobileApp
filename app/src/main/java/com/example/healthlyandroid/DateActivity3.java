package com.example.healthlyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DateActivity3 extends AppCompatActivity {

    private DatePicker datePicker;
    private Button nextButton;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date3);

        datePicker = findViewById(R.id.datePicker);
        nextButton = findViewById(R.id.next_button2);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        // Ustawienie wartości domyślnej dla DatePicker na 01.01.2000
        int defaultYear = 2000;
        int defaultMonth = 0; // styczeń (numeracja miesięcy zaczyna się od zera)
        int defaultDay = 1;

        // Ustawienie wartości domyślnej dla DatePicker
        datePicker.init(defaultYear, defaultMonth, defaultDay, null);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndSaveAge();
            }
        });
    }

    private void calculateAndSaveAge() {
        int selectedDay = datePicker.getDayOfMonth();
        int selectedMonth = datePicker.getMonth();
        int selectedYear = datePicker.getYear();

        Calendar currentDate = Calendar.getInstance();
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentYear = currentDate.get(Calendar.YEAR);

        int age = currentYear - selectedYear;
        if (currentMonth < selectedMonth) {
            age--;
        } else if (currentMonth == selectedMonth && currentDay < selectedDay) {
            age--;
        }

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference.child("users").child(userId).child("age").setValue(age);
        }
        openInfoActivity();
    }

    private void openInfoActivity() {
        Intent intent = new Intent(DateActivity3.this, InfoActivity4.class);
        startActivity(intent);
        finish();
    }
}