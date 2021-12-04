package com.company;

public class FRQQuestion extends Question {
    public FRQQuestion(String question) {
        super(question);
    }

    public String writeQuestion() {
        return String.format("FRQ: %s\nAnswer: ", question);
    }
}