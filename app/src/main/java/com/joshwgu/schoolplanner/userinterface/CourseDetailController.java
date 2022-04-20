package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class CourseDetailController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
    }



    public void save(View view) {
    }

    public void edit(View view){

    }

    public void delete(View view) {
    }

    public void goToAssessments(View view) {
        Intent intent = new Intent(CourseDetailController.this, AssessmentController.class);
        startActivity(intent);
    }

    public void goToNotes(View view) {
        Intent intent = new Intent(CourseDetailController.this, NoteController.class);
        startActivity(intent);
    }

    public void goToInstructors(View view) {
        Intent intent = new Intent(CourseDetailController.this, InstructorController.class);
        startActivity(intent);
    }
}