package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class CourseController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(CourseController.this, CourseDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on course in scroll view and enter course detail activity with prefilled data
    public void editCourse(View view){

    }
}