package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class NoteController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public void addNote(View view) {
        Intent intent = new Intent(NoteController.this, NoteDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on note in scroll view and enter note detail activity with prefilled data
    public void editNote(View view){

    }
}