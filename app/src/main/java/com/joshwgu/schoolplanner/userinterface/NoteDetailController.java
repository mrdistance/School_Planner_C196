package com.joshwgu.schoolplanner.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.joshwgu.schoolplanner.R;
import com.joshwgu.schoolplanner.database.ScheduleDatabase;
import com.joshwgu.schoolplanner.model.Instructor;
import com.joshwgu.schoolplanner.model.Note;

public class NoteDetailController extends AppCompatActivity {

    private EditText mNameText;
    private EditText mContentText;
    private boolean saved;
    private ScheduleDatabase db;
    private Note note;
    private String noteInfo;
    private String courseInfo;
    private Intent intentFromNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new ScheduleDatabase(getApplicationContext());
        intentFromNote= getIntent();
        setContentView(R.layout.activity_note_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        courseInfo = intentFromNote.getStringExtra("Course Info");
        noteInfo = intentFromNote.getStringExtra("Note Info");
        saved = false;
        mNameText = (EditText) findViewById(R.id.noteNameText);
        mContentText = (EditText) findViewById(R.id.noteContent);


        if(noteInfo != null){
            String[] noteInfoParts = noteInfo.split("-");
            note = new Note(Integer.parseInt(noteInfoParts[0]), noteInfoParts[1], noteInfoParts[2], Integer.parseInt(noteInfoParts[3]));
            mNameText.setText(note.getName());
            mContentText.setText(note.getContent());
            mNameText.setEnabled(false);
            mContentText.setEnabled(false);
            saved = true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate menu adds items to action bar if present
        getMenuInflater().inflate(R.menu.note_detail_back_menu, menu);
        MenuItem home = menu.findItem(R.id.home_screen);
        Intent goHome = new Intent(this, MainActivity.class);
        home.setIntent(goHome);
        MenuItem term = menu.findItem(R.id.term_screen);
        Intent goTerm = new Intent(this, TermController.class);
        term.setIntent(goTerm);
        MenuItem course = menu.findItem(R.id.course_screen);
        Intent goCourse = new Intent(this, CourseController.class);
        goCourse.putExtra("Term Info", intentFromNote.getStringExtra("Term Info"));
        course.setIntent(goCourse);
        MenuItem note = menu.findItem(R.id.note_screen);
        Intent goNote = new Intent(this, NoteController.class);
        goNote.putExtra("Term Info", intentFromNote.getStringExtra("Term Info"));
        goNote.putExtra("Course Info", intentFromNote.getStringExtra("Course Info"));
        note.setIntent(goNote);
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
            if (note != null) {
                note.setName(mNameText.getText().toString());
                note.setContent(mContentText.getText().toString());
                db.updateNote(note);
            } else {
                note = new Note(mNameText.getText().toString(), mContentText.getText().toString(),Integer.parseInt(courseInfo.split("-")[0]));
                note.setId((int) db.addNote(note));

            }
            mNameText.setEnabled(false);
            mContentText.setEnabled(false);
            saved = true;
        }else{
            if(mNameText.getText().toString().isEmpty()){
                mNameText.setHintTextColor(Color.RED);
            }
            if(mContentText.getText().toString().isEmpty()){
                mContentText.setHintTextColor(Color.RED);
            }
        }
    }




    private boolean validateFields(){
        if(mNameText.getText().toString().isEmpty() || mContentText.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    public void edit(View view){
        mNameText.setEnabled(true);
        mContentText.setEnabled(true);
        saved = false;
    }

    public void delete(View view) {
        if(note != null) {
            db.deleteNote(note);
            Intent intent = new Intent(NoteDetailController.this, NoteController.class);
            intent.putExtra("Term Info", intentFromNote.getStringExtra("Term Info"));
            intent.putExtra("Course Info", intentFromNote.getStringExtra("Course Info"));
            startActivity(intent);
        }
    }

    public void share(View view) {
        if(note != null) {
            Intent intent = new Intent(Intent.ACTION_SEND);

// Supply extra that is plain text
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Note Content");
            intent.putExtra(Intent.EXTRA_TEXT, note.getContent());

// If at least one app can handle intent, allow user to choose
            if (intent.resolveActivity(getPackageManager()) != null) {
                Intent chooser = intent.createChooser(intent, "Share Note");
                startActivity(chooser);
            }
        }
    }
}