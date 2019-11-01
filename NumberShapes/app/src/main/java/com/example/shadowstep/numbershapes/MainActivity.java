package com.example.shadowstep.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    class Number{

        int a;

        public boolean isTriangular(){
            int testCase = a * 2;
            int squareRoot = (int) Math.rint(Math.sqrt(testCase));
            int result = (squareRoot*(squareRoot + 1))/2;
            return (result == a);
        }

        public boolean isSquare(){
            double testCase = Math.sqrt(a);
            return (testCase - Math.floor(testCase) == 0);

        }
    }
    public void testNumber(View view){

        EditText numString = (EditText) findViewById(R.id.userNumber);

        if(numString.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
        }else {


            int userInput = Integer.parseInt(numString.getText().toString());

            Number myNumber = new Number();
            myNumber.a = userInput;

            boolean triangular = myNumber.isTriangular();
            boolean square = myNumber.isSquare();

            if (triangular && square) {
                Toast.makeText(this, userInput + " is both", Toast.LENGTH_SHORT).show();
            } else if (triangular) {
                Toast.makeText(this, userInput + " is triangular", Toast.LENGTH_SHORT).show();
            } else if (square) {
                Toast.makeText(this, userInput + " is square", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, userInput + " is neither", Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
