package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This app displays an order form to Test
 */
public class TestActivity extends AppCompatActivity {

    //creating list of Questions with Question class
    ArrayList<Question> questionList;

    //Declare local values
    int indexQuestion = 0;
    String userName = null;
    int correctAnswers = 0;

    //Declare elements
    TextView questionText;
    TextView questionState;
    RadioGroup radioGroup;
    RadioButton radioAnswer1;
    RadioButton radioAnswer2;
    RadioButton radioAnswer3;
    RadioButton radioAnswer4;
    Button nextButton;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //get userName from MainActivity class using getIntent()
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");

        //set values
        questionText = findViewById(R.id.questionText);
        questionState = findViewById(R.id.questionsState);
        radioGroup = findViewById(R.id.answersGroup);
        radioAnswer1 = findViewById(R.id.radioAnswer1);
        radioAnswer2 = findViewById(R.id.radioAnswer2);
        radioAnswer3 = findViewById(R.id.radioAnswer3);
        radioAnswer4 = findViewById(R.id.radioAnswer4);
        nextButton = findViewById(R.id.nextButton);
        homeButton = findViewById(R.id.homeButton);

        //filling default view
        fillQuestionsList();
        fillTextViews(0);
        indexQuestion++;

        /**
         * This method is called when the nextButton button is clicked.
         */
        nextButton.setOnClickListener(view -> {

            //check selection
            if( radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()))==-1)
            {
                Toast.makeText(getApplicationContext(), userName + ", Please select one choice!", Toast.LENGTH_SHORT).show();
                return;
            }

            //main part - moving the next question or finish
            if (indexQuestion < questionList.size()) {
                //fill question and answers texts
                fillTextViews(indexQuestion);

                //get selected radioButton id
                int id = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));

                //checking answer
                checkAnswer(indexQuestion-1, id);

                //index of question iteration
                indexQuestion++;

                //at last change nextButton text to Finish
                if (indexQuestion == questionList.size()){
                    nextButton.setText("Finish");
                }
            }

            //get total result
            else {
                //check last test
                int id = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                checkAnswer(indexQuestion - 1, id);

                //show user result
                Toast.makeText(getApplicationContext(), userName + ", your total score is  " + String.valueOf(correctAnswers), Toast.LENGTH_SHORT).show();

                //showing activity_main
                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
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
     * This method fills questionList with question
     */
    private void fillQuestionsList(){
        //create new list
        questionList = new ArrayList<>();

        //create new Question object
        Question question1 = new Question();

        //set Question object props
        question1.questionText = "Which of the following methods is an entry point in the C# console program?";
        question1.answers.add("public static void Program()");
        question1.answers.add("public static void Main()");
        question1.answers.add("public static void main()");
        question1.answers.add("public string void Main()");
        question1.correctAnswerId = 1;
        //add question object to questionList
        questionList.add(question1);


        //create new Question object
        Question question2 = new Question();

        //set Question object props
        question2.questionText = "Which of the followings are value types in C#?";
        question2.answers.add("Int32");
        question2.answers.add("Double");
        question2.answers.add("Decimal");
        question2.answers.add("All of the above");
        question2.correctAnswerId = 3;
        //add question object to questionList
        questionList.add(question2);


        //create new Question object
        Question question3 = new Question();

        //set Question object props
        question3.questionText = "Which of the following is a reference type in C#?";
        question3.answers.add("String");
        question3.answers.add("Boolean");
        question3.answers.add("Long");
        question3.answers.add("None of the above");
        question3.correctAnswerId = 0;
        //add question object to questionList
        questionList.add(question3);


        //create new Question object
        Question question4 = new Question();

        //set Question object props
        question4.questionText = "Struct is a _____";
        question4.answers.add("Reference type");
        question4.answers.add("Value type");
        question4.answers.add("Class type");
        question4.answers.add("String type");
        question4.correctAnswerId = 1;
        //add question object to questionList
        questionList.add(question4);


        //create new Question object
        Question question5 = new Question();

        //set Question object props
        question5.questionText = "All one dimensional arrays start with _____ index.";
        question5.answers.add("0.0");
        question5.answers.add("1");
        question5.answers.add("0");
        question5.answers.add("-1");
        question5.correctAnswerId = 2;
        //add question object to questionList
        questionList.add(question5);
    }

    /**
     * This method fills view with question and answers
     */
    private void fillTextViews(int id){

        disableChecks();
        questionState.setText("Question " + String.valueOf(id + 1) + "/5");

        //get Question object with id
        Question question = questionList.get(id);

        //setText textViews with current question props
        questionText.setText(question.questionText);
        radioAnswer1.setText(question.answers.get(0));
        radioAnswer2.setText(question.answers.get(1));
        radioAnswer3.setText(question.answers.get(2));
        radioAnswer4.setText(question.answers.get(3));
    }

    /**
     * This method checks answers
     */
    private void checkAnswer(int questionId, int answer) {
        //get Question object with id
        Question question = questionList.get(questionId);

        //check answerId equals to current question's correctAnswerId or not
        if (answer == question.correctAnswerId) {
            correctAnswers++;
        }
    }

    /**
     * This method disable checks all radioButtons
     */
    private void disableChecks() {
        radioAnswer1.setChecked(false);
        radioAnswer2.setChecked(false);
        radioAnswer3.setChecked(false);
        radioAnswer4.setChecked(false);
    }
}
