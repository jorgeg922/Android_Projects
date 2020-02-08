package com.example.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class EditYourNote extends AppCompatActivity {
    EditText editText;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);

        editText = findViewById(R.id.editText2);
        intent = getIntent();
        Log.i("Place Number Passed: " , Integer.toString(intent.getIntExtra("noteNumber",0)));
        Log.i("New Note: " , String.valueOf(intent.getBooleanExtra("newNote",false)));
        if(intent.getIntExtra("noteNumber",0) > 0){
            editText.setText(MainActivity.noteContent.get(intent.getIntExtra("noteNumber",0)));
        }
    }

    @Override
    public void onBackPressed() {
        String[] noteContent;
        String delimiter = "\n";
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.mynotesapp", Context.MODE_PRIVATE);

        if(editText.getText().toString().matches("")){
            Log.i("Note Content","Nothing was entered");
        }else{
          noteContent = editText.getText().toString().split(delimiter);
          Log.i("title",noteContent[0]);
          if(intent.getBooleanExtra("newNote",false)){
              MainActivity.storedNotesArray.add(noteContent[0]);
              MainActivity.noteContent.add(editText.getText().toString());
          }else{
              MainActivity.storedNotesArray.set(intent.getIntExtra("noteNumber",0),noteContent[0]);
              MainActivity.noteContent.set(intent.getIntExtra("noteNumber",0),editText.getText().toString());
          }
            try{
                sharedPreferences.edit().putString("notesTitles", ObjectSerializer.serialize(MainActivity.storedNotesArray)).apply();
                sharedPreferences.edit().putString("notesContents", ObjectSerializer.serialize(MainActivity.noteContent)).apply();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //MainActivity.displayNotesArray.add("Test Title");
        MainActivity.arrayAdapter.notifyDataSetChanged();

        super.onBackPressed();
    }
}
