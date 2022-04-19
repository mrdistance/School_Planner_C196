package com.joshwgu.schoolplanner.model;

import java.time.LocalDate;

public class Assessment {

    private String type;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;


    public Assessment(String type, String title, LocalDate startDate, LocalDate endDate){
        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
