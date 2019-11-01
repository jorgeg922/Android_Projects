package com.example.shadowstep.highorlow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int generatedNumber;

    public void generateNumber(){

        Random rand = new Random();
        generatedNumber = rand.nextInt(21);
    }

    public void highOrLow(View view){

        EditText boxString = (EditText) findViewById(R.id.userInput);

        int userNumber = Integer.parseInt(boxString.getText().toString());

        if(userNumber == generatedNumber){
            Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show();
            generateNumber();
        }else if(userNumber < generatedNumber){
            Toast.makeText(this, "Too low", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Too high", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateNumber();
    }
}
