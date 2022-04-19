package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class TermController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
    }

    public void addTerm(View view) {
        Intent intent = new Intent(TermController.this, TermDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on term in scroll view and enter term detail activity with prefilled data
    public void editTerm(View view){

    }
}