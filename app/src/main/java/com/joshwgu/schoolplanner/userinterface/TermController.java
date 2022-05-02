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
import android.widget.TextView;

import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Term;

import java.util.ArrayList;
import java.util.List;

public class TermController extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScheduleDatabase db = new ScheduleDatabase(getApplicationContext());
        List<Term> terms = new ArrayList<>();
        setContentView(R.layout.activity_term);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        terms = db.getTerms();
        if(terms != null){
            for(Term term : terms){
                Button button = new Button(this);
                button.setText(term.toString());
                button.setGravity(Gravity.CENTER);
                button.setTextSize(32);
                button.setClickable(true);
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        editTerm(v, term);
                    }
                });
                LinearLayout termsDisplayList = findViewById(R.id.termsDisplayList);
                termsDisplayList.addView(button);
            }
        }





    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu adds items to action bar if present
        getMenuInflater().inflate(R.menu.term_back_menu, menu);

        MenuItem home = menu.findItem(R.id.home_screen);
        Intent goHome = new Intent(this, MainActivity.class);
        home.setIntent(goHome);

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


    public void addTerm(View view) {
        Intent intent = new Intent(TermController.this, TermDetailController.class);
        startActivity(intent);
    }

    //todo add code to click on term in scroll view and enter term detail activity with prefilled data
    public void editTerm(View view, Term term){
        Intent intent = new Intent(TermController.this, TermDetailController.class);
        intent.putExtra("Term Info", term.getId() + "-"+ term.getTitle() + "-" + term.getStartDate() + "-" + term.getEndDate());
        startActivity(intent);
    }

    public void refresh(View view){
        this.recreate();
    }
}