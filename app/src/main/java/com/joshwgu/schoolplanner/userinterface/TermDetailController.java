package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class TermDetailController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
    }


    public void goToCourses(View view) {
        Intent intent = new Intent(TermDetailController.this, CourseDetailController.class);
        startActivity(intent);
    }

    public void delete(View view){

    }

    public void save(View view){

    }


}