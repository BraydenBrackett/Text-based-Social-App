package com.company;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Main runner for creating objects of the account class
 * */
public class Account {
    //Specific users password
    private String password;
    //Identifies whether a class is of teacher type or not
    private boolean isTeacher;
    //Specific users username
    private String username;
    //Specific courses
    private ArrayList<Course> courses;

    /**
     * Creates a new account object.
     * */
    public Account(){
        password = "";
        username = "";
        isTeacher = false;
    }

    public Account(String username, String password, boolean isTeacher) {
        this.password = password;
        this.username = username;
        this.isTeacher = isTeacher;
        courses = new ArrayList<Course>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addCourse(Course course){
        courses.add(course);
    }
    public void removeCourse(Course course){
        courses.remove(course);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return isTeacher() == account.isTeacher() && Objects.equals(getPassword(), account.getPassword()) && Objects.equals(getUsername(), account.getUsername());
    }

    @Override
    public String toString() {
        return "Account{" +
                "password='" + password + '\'' +
                ", isTeacher=" + isTeacher +
                ", username='" + username + '\'' +
                '}';
    }
}