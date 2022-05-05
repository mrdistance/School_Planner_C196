package com.joshwgu.schoolplanner.model;

public class Course {

    private int id;
    private String title;
    private String status;
    private String startDate;
    private String endDate;
    private int termId;


    public Course(String title, String startDate, String endDate, String status, int termId){
        this.id = 0;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.termId = termId;

    }
    public Course(int id, String title, String startDate, String endDate, String status, int termId){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.termId = termId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIntStatus() {

        if(status.equals("Plan to Take")){
            return 0;
        }else if(status.equals("In Progress")){
            return 1;
        }else if(status.equals("Completed")){
            return 2;
        }else{
            return 3;
        }
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        return this.title + "\n---------------\nSTART - " + this.startDate + "\nEND - " + this.endDate;
    }
}
