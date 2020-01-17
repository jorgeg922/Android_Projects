package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView memoriesList = findViewById(R.id.memorablePlacesList);

        final List<String> listContent = new ArrayList<String>();
        final List<String> coordinates = new ArrayList<String>();
        listContent.add("Add a new place...");

        /*Intent secondIntent = getIntent();
        String newLocation = secondIntent.getStringExtra("newLocation");
        if(newLocation != null || newLocation != ""){
            listContent.add(newLocation);
        }*/

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listContent);
        memoriesList.setAdapter(myAdapter);

        memoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),secondActivity.class);
                Bundle extras = new Bundle();
                if(position == 0){
                    extras.putString("location","new");
                }else{
                    extras.putString("location",listContent.get(position));
                    extras.putString("coordinates", coordinates.get(position));
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }
}
