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
    public long addTerm(Term term) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TermsTable.COL_NAME, term.getTitle());
        values.put(TermsTable.COL_START_DATE, term.getStartDate());
        values.put(TermsTable.COL_END_DATE, term.getEndDate());
        long termId = db.insert(TermsTable.TABLE, null, values);
        return termId;
    }

    public long addCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CoursesTable.COL_NAME, course.getTitle());
        values.put(CoursesTable.COL_START_DATE, course.getStartDate());
        values.put(CoursesTable.COL_END_DATE, course.getEndDate());
        values.put(CoursesTable.COL_PROGRESS, course.getStatus());
        values.put(CoursesTable.COL_FK, course.getTermId());
        long courseId = db.insert(CoursesTable.TABLE, null, values);
        return courseId;
    }

    public long addAssessment(Assessment assessment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AssessmentsTable.COL_NAME, assessment.getTitle());
        values.put(AssessmentsTable.COL_START_DATE, assessment.getStartDate());
        values.put(AssessmentsTable.COL_END_DATE, assessment.getEndDate());
        values.put(AssessmentsTable.COL_TYPE, assessment.getType());
        values.put(AssessmentsTable.COL_FK, assessment.getCourseId());
        long assessmentId = db.insert(AssessmentsTable.TABLE, null, values);
        return assessmentId;
    }

    public long addInstructor(Instructor instructor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InstructorsTable.COL_NAME, instructor.getName());
        values.put(InstructorsTable.COL_PHONE, instructor.getPhone());
        values.put(InstructorsTable.COL_EMAIL, instructor.getEmail());
        values.put(InstructorsTable.COL_FK, instructor.getCourseId());
        long instructorId = db.insert(InstructorsTable.TABLE, null, values);
        return instructorId;
    }

    public long addNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesTable.COL_NAME, note.getName());
        values.put(NotesTable.COL_CONTENT, note.getContent());
        values.put(NotesTable.COL_FK, note.getCourseId());
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
            cursor.close();
            return terms;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Course> getCourses(Term term) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + CoursesTable.TABLE + " where term_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(term.getId())});
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
            cursor.close();
            return courses;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Assessment> getAssessments(Course course) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + AssessmentsTable.TABLE + " where course_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(course.getId())});
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
            cursor.close();
            return assessments;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Instructor> getInstructors(Course course) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + InstructorsTable.TABLE + " where course_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(course.getId())});
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
            cursor.close();
            return instructors;
        }
        cursor.close();
        return null;
    }

    public ArrayList<Note> getNotes(Course course) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + NotesTable.TABLE + " where course_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(course.getId())});
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
            cursor.close();
            return notes;
        }
        cursor.close();
        return null;
    }


//===========================================================Update Statements=====================================================================================================

    public boolean updateTerm(Term term) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TermsTable.COL_NAME, term.getTitle());
        values.put(TermsTable.COL_START_DATE, term.getStartDate());
        values.put(TermsTable.COL_END_DATE, term.getEndDate());
        int rowsUpdated = db.update(TermsTable.TABLE, values, "_id = ?",
                new String[] { Float.toString(term.getId()) });
        return rowsUpdated > 0;
    }

    public boolean updateCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CoursesTable.COL_NAME, course.getTitle());
        values.put(CoursesTable.COL_START_DATE, course.getStartDate());
        values.put(CoursesTable.COL_END_DATE, course.getEndDate());
        values.put(CoursesTable.COL_PROGRESS, course.getStatus());
        values.put(CoursesTable.COL_FK, course.getTermId());
        int rowsUpdated = db.update(CoursesTable.TABLE, values, "_id = ?",
                new String[] { Float.toString(course.getId()) });
        return rowsUpdated > 0;
    }

    public boolean updateAssessment(Assessment assessment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AssessmentsTable.COL_NAME, assessment.getTitle());
        values.put(AssessmentsTable.COL_START_DATE, assessment.getStartDate());
        values.put(AssessmentsTable.COL_END_DATE, assessment.getEndDate());
        values.put(AssessmentsTable.COL_TYPE, assessment.getType());
        values.put(AssessmentsTable.COL_FK, assessment.getCourseId());
        int rowsUpdated = db.update(AssessmentsTable.TABLE, values, "_id = ?",
                new String[] { Float.toString(assessment.getId()) });
        return rowsUpdated > 0;
    }

    public boolean updateInstructor(Instructor instructor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InstructorsTable.COL_NAME, instructor.getName());
        values.put(InstructorsTable.COL_PHONE, instructor.getPhone());
        values.put(InstructorsTable.COL_EMAIL, instructor.getEmail());
        values.put(InstructorsTable.COL_FK, instructor.getCourseId());
        int rowsUpdated = db.update(InstructorsTable.TABLE, values, "_id = ?",
                new String[] { Float.toString(instructor.getId()) });
        return rowsUpdated > 0;
    }

    public boolean updateNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesTable.COL_NAME, note.getName());
        values.put(NotesTable.COL_CONTENT, note.getContent());
        values.put(NotesTable.COL_FK, note.getCourseId());
        int rowsUpdated = db.update(NotesTable.TABLE, values, "_id = ?",
                new String[] { Float.toString(note.getId()) });
        return rowsUpdated > 0;
    }



//=============================================================Delete Statements===================================================================================


    public boolean deleteTerm(Term term) {
        SQLiteDatabase db = getWritableDatabase();
        //Check that term has no courses
        String sql = "select * from " + CoursesTable.TABLE + " where term_id = ?";
        Cursor cursor = db.rawQuery(sql, new String [] {String.valueOf(term.getId())});
        if (cursor.moveToFirst()) {
            cursor.close();
            return false;
        }else {
            cursor.close();
            int rowsDeleted = db.delete(TermsTable.TABLE, TermsTable.COL_ID + " = ?",
                    new String[]{Long.toString(term.getId())});
            return rowsDeleted > 0;
        }
    }


    public boolean deleteCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        deleteAssessments(course);
        deleteInstructors(course);
        deleteNotes(course);
        int rowsDeleted = db.delete(CoursesTable.TABLE, CoursesTable.COL_ID + " = ?",
                new String[] { Long.toString(course.getId()) });
        return rowsDeleted > 0;
    }

    public boolean deleteAssessment(Assessment assessment) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(AssessmentsTable.TABLE, AssessmentsTable.COL_ID + " = ?",
                new String[] { Long.toString(assessment.getId()) });
        return rowsDeleted > 0;
    }

    public boolean deleteInstructor(Instructor instructor) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(InstructorsTable.TABLE, InstructorsTable.COL_ID + " = ?",
                new String[] { Long.toString(instructor.getId()) });
        return rowsDeleted > 0;
    }

    public boolean deleteNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(NotesTable.TABLE, NotesTable.COL_ID + " = ?",
                new String[] { Long.toString(note.getId()) });
        return rowsDeleted > 0;
    }

    private boolean deleteAssessments(Course course){
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(AssessmentsTable.TABLE, AssessmentsTable.COL_FK + " = ?",
                new String[] { Long.toString(course.getId()) });
        return rowsDeleted > 0;
    }

    private boolean deleteInstructors(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(InstructorsTable.TABLE, InstructorsTable.COL_FK + " = ?",
                new String[] { Long.toString(course.getId()) });
        return rowsDeleted > 0;
    }

    private boolean deleteNotes(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(NotesTable.TABLE, NotesTable.COL_FK + " = ?",
                new String[] { Long.toString(course.getId()) });
        return rowsDeleted > 0;
    }



}
