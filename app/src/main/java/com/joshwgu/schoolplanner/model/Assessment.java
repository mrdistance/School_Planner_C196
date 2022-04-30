package com.joshwgu.schoolplanner.model;

public class Assessment {

    private int id;
    private String type;
    private String title;
    private int courseId;
    private long startDate;
    private long endDate;



    public Assessment(String title, long startDate, long endDate, String type, int courseId ){
        this.id = 0;
        this.type = type;
        this.title = title;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Assessment(int id, String title, long startDate, long endDate, String type, int courseId ){
        this.id = id;
        this.type = type;
        this.title = title;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
