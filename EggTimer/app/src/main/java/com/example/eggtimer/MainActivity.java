package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerDisplay;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerDisplay.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO");
        counterIsActive = false;
    }
    public void btnCountDown(View view){
        if(counterIsActive){
            resetTimer();
        }else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP");


            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                    Log.i("Finished", "Done");
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - minutes * 60;
        String secondsS;

        if(seconds < 10){
            secondsS = "0" + Integer.toString(seconds);
        }else{
            secondsS = Integer.toString(seconds);
        }

        String timer = Integer.toString(minutes) + ":" + secondsS;
        timerDisplay.setText(timer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerSeekBar.setMax(600);
        goButton = findViewById(R.id.timerBtn);
       timerDisplay = findViewById(R.id.timerDisplay);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
