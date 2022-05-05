package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Assessment;
import com.joshwgu.schoolplanner.model.Course;
import com.joshwgu.schoolplanner.model.Instructor;

public class InstructorDetailController extends AppCompatActivity {

    private static final String TAG = "InstructorDetailController";
    private EditText mNameText;
    private EditText mPhoneText;
    private EditText mEmailText;
    private boolean saved;
    private ScheduleDatabase db;
    private Intent intentFromInstructor;
    private Instructor instructor;
    private String instructorInfo;
    private String courseInfo;
    private TextView mNameTF;
    private TextView mPhoneTF;
    private TextView mEmailTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new ScheduleDatabase(getApplicationContext());
        intentFromInstructor= getIntent();
        setContentView(R.layout.activity_instructor_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        courseInfo = intentFromInstructor.getStringExtra("Course Info");
        instructorInfo = intentFromInstructor.getStringExtra("Instructor Info");
        saved = false;
        mNameText = (EditText) findViewById(R.id.instructorNameText);
        mPhoneText = (EditText) findViewById(R.id.instructorPhoneText);
        mEmailText = (EditText) findViewById(R.id.instructorEmailText);
        mNameTF = (TextView) findViewById(R.id.instructorNameTF);
        mPhoneTF = (TextView) findViewById(R.id.instructorPhoneTF);
        mEmailTF = (TextView) findViewById(R.id.instructorEmailTF);
        Log.d(TAG, "Made it to if instructor info not null");

        if(instructorInfo != null){
            String[] instructorInfoParts = instructorInfo.split("-");
            instructor = new Instructor(Integer.parseInt(instructorInfoParts[0]), instructorInfoParts[1], instructorInfoParts[2], instructorInfoParts[3], Integer.parseInt(instructorInfoParts[4]));
            mNameText.setText(instructor.getName());
            mPhoneText.setText(instructor.getPhone());
            mEmailText.setText(instructor.getEmail());
            mNameText.setEnabled(false);
            mPhoneText.setEnabled(false);
            mEmailText.setEnabled(false);
            saved = true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu adds items to action bar if present
        getMenuInflater().inflate(R.menu.instructor_detail_back_menu, menu);
        MenuItem home = menu.findItem(R.id.home_screen);
        Intent goHome = new Intent(this, MainActivity.class);
        home.setIntent(goHome);
        MenuItem term = menu.findItem(R.id.term_screen);
        Intent goTerm = new Intent(this, TermController.class);
        term.setIntent(goTerm);
        MenuItem course = menu.findItem(R.id.course_screen);
        Intent goCourse = new Intent(this, CourseController.class);
        goCourse.putExtra("Term Info", intentFromInstructor.getStringExtra("Term Info"));
        course.setIntent(goCourse);
        MenuItem instructor = menu.findItem(R.id.instructor_screen);
        Intent goInstructor = new Intent(this, InstructorController.class);
        goInstructor.putExtra("Term Info", intentFromInstructor.getStringExtra("Term Info"));
        goInstructor.putExtra("Course Info", intentFromInstructor.getStringExtra("Course Info"));
        instructor.setIntent(goInstructor);
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
        if(validateFields()) {
            if (instructor != null) {
                instructor.setName(mNameText.getText().toString());
                instructor.setPhone(mPhoneText.getText().toString());
                instructor.setEmail(mEmailText.getText().toString());
                db.updateInstructor(instructor);
            } else {
                instructor = new Instructor(mNameText.getText().toString(), mPhoneText.getText().toString(), mEmailText.getText().toString(), Integer.parseInt(courseInfo.split("-")[0]));
                instructor.setId((int) db.addInstructor(instructor));

            }
            mNameText.setEnabled(false);
            mPhoneText.setEnabled(false);
            mEmailText.setEnabled(false);
            saved = true;
        }else{
            if(mNameText.getText().toString().isEmpty()){
                mNameTF.setTextColor(Color.RED);
            }
            if(mPhoneText.getText().toString().isEmpty()){
                mPhoneTF.setTextColor(Color.RED);
            }
            if(mEmailText.getText().toString().isEmpty()){
                mEmailTF.setTextColor(Color.RED);
            }
        }
    }




    private boolean validateFields(){
        if(mNameText.getText().toString().isEmpty() || mPhoneText.getText().toString().isEmpty() || mEmailText.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    public void edit(View view){
        mNameText.setEnabled(true);
        mPhoneText.setEnabled(true);
        mEmailText.setEnabled(true);
        saved = false;
    }

    public void delete(View view) {
        if(instructor != null) {
            db.deleteInstructor(instructor);
            Intent intent = new Intent(InstructorDetailController.this, InstructorController.class);
            intent.putExtra("Term Info", intentFromInstructor.getStringExtra("Term Info"));
            intent.putExtra("Course Info", intentFromInstructor.getStringExtra("Course Info"));
            startActivity(intent);
        }
    }
}