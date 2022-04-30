package com.joshwgu.schoolplanner.model;

public class Course {

    private int id;
    private String title;
    private String status;
    private long startDate;
    private long endDate;
    private int termId;


    public Course( String title, long startDate, long endDate, String status, int termId){
        this.id = 0;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.termId = termId;

    }
    public Course(int id, String title, long startDate, long endDate, String status, int termId){
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }


}
