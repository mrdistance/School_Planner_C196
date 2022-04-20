package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class AssessmentController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(AssessmentController.this, AssessmentDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on assessment in scroll view and enter assessment detail activity with prefilled data
    public void editAssessment(View view){

    }
}