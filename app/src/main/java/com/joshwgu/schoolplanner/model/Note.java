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

    public Note(int id, String name, String content, int courseId){
        this.id = id;
        this.content = content;
        this.name = name;
        this.courseId = courseId;
    }
}
