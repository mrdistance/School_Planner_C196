package com.joshwgu.schoolplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.joshwgu.schoolplanner.model.Assessment;
import com.joshwgu.schoolplanner.model.Course;
import com.joshwgu.schoolplanner.model.Instructor;
import com.joshwgu.schoolplanner.model.Note;
import com.joshwgu.schoolplanner.model.Term;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ScheduleDatabase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "schedule.db";
    private static final int VERSION = 1;

    public ScheduleDatabase(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }


//============================================================Table Classes========================================================================
    private static final class TermsTable{
        private static final String TABLE = "terms";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_START_DATE = "start_date";
        private static final String COL_END_DATE = "end_date";
    }

    private static final class CoursesTable{
        private static final String TABLE = "courses";
        private static final String COL_ID = "_id";
        private static final String COL_FK = "term_id";
        private static final String COL_NAME = "name";
        private static final String COL_START_DATE = "start_date";
        private static final String COL_END_DATE = "end_date";
        private static final String COL_PROGRESS = "progress";
    }

    private static final class NotesTable{
        private static final String TABLE = "notes";
        private static final String COL_ID = "_id";
        private static final String COL_FK = "course_id";
        private static final String COL_NAME = "name";
        private static final String COL_CONTENT = "content";

    }

    private static final class InstructorsTable{
        private static final String TABLE = "instructors";
        private static final String COL_ID = "_id";
        private static final String COL_FK = "course_id";
        private static final String COL_NAME = "name";
        private static final String COL_PHONE = "phone";
        private static final String COL_EMAIL = "email";

    }

    private static final class AssessmentsTable{
        private static final String TABLE = "assessments";
        private static final String COL_ID = "_id";
        private static final String COL_FK = "course_id";
        private static final String COL_NAME = "name";
        private static final String COL_START_DATE = "start_date";
        private static final String COL_END_DATE = "end_date";
        private static final String COL_TYPE = "type";
    }


//==============================================================On Create and On Update=====================================================================
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TermsTable.TABLE + " (" +
                TermsTable.COL_ID + " integer primary key autoincrement, " +
                TermsTable.COL_NAME + " text, " +
                TermsTable.COL_START_DATE + " integer, "  +
                TermsTable.COL_END_DATE + " integer)");

        db.execSQL("create table " + CoursesTable.TABLE + " (" +
                CoursesTable.COL_ID + " integer primary key autoincrement, " +
                CoursesTable.COL_NAME + " text, " +
                CoursesTable.COL_START_DATE + " integer, "  +
                CoursesTable.COL_END_DATE + " integer, " +
                CoursesTable.COL_PROGRESS + " text, " +
                CoursesTable.COL_FK + " integer, " +
                "foreign key (" + CoursesTable.COL_FK + ") references " + TermsTable.TABLE + "(" + TermsTable.COL_ID + "))");

        db.execSQL("create table " + AssessmentsTable.TABLE + " (" +
                AssessmentsTable.COL_ID + " integer primary key autoincrement, " +
                AssessmentsTable.COL_NAME + " text, " +
                AssessmentsTable.COL_START_DATE + " integer, "  +
                AssessmentsTable.COL_END_DATE + " integer, " +
                AssessmentsTable.COL_TYPE + " text, "  +
                AssessmentsTable.COL_FK + " integer, " +
                "foreign key (" + AssessmentsTable.COL_FK + ") references " + CoursesTable.TABLE + "(" + CoursesTable.COL_ID + "))");

        db.execSQL("create table " + InstructorsTable.TABLE + " (" +
                InstructorsTable.COL_ID + " integer primary key autoincrement, " +
                InstructorsTable.COL_NAME + " text, " +
                InstructorsTable.COL_PHONE + " text, " +
                InstructorsTable.COL_EMAIL + " text, "  +
                InstructorsTable.COL_FK + " integer, " +
                "foreign key (" + InstructorsTable.COL_FK + ") references " + CoursesTable.TABLE + "(" + CoursesTable.COL_ID + "))");

        db.execSQL("create table " + NotesTable.TABLE + " (" +
                NotesTable.COL_ID + " integer primary key autoincrement, " +
                NotesTable.COL_NAME + " text, " +
                NotesTable.COL_CONTENT + " text, " +
                NotesTable.COL_FK + " integer, " +
                "foreign key (" + NotesTable.COL_FK + ") references " + CoursesTable.TABLE + "(" + CoursesTable.COL_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TermsTable.TABLE);
        db.execSQL("drop table if exists " + CoursesTable.TABLE);
        db.execSQL("drop table if exists " + NotesTable.TABLE);
        db.execSQL("drop table if exists " + InstructorsTable.TABLE);
        db.execSQL("drop table if exists " + AssessmentsTable.TABLE);
        onCreate(db);
    }

//============================================================================Insert Statements=================================================================
    public long addTerm(String name, int startDate, int endDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TermsTable.COL_NAME, name);
        values.put(TermsTable.COL_START_DATE, startDate);
        values.put(TermsTable.COL_END_DATE, endDate);
        long termId = db.insert(TermsTable.TABLE, null, values);
        return termId;
    }

    public long addCourse(String name, int startDate, int endDate, String progress, int fk ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CoursesTable.COL_NAME, name);
        values.put(CoursesTable.COL_START_DATE, startDate);
        values.put(CoursesTable.COL_END_DATE, endDate);
        values.put(CoursesTable.COL_PROGRESS, progress);
        values.put(CoursesTable.COL_FK, fk);
        long courseId = db.insert(CoursesTable.TABLE, null, values);
        return courseId;
    }

    public long addAssessment(String name, int startDate, int endDate, String type, int fk ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AssessmentsTable.COL_NAME, name);
        values.put(AssessmentsTable.COL_START_DATE, startDate);
        values.put(AssessmentsTable.COL_END_DATE, endDate);
        values.put(AssessmentsTable.COL_TYPE, type);
        values.put(AssessmentsTable.COL_FK, fk);
        long assessmentId = db.insert(AssessmentsTable.TABLE, null, values);
        return assessmentId;
    }

    public long addInstructor(String name, String phone, String email, int fk ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InstructorsTable.COL_NAME, name);
        values.put(InstructorsTable.COL_PHONE, phone);
        values.put(InstructorsTable.COL_EMAIL, email);
        values.put(InstructorsTable.COL_FK, fk);
        long instructorId = db.insert(InstructorsTable.TABLE, null, values);
        return instructorId;
    }

    public long addNote(String name, String content, int fk ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesTable.COL_NAME, name);
        values.put(NotesTable.COL_CONTENT, content);
        values.put(NotesTable.COL_FK, fk);
        long noteId = db.insert(NotesTable.TABLE, null, values);
        return noteId;
    }

//====================================================================Select Statements===================================================================================

    public ArrayList<Term> getTerms() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + TermsTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            ArrayList<Term> terms = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                Long startDate = cursor.getLong(2);
                Long endDate = cursor.getLong(3);
                Term term = new Term(id, name, startDate, endDate);
                terms.add(term);
            } while (cursor.moveToNext());
            return terms;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Course> getCourses(int searchId) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + CoursesTable.TABLE + " where term_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(searchId)});
        if (cursor.moveToFirst()) {
            ArrayList<Course> courses = new ArrayList<>();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                Long startDate = cursor.getLong(2);
                Long endDate = cursor.getLong(3);
                String progress = cursor.getString(4);
                int termId = cursor.getInt(5);

                Course course = new Course(id, name, startDate, endDate, progress, termId);
                courses.add(course);
            } while (cursor.moveToNext());
            return courses;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Assessment> getAssessments(int searchId) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + AssessmentsTable.TABLE + " where course_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(searchId)});
        if (cursor.moveToFirst()) {
            ArrayList<Assessment> assessments = new ArrayList<>();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                Long startDate = cursor.getLong(2);
                Long endDate = cursor.getLong(3);
                String type = cursor.getString(4);
                int courseId = cursor.getInt(5);

                Assessment assessment = new Assessment(id, name, startDate, endDate, type, courseId);
                assessments.add(assessment);
            } while (cursor.moveToNext());
            return assessments;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Instructor> getInstructors(int searchId) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + InstructorsTable.TABLE + " where course_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(searchId)});
        if (cursor.moveToFirst()) {
            ArrayList<Instructor> instructors = new ArrayList<>();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String email = cursor.getString(3);
                int courseId = cursor.getInt(4);

                Instructor instructor = new Instructor(id, name, phone, email, courseId);
                instructors.add(instructor);
            } while (cursor.moveToNext());
            return instructors;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Note> getNotes(int searchId) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + NotesTable.TABLE + " where course_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(searchId)});
        if (cursor.moveToFirst()) {
            ArrayList<Note> notes = new ArrayList<>();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String content = cursor.getString(2);
                int courseId = cursor.getInt(3);

                Note note = new Note(id, name, content, courseId);
                notes.add(note);
            } while (cursor.moveToNext());
            return notes;
        }
        cursor.close();
        return null;
    }


}
