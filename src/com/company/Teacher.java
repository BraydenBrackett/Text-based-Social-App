package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 * Teacher
 * <p>
 * The teacher class contains several methods that execute the roles of the teacher.
 * For example there is a method which allows the teacher to create a quiz or modify a quiz.
 *
 * @author Erik Nelson, L13
 * @version November 15, 2021
 */

public class Teacher extends Account {

    private String[][] quizzes;
    private static ArrayList<Quiz> quizList;

    public Teacher(String username, String password, boolean isTeacher) {
        super(username, password, isTeacher);
        this.quizList = new ArrayList<Quiz>();
    }

    public void setQuizzes() {
        this.quizzes = quizzes;
    }

    //Adds a quiz file names to a file
    public static void addToCourse(File name, String quizN) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(name), true);
        pw.println(quizN);
        pw.flush();
        pw.close();
    }

    public static void initializeQuizList(File file) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(file));

        String line = bfr.readLine();
        while (line != null) {
            Quiz quiz = new Quiz(line);
            quizList.add(quiz);
            line = bfr.readLine();
        }
    }

    //Reads the quiz for the teacher
    //Can also be used to view student submission
    public static String[][] readQuizFile(File file) throws IOException {
        ArrayList<String> questions = new ArrayList<String>();
        ArrayList<String> answers = new ArrayList<String>();

        BufferedReader bfr = new BufferedReader(new FileReader(file));
        String line = bfr.readLine();
        while (line != null) {
            questions.add(line);
            line = bfr.readLine();
            answers.add(line);
            line = bfr.readLine();
        }
        String[][] quiz = new String[questions.size()][2];
        for (int i = 0; i < questions.size(); i++) {
            quiz[i][0] = questions.get(i);
            quiz[i][1] = answers.get(i);
        }
        bfr.close();
        return quiz;
    }

    public static String[][] readQuizTakenFile(File file) throws IOException {
        ArrayList<String> questions = new ArrayList<String>();
        ArrayList<String> answers = new ArrayList<String>();
        ArrayList<String> studentAns = new ArrayList<String>();

        BufferedReader bfr = new BufferedReader(new FileReader(file));
        String line = bfr.readLine();
        while (line != null) {
            questions.add(line);
            line = bfr.readLine();
            answers.add(line);
            line = bfr.readLine();
            studentAns.add(line);
            line = bfr.readLine();
        }
        String[][] quiz = new String[questions.size()][3];
        for (int i = 0; i < questions.size(); i++) {
            quiz[i][0] = questions.get(i);
            quiz[i][1] = answers.get(i);
            quiz[i][2] = studentAns.get(i);
        }
        bfr.close();
        return quiz;
    }

    //Deletes Quiz
    public static void deleteQuiz(File file) throws IOException {
        file.delete();
    }
}