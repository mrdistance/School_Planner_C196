package com.joshwgu.schoolplanner.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Course {

    private int id;
    private String title;
    private String status;
    private final ArrayList<Instructor> instructors;
    private final ArrayList<Assessment> assessments;
    private final ArrayList<Note> notes;
    private LocalDate startDate;
    private LocalDate endDate;
    private int termId;

    public Course(int id, String title, LocalDate startDate, LocalDate endDate, String status,  int termId){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.termId = termId;
        instructors = new ArrayList<>();
        assessments = new ArrayList<>();
        notes = new ArrayList<>();
    }
    public void addInstructor(Instructor instructor){
        this.instructors.add(instructor);
    }

    public void deleteInstructor(Instructor instructor){
        instructors.remove(instructor);
    }

    public ArrayList<Instructor> getInstructors(){
        return this.instructors;
    }

    public void addAssessment(Assessment assessment){
        assessments.add(assessment);
    }

    public void deleteAssessment(Assessment assessment){
        assessments.remove(assessment);
    }

    public ArrayList<Assessment> getAssessments(){
        return this.assessments;
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void deleteNote(Note note){
        notes.remove(note);
    }

    public ArrayList<Note> getNotes(){
        return this.notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}
