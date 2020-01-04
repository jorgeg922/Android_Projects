package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> myFriends = new ArrayList<String>();
        ListView myListView = findViewById(R.id.myListView);

        myFriends.add("Vlad");
        myFriends.add("Ihsan");
        myFriends.add("Steven");
        myFriends.add("Jeffrey");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myFriends);

        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context,"" + myFriends.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}
