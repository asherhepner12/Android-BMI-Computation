package com.example.bmicomputation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView, bmiSuggestion;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create UI
        resultTextView = findViewById(R.id.resultTextView);
        bmiSuggestion = findViewById(R.id.bmiSuggestion);
        backButton = findViewById(R.id.backButton);

        calculateResult();

        // Set a listener to go back to the MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);

                startActivity(intent);}
        });
    }

    //Calculate the BMI
    private void calculateResult() {
        //Get the parameters from user input in MainActivity
        Intent bmiData = getIntent();
        String heightFtString = bmiData.getStringExtra("heightFeet");
        String heightInString = bmiData.getStringExtra("heightInches");
        String weightLbsString = bmiData.getStringExtra("weightLbs");

        //Combine heightFt and heightIn to get height in inches as a double
        double height = (Double.parseDouble(heightFtString) * 12) + Double.parseDouble(heightInString);
        //Convert weight string to a double
        double weight = Double.parseDouble(weightLbsString);

        //Use BMI formula and display BMI
        double bmi = weight / (height * height) * 703;
        resultTextView.setText("Your BMI: " + String.format("%.2f", bmi));

        //Determine BMI suggestion
        if (bmi < 18.5){
            bmiSuggestion.setText("You are too slim! you need more sandwich!");
        }
        else if (bmi < 24.9){
            bmiSuggestion.setText("Your body is in good shape!");
        }
        else if (bmi < 29.9){
            bmiSuggestion.setText("Watch out! You are slightly overweight!");
        }
        else{
            bmiSuggestion.setText("You need to lose a couple of pounds!");
        }
    }

}