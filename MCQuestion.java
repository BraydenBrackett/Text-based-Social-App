package com.company;

public class MCQuestion extends Question{
    private String a;
    private String b;
    private String c;
    private String d;

    public MCQuestion(String question, String a, String b, String c, String d) {
        super(question);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public String writeQuestion() {
        return String.format("MC: %s\nA: %s B: %s C: %s D: %s", question, a, b, c, d);
    }

}
