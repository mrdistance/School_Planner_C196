package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class CourseDetailController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu adds items to action bar if present
        getMenuInflater().inflate(R.menu.course_detail_back_menu, menu);

        MenuItem home = menu.findItem(R.id.home_screen);
        Intent goHome = new Intent(this, MainActivity.class);
        home.setIntent(goHome);

        MenuItem term = menu.findItem(R.id.term_screen);
        Intent goTerm = new Intent(this, TermController.class);
        term.setIntent(goTerm);

        MenuItem course = menu.findItem(R.id.course_screen);
        Intent goCourse = new Intent(this, CourseController.class);
        course.setIntent(goCourse);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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