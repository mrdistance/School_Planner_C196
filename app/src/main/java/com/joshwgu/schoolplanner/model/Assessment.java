package com.joshwgu.schoolplanner.model;

public class Assessment {

    private int id;
    private String type;
    private String title;
    private int courseId;
    private String startDate;
    private String endDate;



    public Assessment(String title, String startDate, String endDate, String type, int courseId ){
        this.id = 0;
        this.type = type;
        this.title = title;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Assessment(int id, String title, String startDate, String endDate, String type, int courseId ){
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

    public int getIntType(){
        if(type.equals("Objective")){
            return 0;
        }else{
            return 1;
        }
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
        return this.title + "\n---------------\nSTART - " + this.startDate + "\nEND -" + this.endDate;
    }
}
