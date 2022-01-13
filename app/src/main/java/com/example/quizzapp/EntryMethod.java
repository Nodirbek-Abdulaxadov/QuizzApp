package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to Entry Method
 */
public class EntryMethod extends AppCompatActivity {

    //declare questions Array
    String[] questions = {
            "What is the output of this code?\nint x = 9;\nint y = 4;\nint z = x++ - y-- + (x + y);\nConsole.Write(--z%2);",
            "What is the output of this code?\nint x = 10;\nint y = x++;\nConsole.Write(y++ + ++x);",
            "What is the output of this code?\nstring str = \"Hello World!\";\nint x = str.Length;\nConsole.Write(x % 5);",
            "What is the output of this code?\nint i = 0;\nfor(; i<=5; i++)\n{\n\tif(i>=4) break;\n}\nConsole.Write(i);",
            "What is the output of this code?\nsbyte s = 127;\nConsole.Write(s+1);"
    };

    //declare answers Array
    String[] answers = { "1", "22", "2", "4", "128"};

    //declare variables
    int index = 1;
    int correct = 0;

    //Declare elements
    String userName;
    TextView questionText;
    TextView questionsState;
    EditText userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entr_method);

        //get userName from MainActivity class using getIntent()
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");

        //set values
        questionText = findViewById(R.id.questionText);
        userAnswer = findViewById(R.id.userAnswer);
        questionsState = findViewById(R.id.questionsState);
        Button nextButton = findViewById(R.id.nextButton);
        Button homeButton = findViewById(R.id.homeButton);

        //filling default view
        fillView(0);

        /**
         * This method is called when the nextButton button is clicked.
         */
        nextButton.setOnClickListener(view -> {

            //check user input not empty
            if (userAnswer.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), userName + ", Please enter your answer!", Toast.LENGTH_SHORT).show();
            }
            else {
                //main part - moving the next question or finish
                if (index < questions.length) {
                    checkAnswer(findViewById(R.id.userAnswer).toString(), index - 1);
                    fillView(index);
                    index++;
                } else {
                    //check last test
                    checkAnswer(findViewById(R.id.userAnswer).toString(), index - 1);

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
     * This method fills view with question
     */
    private void fillView(int i) {
        questionsState.setText("Question " + String.valueOf(i+1) + "/5");
        userAnswer.setText("");
        questionText.setText(questions[i]);
    }

    /**
     * This method checks answers
     */
    private void checkAnswer(String answer, int questionId) {
        if (userAnswer.getText().toString().equals(answers[questionId])) {
            correct++;
        }
    }
}
