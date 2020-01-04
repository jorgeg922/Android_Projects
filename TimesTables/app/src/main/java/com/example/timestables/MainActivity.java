package com.example.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView timesNumber;
    final List<Integer> timesListOfNumbers = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12));

    public void changeAdapter(List<Integer> resultList){
        ArrayAdapter<Integer> myAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,resultList);
        ListView timesListView = findViewById(R.id.timesListView);
        timesListView.setAdapter(myAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timesNumber = findViewById(R.id.timesNumber); //textview

        SeekBar timesSeekBar = findViewById(R.id.timesSeekBar);
        timesSeekBar.setMax(12);

        final List<Integer> timesResult = new ArrayList<Integer>();

        timesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                timesNumber.setText(Integer.toString(progress));

                timesResult.clear();
                for(int i : timesListOfNumbers){
                    timesResult.add(progress * i);
                }
                changeAdapter(timesResult);
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
