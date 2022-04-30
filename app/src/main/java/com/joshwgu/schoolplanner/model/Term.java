package com.joshwgu.schoolplanner.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Term {

    private int id;
    private String title;
    private long startDate;
    private long endDate;


    public Term( String title, long startDate, long endDate){
        this.id = 0;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Term(int id, String title, long startDate, long endDate){
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
