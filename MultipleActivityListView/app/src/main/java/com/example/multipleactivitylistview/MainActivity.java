package com.example.multipleactivitylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> myFriends = new ArrayList<String>();
        ListView mainListView = findViewById(R.id.mainListView);

        myFriends.add("Nika");
        myFriends.add("Vlad");
        myFriends.add("Joe");
        myFriends.add("Liza");
        myFriends.add("Jair");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myFriends);

        mainListView.setAdapter(myAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),secondActivity.class);
                intent.putExtra("name",myFriends.get(position));
                startActivity(intent);
            }
        });
    }
}
