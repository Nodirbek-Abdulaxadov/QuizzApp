package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to Multiple Choice
 */
public class MultipleChoice extends AppCompatActivity {

    private Bundle savedInstanceState;

    //declare questions Array
    String[] questions = {
            "What values the bool type variables take in C#?",
            "Which are the reference type values?",
            "How character is used to insert COMMENTS in C# code?"
    };

    //declare answers Array
    String[][] answers = {
            {
                    "True", "false", "true", "False"
            },
            {
                    "delegate", "string", "struct", "class"
            },
            {
                    "//", "#", "/**/" , "?"
            }
    };

    //Declare elements
    TextView questionText;
    TextView questionsState;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    //Declare variables
    int index = 1;
    int correct = 0;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        //get userName from MainActivity class using getIntent()
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");

        questionText = findViewById(R.id.questionText);
        questionsState = findViewById(R.id.questionsState);
        Button nextButton = findViewById(R.id.nextButton);
        Button homeButton = findViewById(R.id.homeButton);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        //filling default view
        fillView(0);

        /**
         * This method is called when the nextButton button is clicked.
         */
        nextButton.setOnClickListener(view -> {

            //check user selection
            if (!anyIsChecked()) {
                Toast.makeText(getApplicationContext(), userName + ", Please select one or more answer!", Toast.LENGTH_SHORT).show();
            }
            else {
                //main part - moving the next question or finish
                if (index < 3) {
                    if (checkAnswer(index)) {
                        correct++;
                    }
                    fillView(index);
                    index++;
                } else {
                    if (checkAnswer(index)) {
                        correct++;
                    }
                    //show user result
                    Toast.makeText(getApplicationContext(), userName + ", your total score is  " + String.valueOf(correct), Toast.LENGTH_SHORT).show();

                    //showing activity_main
                    Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(homeIntent);
                }
            }

            //at last change nextButton text to Finish
            if (index == 3){
                nextButton.setText("Finish");
            }

        });

        /**
         * This method is called when the homeButton button is clicked.
         */
        homeButton.setOnClickListener(view -> {
            //showing activity_test
            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(homeIntent);
        });

    }

    /**
     * This method fills view with question and answers
     */
    private void fillView(int i) {
        disableChecks();
        questionsState.setText("Question " + String.valueOf(i+1) + "/3");
        questionText.setText(questions[i]);
        checkBox1.setText(answers[i][0]);
        checkBox2.setText(answers[i][1]);
        checkBox3.setText(answers[i][2]);
        checkBox4.setText(answers[i][3]);
    }

    /**
     * This method checks answers
     */
    private boolean checkAnswer(int questionIndex) {

        //check first question
        if (!checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && !checkBox4.isChecked() && questionIndex == 1) {
            return true;
        }
        //check second question
        if (checkBox1.isChecked() && checkBox2.isChecked() && !checkBox1.isChecked() && checkBox4.isChecked() && questionIndex == 2) {
            return true;
        }
        //check last question
        return checkBox1.isChecked() && !checkBox2.isChecked() && checkBox3.isChecked() && !checkBox4.isChecked() && questionIndex == 3;
    }

    /**
     * This method disable checks all radioButtons
     */
    private void disableChecks() {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
    }

    /**
     * This method check any checkBox select or no
     */
    private boolean anyIsChecked() {
        return checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked();
    }
}
