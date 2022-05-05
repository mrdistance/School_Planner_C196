package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Course;

import java.util.Calendar;

public class CourseDetailController extends AppCompatActivity {

    private static final String TAG = "CourseDetailController";
    private TextView mStartDate;
    private TextView mEndDate;
    private TextView mNameText;
    private Spinner mProgressSpinner;
    private ScheduleDatabase db;
    private Intent intentFromCourse;
    private String courseInfo;
    private SpinnerAdapter adapter;
    private String termInfo;
    private boolean saved;
    private Course course;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new ScheduleDatabase(getApplicationContext());
        intentFromCourse = getIntent();
        setContentView(R.layout.activity_course_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_dropdown_item);
        courseInfo = intentFromCourse.getStringExtra("Course Info");
        termInfo = intentFromCourse.getStringExtra("Term Info");
        saved = false;
        mStartDate = (TextView) findViewById(R.id.startDateText);
        mEndDate = (TextView) findViewById(R.id.endDateText);
        mNameText = (TextView) findViewById(R.id.assessmentNameText);
        mProgressSpinner = (Spinner) findViewById(R.id.progressSpinner);
        mProgressSpinner.setAdapter(adapter);


        if(courseInfo != null){
            String[] courseInfoParts = courseInfo.split("-");
            course = new Course(Integer.parseInt(courseInfoParts[0]), courseInfoParts[1], courseInfoParts[2], courseInfoParts[3], courseInfoParts[4], Integer.parseInt(courseInfoParts[5]));
            mNameText.setText(course.getTitle());
            mStartDate.setText(course.getStartDate());
            mEndDate.setText(course.getEndDate());
            mProgressSpinner.setSelection(course.getIntStatus());
            mProgressSpinner.setEnabled(false);
            mNameText.setEnabled(false);
            mStartDate.setEnabled(false);
            mEndDate.setEnabled(false);
            saved = true;
        }
        mStartDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(CourseDetailController.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
                        mStartDateSetListener, year, month, day);
                dialog.show();
                dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mStartDate.setText(date);
            }
        };

        mEndDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(CourseDetailController.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
                        mEndDateSetListener, year, month, day);
                dialog.show();
                dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mEndDate.setText(date);
            }
        };



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



    public void save(View view) {
        if(validateFields()) {
            if (course != null) {
                course.setTitle(mNameText.getText().toString());
                course.setStartDate(mStartDate.getText().toString());
                course.setEndDate(mEndDate.getText().toString());
                course.setStatus(mProgressSpinner.getSelectedItem().toString());
                db.updateCourse(course);
            } else {
                course = new Course(mNameText.getText().toString(), mStartDate.getText().toString(), mEndDate.getText().toString(), mProgressSpinner.getSelectedItem().toString(), Integer.parseInt(termInfo.split("-")[0]));
                course.setId((int) db.addCourse(course));

            }
            mNameText.setEnabled(false);
            mStartDate.setEnabled(false);
            mEndDate.setEnabled(false);
            mProgressSpinner.setEnabled(false);
            saved = true;
        }else{
            if(mStartDate.getText().toString().isEmpty()){
                mStartDate.setHintTextColor(Color.RED);
            }
            if(mEndDate.getText().toString().isEmpty()){
                mEndDate.setHintTextColor(Color.RED);
            }
            if(mNameText.getText().toString().isEmpty()){
                mNameText.setHintTextColor(Color.RED);
            }
        }
    }

    private boolean validateFields(){
        if(mStartDate.getText().toString().isEmpty() || mEndDate.getText().toString().isEmpty() || mNameText.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    public void edit(View view){
        mNameText.setEnabled(true);
        mStartDate.setEnabled(true);
        mEndDate.setEnabled(true);
        mProgressSpinner.setEnabled(true);
        saved = false;
    }

    public void delete(View view) {
        if(course != null) {
            db.deleteCourse(course);
            Intent intent = new Intent(CourseDetailController.this, CourseController.class);
            intent.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
            startActivity(intent);
        }
    }

    public void goToAssessments(View view) {
        if(saved) {
            Intent intent = new Intent(CourseDetailController.this, AssessmentController.class);
            intent.putExtra("Course Info", course.getId() + "-"+ course.getTitle() + "-" + course.getStartDate() + "-" + course.getEndDate() + "-" + course.getStatus() + "-" + course.getTermId());
            intent.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
            startActivity(intent);
        }
    }

    public void goToNotes(View view) {
        if(saved) {
            Intent intent = new Intent(CourseDetailController.this, NoteController.class);
            intent.putExtra("Course Info", course.getId() + "-"+ course.getTitle() + "-" + course.getStartDate() + "-" + course.getEndDate() + "-" + course.getStatus() + "-" + course.getTermId());
            intent.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
            startActivity(intent);
        }
    }

    public void goToInstructors(View view) {
        if(saved) {
            Intent intent = new Intent(CourseDetailController.this, InstructorController.class);
            intent.putExtra("Course Info", course.getId() + "-"+ course.getTitle() + "-" + course.getStartDate() + "-" + course.getEndDate() + "-" + course.getStatus() + "-" + course.getTermId());
            intent.putExtra("Term Info", intentFromCourse.getStringExtra("Term Info"));
            startActivity(intent);
        }
    }
}