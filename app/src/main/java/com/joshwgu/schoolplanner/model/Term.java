package com.joshwgu.schoolplanner.model;

public class Term {

    private int id;
    private String title;
    private String startDate;
    private String endDate;


    public Term(String title, String startDate, String endDate){
        this.id = 0;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Term(int id, String title, String startDate, String endDate){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return this.title + "\n---------------\nStart - " + this.startDate + "\nEnd - " + this.endDate ;
    }



}
