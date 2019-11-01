package com.example.shadowstep.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public void convertCurr(View view){

        EditText currStr = (EditText) findViewById(R.id.currencyInput);

        int currNumber = Integer.parseInt(currStr.getText().toString());

        double convertedAmount = currNumber * 1.3;

        Toast.makeText(this, "Total is " + df.format(convertedAmount), Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
