
package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class InstructorController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
    }

    public void addInstructor(View view) {
        Intent intent = new Intent(InstructorController.this, InstructorDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on instructor in scroll view and enter instructor detail activity with prefilled data
    public void editTInstructor(View view){

    }
}