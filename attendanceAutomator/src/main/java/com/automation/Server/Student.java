package com.automation.Server;

public class Student {
    private String name;
    private int id;
    private String email;
    private String password;
    private String course;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return name;
    }
}
