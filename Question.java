package com.company;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Question {

    protected String question;
    public Question(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String writeQuestion() {
        return null;
    }

}
