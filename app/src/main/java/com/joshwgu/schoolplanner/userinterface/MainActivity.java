package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshwgu.schoolplanner.R;

public class MainActivity extends AppCompatActivity {
    public static int notificationNumberAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void enter(View view) {
        Intent intent = new Intent(MainActivity.this, TermController.class);
        startActivity(intent);
    }
}