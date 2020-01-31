package com.example.savingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Context context = MainActivity.this;
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.savingdemo", Context.MODE_PRIVATE);

            ArrayList<String> friends = new ArrayList<>();

            friends.add("Nika");
            friends.add("Joe");
            friends.add("Jair");
        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
            //sharedPreferences.edit().putString("username","jg").apply();
            //String username = sharedPreferences.getString("username","");
            //Log.i("USER", username);
        }catch(Exception e){
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();

            try {
                newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
            }catch(Exception e){
                e.printStackTrace();
            }

        Log.i("new friends", newFriends.toString());




    }
}
