package com.joshwgu.schoolplanner.model;

public class Instructor {

    private int id;
    private String name;
    private String email;
    private String phone;
    private int courseId;

    public Instructor(int id, String name, String phone, String email, int courseId){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.courseId=courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
