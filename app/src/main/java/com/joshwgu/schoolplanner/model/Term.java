package com.joshwgu.schoolplanner.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Term {

    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public Term(int id, String title, LocalDate startDate, LocalDate endDate){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
