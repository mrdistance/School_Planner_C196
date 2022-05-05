package com.joshwgu.schoolplanner.model;

public class Note {
    private int id;
    private String name;
    private String content;
    private int courseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String toString(){
        return  this.name + "\n---------------\n" + this.content ;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public Note( String name, String content, int courseId){
        this.id = 0;
        this.content = content;
        this.name = name;
        this.courseId = courseId;
    }
    public Note(int id, String name, String content, int courseId){
        this.id = id;
        this.content = content;
        this.name = name;
        this.courseId = courseId;
    }
}
