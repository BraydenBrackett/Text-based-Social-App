package com.company;

public class FillInBlankQuestion extends Question {

    public FillInBlankQuestion(String question) {
        super(question);
    }
    public String writeQuestion() {
        return String.format("Fill in the blank: %s\nAnswer: _________", question);
    }
}