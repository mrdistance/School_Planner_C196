package com.joshwgu.schoolplanner.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Course {

    private int id;
    private String title;
    private String status;
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
