
package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class InstructorController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu adds items to action bar if present
        getMenuInflater().inflate(R.menu.instructor_back_menu, menu);

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

    public void addInstructor(View view) {
        Intent intent = new Intent(InstructorController.this, InstructorDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on instructor in scroll view and enter instructor detail activity with prefilled data
    public void editTInstructor(View view){

    }
}