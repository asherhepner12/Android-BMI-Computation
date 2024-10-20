package com.example.bmicomputation;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText heightFeet, heightInches, weightLbs;
    private Button computeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create UI
        heightFeet = findViewById(R.id.heightFeet);
        heightInches = findViewById(R.id.heightInches);
        weightLbs = findViewById(R.id.weightLbs);
        computeButton = findViewById(R.id.computeButton);


        // Set a listener  for the calculate button
        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert user input to strings
                String heightFtString = heightFeet.getText().toString();
                String heightInString = heightInches.getText().toString();
                String weightLbsString = weightLbs.getText().toString();

                //Check if any inputs are empty and stop the computation
                if (heightFtString.isEmpty() || heightInString.isEmpty() || weightLbsString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Pass parameters to ResultActivity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("heightFeet", heightFtString);
                intent.putExtra("heightInches",heightInString);
                intent.putExtra("weightLbs", weightLbsString);
                startActivity(intent);}
        });
    }

    //Clear inputs after pressing back button
    @Override
    protected void onResume() {
        super.onResume();
        heightFeet.setText("");
        heightInches.setText("");
        weightLbs.setText("");
    }

}