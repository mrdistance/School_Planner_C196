package com.joshwgu.schoolplanner.model;

import java.time.LocalDate;

public class Assessment {

    private int id;
    private String type;
    private String title;
    private int courseId;
    private LocalDate startDate;
    private LocalDate endDate;


    public Assessment(int id, String title, LocalDate startDate, LocalDate endDate, String type, int courseId ){
        this.id = id;
        this.type = type;
        this.title = title;
        this.courseId = courseId;
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
