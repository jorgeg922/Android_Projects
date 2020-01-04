package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView counterDisplay;
    TextView operationDisplay;
    TextView scoreDisplay;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button goBtn;
    Button tryAgain;
    CountDownTimer countDown;
    String[] operationsAvail = new String[]{"13 + 13","5 + 1","9 - 4","10 * 2", "12 * 12"};
    String[] resultsAvail = new String[]{"26","6","5","20", "144"};
    int operationTracker = 0;
    int numberOfCorrectAns = 0;
    public void startGame(View view){
        showFeatures();
        countDown = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int)millisUntilFinished/1000;
                counterDisplay.setText(seconds + "S");
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();

        showOperations();
    }

    public void endGame(){
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
        tryAgain.setVisibility(tryAgain.VISIBLE);
        countDown.cancel();
    }

    public void tryAgain(View view){
        tryAgain.setVisibility(tryAgain.INVISIBLE);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        operationTracker = 0;
        numberOfCorrectAns = 0;
        countDown.start();
        showOperations();
    }
    public void checkAnswer(View view){
        int btnId = view.getId();
        Button b = (Button) findViewById(btnId);
        String answerText = b.getText().toString();

        if(answerText == resultsAvail[operationTracker]){
            numberOfCorrectAns += 1;
        }

        scoreDisplay.setText(Integer.toString(numberOfCorrectAns) + "/" + (operationTracker+1));
        operationTracker += 1;
        if(operationTracker < 5){
            showOperations();
        }else{
            endGame();
        }

    }
    public void showOperations(){
        String operationText = operationsAvail[operationTracker];
        operationDisplay.setText(operationText);
        Random r = new Random();
        Random r2 = new Random();
        int correctPos = r.nextInt((4-1) + 1) + 1;
        for(int i=1; i<=4; i++){
            Button answer = (Button) findViewById(getResources().getIdentifier("option" + i, "id",this.getPackageName()));
            if(i == correctPos){
                answer.setText(resultsAvail[operationTracker]);
            }else{
                answer.setText(Integer.toString(r2.nextInt((100-1) + 1) +1));
            }
        }
    }

    public void showFeatures(){
        counterDisplay.setVisibility(counterDisplay.VISIBLE);
        operationDisplay.setVisibility(operationDisplay.VISIBLE);
        scoreDisplay.setVisibility(scoreDisplay.VISIBLE);
        option1.setVisibility(option1.VISIBLE);
        option2.setVisibility(option2.VISIBLE);
        option3.setVisibility(option3.VISIBLE);
        option4.setVisibility(option4.VISIBLE);
        goBtn.setVisibility(option4.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterDisplay = findViewById(R.id.counterView);
        operationDisplay = findViewById(R.id.operationView);
        scoreDisplay = findViewById(R.id.scoreView);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        goBtn = findViewById(R.id.gobtn);
        tryAgain = findViewById(R.id.tryAgainbtn);

        counterDisplay.setVisibility(counterDisplay.INVISIBLE);
        operationDisplay.setVisibility(operationDisplay.INVISIBLE);
        scoreDisplay.setVisibility(scoreDisplay.INVISIBLE);
        option1.setVisibility(option1.INVISIBLE);
        option2.setVisibility(option2.INVISIBLE);
        option3.setVisibility(option3.INVISIBLE);
        option4.setVisibility(option4.INVISIBLE);
        tryAgain.setVisibility(tryAgain.INVISIBLE);
    }
}
