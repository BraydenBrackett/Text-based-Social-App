package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    private ArrayList<Question> questions;
    String quizFileName;
    File quizFile;


    public String getQuizFileName() {
        return quizFileName;
    }



    public Quiz(String quizFileName) {
        this.questions = new ArrayList<Question>();
        this.quizFileName = quizFileName;
        this.quizFile = new File(quizFileName);
    }



    public File getQuizFile() {
        return quizFile;
    }



    public void addQuestion(Question question) throws FileNotFoundException {
        questions.add(question);
        writeQuiz();
    }
    public void removeQuestion(int questionNumber) throws FileNotFoundException {
        questions.remove(questions.get(questionNumber + 1));
        writeQuiz();
    }
    public void modifyQuestion(Question question, int questionNumber) {
        questions.set(questionNumber + 1, question);
    }
    public void randomize() throws FileNotFoundException {
        ArrayList<Question> copy = new ArrayList<>();
        copy.addAll(questions);
        Collections.shuffle(copy);
        writeQuiz(copy);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void questionPool(int numQuestions) throws FileNotFoundException {
        ArrayList<Question> copy = new ArrayList<>();
        copy.addAll(questions);
        Collections.shuffle(copy);
        ArrayList<Question> pooledQuiz = new ArrayList<>();

        for(int i = 0; i < numQuestions; i++) {
            pooledQuiz.add(copy.get(i));
        }
        writeQuiz(pooledQuiz);
    }

    public void writeQuiz(ArrayList<Question> copy) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(quizFileName);
        PrintWriter pw = new PrintWriter(fos);
        for(Question question: questions) {
            pw.write(question.writeQuestion() + "\n\n");
        }
        pw.close();
    }
    //call writeQuiz to revert a randomized quiz back to its original.
    public void writeQuiz() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(quizFileName);
        PrintWriter pw = new PrintWriter(fos);
        for(Question question: questions) {
            pw.write(question.writeQuestion() + "\n\n");
        }
        pw.close();
    }
}