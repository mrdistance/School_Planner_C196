
package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Assessment;
import com.joshwgu.schoolplanner.model.Course;
import com.joshwgu.schoolplanner.model.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorController extends AppCompatActivity {

    private Intent intentFromCourse;
    private Course course;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScheduleDatabase db = new ScheduleDatabase(getApplicationContext());
        intentFromCourse = getIntent();
        List<Instructor> instructors = new ArrayList<>();
        setContentView(R.layout.activity_instructor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String courseInfo = intentFromCourse.getStringExtra("Course Info");
        String[] courseInfoParts = courseInfo.split("-");
        course = new Course(Integer.parseInt(courseInfoParts[0]), courseInfoParts[1], courseInfoParts[2], courseInfoParts[3], courseInfoParts[4], Integer.parseInt(courseInfoParts[5]));
        instructors = db.getInstructors(course);
        if (instructors != null) {
            for (Instructor instructor : instructors) {
                Button button = new Button(this);
                button.setText(instructor.toString());
                button.setGravity(Gravity.CENTER);
                button.setTextSize(20);
                button.setClickable(true);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editInstructor(v, instructor);
                    }
                });
                LinearLayout instructorsDisplayList = findViewById(R.id.instructorDisplayList);
                instructorsDisplayList.addView(button);
            }
        }
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
        goCourse.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
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
        intent.putExtra("Course Info", course.getId() + "-"+ course.getTitle() + "-" + course.getStartDate() + "-" + course.getEndDate() + "-" + course.getStatus() + "-" + course.getTermId());
        intent.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
        startActivity(intent);
    }

    public void editInstructor(View view, Instructor instructor){
        Intent intent = new Intent(InstructorController.this, InstructorDetailController.class);
        intent.putExtra("Instructor Info", instructor.getId() + "-"+ instructor.getName()  + "-" + instructor.getPhone() +  "-" + instructor.getEmail() + "-" + instructor.getCourseId());
        intent.putExtra("Course Info", course.getId() + "-"+ course.getTitle() + "-" + course.getStartDate() + "-" + course.getEndDate() + "-" + course.getStatus() + "-" + course.getTermId());
        intent.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
        startActivity(intent);
    }

    public void refresh(View view){
        this.recreate();
    }
}