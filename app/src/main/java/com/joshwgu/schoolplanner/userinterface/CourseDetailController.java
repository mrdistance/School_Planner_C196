package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.joshwgu.schoolplanner.R;

import java.util.Calendar;

public class CourseDetailController extends AppCompatActivity {

    private static final String TAG = "CourseDetailController";
    private TextView mStartDate;
    private TextView mEndDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mStartDate = (TextView) findViewById(R.id.startDateText);
        mEndDate = (TextView) findViewById(R.id.endDateText);

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