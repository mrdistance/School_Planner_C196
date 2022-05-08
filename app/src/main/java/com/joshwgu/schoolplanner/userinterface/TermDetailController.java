package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Term;

import java.util.Calendar;

public class TermDetailController extends AppCompatActivity {

    private static final String TAG = "TermDetailController";
    private TextView mStartDate;
    private TextView mEndDate;
    private TextView mNameText;
    private Intent intentFromTerm;
    private ScheduleDatabase db;
    private Term term;
    private boolean saved;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new ScheduleDatabase(getApplicationContext());
        intentFromTerm = getIntent();
        setContentView(R.layout.activity_term_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String termInfo = intentFromTerm.getStringExtra("Term Info");
        saved = false;
        mStartDate = (TextView) findViewById(R.id.startDateText);
        mEndDate = (TextView) findViewById(R.id.endDateText);
        mNameText = (TextView) findViewById(R.id.termNameText);

        if(termInfo != null){
            String[] termInfoParts = termInfo.split("-");
            term = new Term(Integer.parseInt(termInfoParts[0]), termInfoParts[1], termInfoParts[2], termInfoParts[3]);
            mNameText.setText(term.getTitle());
            mStartDate.setText(term.getStartDate());
            mEndDate.setText(term.getEndDate());
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
                DatePickerDialog dialog = new DatePickerDialog(TermDetailController.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
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
                DatePickerDialog dialog = new DatePickerDialog(TermDetailController.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
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
        getMenuInflater().inflate(R.menu.term_detail_back_menu, menu);

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


    public void goToCourses(View view) {
        if(saved) {
            Intent intent = new Intent(TermDetailController.this, CourseController.class);
            intent.putExtra("Term Info", term.getId() + "-" + term.getTitle() + "-" + term.getStartDate() + "-" + term.getEndDate());
            startActivity(intent);
        }
    }

    public void save(View view) {
        if(validateFields()) {
            if (term != null) {
                term.setTitle(mNameText.getText().toString());
                term.setStartDate(mStartDate.getText().toString());
                term.setEndDate(mEndDate.getText().toString());
                db.updateTerm(term);
            } else {
                term = new Term(mNameText.getText().toString(), mStartDate.getText().toString(), mEndDate.getText().toString());
                term.setId((int) db.addTerm(term));

            }
            mNameText.setEnabled(false);
            mStartDate.setEnabled(false);
            mEndDate.setEnabled(false);
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
        saved = false;
    }

    public void delete(View view) {
        if(term != null) {
            if(db.deleteTerm(term)) {
                Intent intent = new Intent(TermDetailController.this, TermController.class);
                startActivity(intent);
            }else{

                AlertDialog alert = new AlertDialog.Builder(TermDetailController.this).create();
                alert.setTitle("WARNING");
                alert.setMessage("Courses must be deleted before deleting this Term");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();

            }
        }
    }



}