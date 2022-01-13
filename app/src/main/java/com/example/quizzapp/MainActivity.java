package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to Main
 */
public class MainActivity extends AppCompatActivity {

    //local variable
    String entry = "";
    String testMethod = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading elements
        Button startButton = findViewById(R.id.startButton);
        TextView userName = findViewById(R.id.userName);
        Spinner testMethodSpinner = findViewById(R.id.testMethodSpinner);

        /**
         * This method get user Spinner selection
         */
        testMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                testMethod = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /**
         * This method is called when the nextButton start is clicked.
         */
        startButton.setOnClickListener(view -> {
            //get userName input
            entry = userName.getText().toString();

            //check userName
            if (entry.trim().equals("")) {
                entry = "User";
            }

            //select testMethod
            switch (testMethod)
            {
                //showing activity_test
                case "One choice": {
                    Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                    intent.putExtra("name", entry);
                    startActivity(intent);
                } break;

                //showing activity_mixed
                case "Multiple choice": {
                    Intent intent = new Intent(getApplicationContext(), MultipleChoice.class);
                    intent.putExtra("name", entry);
                    startActivity(intent);
                } break;

                //showing activity_entry_method
                case "Write answer": {
                    Intent intent = new Intent(getApplicationContext(), EntryMethod.class);
                    intent.putExtra("name", entry);
                    startActivity(intent);
                } break;
            }
        });
    }
}