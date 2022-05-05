package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Course;
import com.joshwgu.schoolplanner.model.Term;

import java.util.ArrayList;
import java.util.List;

public class CourseController extends AppCompatActivity {

    private Intent intentFromTerm;
    private Term term;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScheduleDatabase db = new ScheduleDatabase(getApplicationContext());
        intentFromTerm = getIntent();
        List<Course> courses = new ArrayList<>();
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String termInfo = intentFromTerm.getStringExtra("Term Info");
        String[] termInfoParts = termInfo.split("-");
        term = new Term(Integer.parseInt(termInfoParts[0]), termInfoParts[1], termInfoParts[2], termInfoParts[3]);
        courses = db.getCourses(term);
        if (courses != null) {
            for (Course course : courses) {
                Button button = new Button(this);
                button.setText(course.toString());
                button.setGravity(Gravity.CENTER);
                button.setTextSize(20);

                button.setClickable(true);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCourse(v, course);
                    }
                });
                LinearLayout coursesDisplayList = findViewById(R.id.coursesDisplayList);
                coursesDisplayList.addView(button);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu adds items to action bar if present
        getMenuInflater().inflate(R.menu.course_back_menu, menu);
        MenuItem home = menu.findItem(R.id.home_screen);
        Intent goHome = new Intent(this, MainActivity.class);
        home.setIntent(goHome);
        MenuItem term = menu.findItem(R.id.term_screen);
        Intent goTerm = new Intent(this, TermController.class);
        term.setIntent(goTerm);

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

    public void addCourse(View view) {
        Intent intent = new Intent(CourseController.this, CourseDetailController.class);
        intent.putExtra("Term Info", intentFromTerm.getStringExtra("Term Info"));
        startActivity(intent);
    }

    //todo add code to click on course in scroll view and enter course detail activity with prefilled data
    public void editCourse(View view, Course course){
        Intent intent = new Intent(CourseController.this, CourseDetailController.class);
        intent.putExtra("Course Info", course.getId() + "-"+ course.getTitle() + "-" + course.getStartDate() + "-" + course.getEndDate() + "-" + course.getStatus() + "-" + course.getTermId());
        intent.putExtra("Term Info", intentFromTerm.getStringExtra("Term Info"));
        startActivity(intent);
    }

    public void refresh(View view){
        this.recreate();
    }
}