package com.example.mynotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> storedNotesArray;
    //static ArrayList<String> displayNotesArray;
    static ArrayList<String> noteContent = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView notesListView = findViewById(R.id.notesListView);

        storedNotesArray = new ArrayList<>();
        //displayNotesArray = new ArrayList<>();

        final SharedPreferences mySharedPreferences = this.getSharedPreferences("com.example.mynotesapp", Context.MODE_PRIVATE);

        try {
            storedNotesArray = (ArrayList<String>) ObjectSerializer.deserialize(mySharedPreferences.getString("notesTitles", ObjectSerializer.serialize(new ArrayList<String>())));
            noteContent = (ArrayList<String>) ObjectSerializer.deserialize(mySharedPreferences.getString("notesContents", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (storedNotesArray.size() <= 0) {
            storedNotesArray.add("Create a new note...");
            noteContent.add("never visible");
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, storedNotesArray);
        notesListView.setAdapter(arrayAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditYourNote.class);
                intent.putExtra("noteNumber", position);
                if (position == 0) {
                    intent.putExtra("newNote", true);
                } else {
                    intent.putExtra("newNote", false);
                }
                startActivity(intent);
            }
        });

        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int rowFocus = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Note")
                        .setMessage("Would you like to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // setLanguage("English");
                                //storedNotesArray.remove(which);
                                Log.i("remove position:",Integer.toString(rowFocus));
                                storedNotesArray.remove(rowFocus);
                                noteContent.remove(rowFocus);
                                arrayAdapter.notifyDataSetChanged();
                                try{
                                    mySharedPreferences.edit().putString("notesTitles", ObjectSerializer.serialize(storedNotesArray)).apply();
                                    mySharedPreferences.edit().putString("notesContents", ObjectSerializer.serialize(noteContent)).apply();
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //setLanguage("Spanish");
                            }
                        })
                        .show();

                return true;
            }
        });
    }
}
