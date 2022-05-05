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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Assessment;

import java.util.Calendar;

public class AssessmentDetailController extends AppCompatActivity {

    private static final String TAG = "AssessmentDetailController";
    private TextView mStartDate;
    private TextView mEndDate;
    private EditText mNameText;
    private Spinner mTypeSpinner;
    private ScheduleDatabase db;
    private Intent intentFromAssessment;
    private String assessmentInfo;
    private SpinnerAdapter adapter;
    private String courseInfo;
    private boolean saved;
    private Assessment assessment;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new ScheduleDatabase(getApplicationContext());
        intentFromAssessment = getIntent();
        setContentView(R.layout.activity_assessment_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = ArrayAdapter.createFromResource(this, R.array.assessment_type_array, android.R.layout.simple_spinner_dropdown_item);
        courseInfo = intentFromAssessment.getStringExtra("Course Info");
        assessmentInfo = intentFromAssessment.getStringExtra("Assessment Info");
        saved = false;
        mStartDate = (TextView) findViewById(R.id.startDateText);
        mEndDate = (TextView) findViewById(R.id.endDateText);
        mNameText = (EditText) findViewById(R.id.assessmentNameText);
        mTypeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        mTypeSpinner.setAdapter(adapter);

        if(assessmentInfo != null){
            String[] assessmentInfoParts = assessmentInfo.split("-");
            assessment = new Assessment(Integer.parseInt(assessmentInfoParts[0]), assessmentInfoParts[1], assessmentInfoParts[2], assessmentInfoParts[3], assessmentInfoParts[4], Integer.parseInt(assessmentInfoParts[5]));
            mNameText.setText(assessment.getTitle());
            mStartDate.setText(assessment.getStartDate());
            mEndDate.setText(assessment.getEndDate());
            mTypeSpinner.setSelection(assessment.getIntType());
            mTypeSpinner.setEnabled(false);
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
                DatePickerDialog dialog = new DatePickerDialog(AssessmentDetailController.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
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
                DatePickerDialog dialog = new DatePickerDialog(AssessmentDetailController.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
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
        getMenuInflater().inflate(R.menu.assessment_detail_back_menu, menu);
        MenuItem home = menu.findItem(R.id.home_screen);
        Intent goHome = new Intent(this, MainActivity.class);
        home.setIntent(goHome);
        MenuItem term = menu.findItem(R.id.term_screen);
        Intent goTerm = new Intent(this, TermController.class);
        term.setIntent(goTerm);
        MenuItem course = menu.findItem(R.id.course_screen);
        Intent goCourse = new Intent(this, CourseController.class);
        goCourse.putExtra("Term Info", intentFromAssessment.getStringExtra("Term Info"));
        course.setIntent(goCourse);
        MenuItem assessment = menu.findItem(R.id.assessment_screen);
        Intent goAssessment = new Intent(this, AssessmentController.class);
        goAssessment.putExtra("Term Info", intentFromAssessment.getStringExtra("Term Info"));
        goAssessment.putExtra("Course Info", intentFromAssessment.getStringExtra("Course Info"));
        assessment.setIntent(goAssessment);
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
            if (assessment != null) {
                assessment.setTitle(mNameText.getText().toString());
                assessment.setStartDate(mStartDate.getText().toString());
                assessment.setEndDate(mEndDate.getText().toString());
                assessment.setType(mTypeSpinner.getSelectedItem().toString());
                db.updateAssessment(assessment);
            } else {
                assessment = new Assessment(mNameText.getText().toString(), mStartDate.getText().toString(), mEndDate.getText().toString(), mTypeSpinner.getSelectedItem().toString(), Integer.parseInt(courseInfo.split("-")[0]));
                assessment.setId((int) db.addAssessment(assessment));

            }
            mNameText.setEnabled(false);
            mStartDate.setEnabled(false);
            mEndDate.setEnabled(false);
            mTypeSpinner.setEnabled(false);
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
        mTypeSpinner.setEnabled(true);
        saved = false;
    }



    public void delete(View view) {
        if(assessment != null) {
            db.deleteAssessment(assessment);
            Intent intent = new Intent(AssessmentDetailController.this, AssessmentController.class);
            intent.putExtra("Term Info", intentFromAssessment.getStringExtra("Term Info"));
            intent.putExtra("Course Info", intentFromAssessment.getStringExtra("Course Info"));
            startActivity(intent);
        }
    }
}