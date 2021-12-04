package com.company;

import java.util.ArrayList;

public class Course {
    private String courseTitle;
    private ArrayList<Quiz> quizzes;

    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
        quizzes = new ArrayList<Quiz>();
    }

    public Course(String courseTitle, ArrayList<Quiz> quizzes) {
        this.courseTitle = courseTitle;
        this.quizzes = quizzes;
    }
    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }
    public void removeQuiz(Quiz quiz) {
        int index = quizzes.indexOf(quiz);
        quizzes.remove(quiz);
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
