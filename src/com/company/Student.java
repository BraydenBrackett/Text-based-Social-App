
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Student
 * <p>
 * Class that handles the student function
 * takes a quiz file and reads it
 * allows student to take and view graded quizzes
 *
 * @author Brandon Kingma, L12
 * @version November 11, 2021
 */

public class Student extends Account {

    private ArrayList<String> quizTaken;

    public Student(String username, String password, boolean isTeacher) {
        super(username, password, isTeacher);
    }

    /**
     * reads the quiz file to a 2D array and returns it to use later
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String[][] readQuizFile(File file) throws IOException {
        ArrayList<String> questions = new ArrayList<String>();
        ArrayList<String> answers = new ArrayList<String>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            questions.add(line);
            line = br.readLine();
            answers.add(line);
            line = br.readLine();
            line = br.readLine();
        }
        String[][] quiz = new String[questions.size()][2];
        for (int i = 0; i < questions.size(); i++) {
            quiz[i][0] = questions.get(i);
            quiz[i][1] = answers.get(i);

        }
        fr.close();
        br.close();
        return quiz;
    }

    /**
     * Prints the quiz for the student to see
     *
     * @param quiz
     * @param graded
     */
    public static String printQuiz(String[][] quiz, boolean graded) {
        String quizStr = "";
        int questionNum = 1;
        if (!graded) {
            for (int i = 0; i < quiz.length; i++) {
                for (int k = 0; k < 1; k++) {
                    quizStr += questionNum + "."+ (quiz[i][k]) + "\n";
                    questionNum++;
                }
            }
        } else {
            for (int i = 0; i < quiz.length; i++) {
                for (int k = 0; k < 2; k++) {
                    if (quiz[i][k] != null) {
                        quizStr += questionNum + "."+ (quiz[i][k]) + "\n";
                        questionNum++;
                    }
                }
            }
        }
        return quizStr;
    }

    /**
     * Writes the student answers to a new quiz file tagged with -taken
     *
     * @param quiz
     * @param ans
     * @param studentF
     * @param name
     * @throws IOException
     */
    public static String writeAnsToFile(String[][] quiz, String[] ans,
                                      String[] studentF, String name) throws IOException {
        String returnStr = "";
        File tempF = new File(name + "-taken");
        PrintWriter pw = new PrintWriter(new FileOutputStream(tempF));
        for (int i = 0; i < quiz.length; i++) {
            pw.write(quiz[i][0]);
            returnStr += quiz[i][0] + "\n";
            pw.println();
            pw.flush();
            pw.write(quiz[i][1]);
            returnStr += quiz[i][1] + "\n";
            pw.println();
            pw.flush();
            if (studentF[i] != null) {
                pw.write(ans[i] + " " + studentF[i]);
                returnStr += ans[i] + " " + studentF[i] + "\n";
                pw.println();
                pw.flush();
            } else {
                pw.write(ans[i]);
                returnStr += ans[i] + "\n";
                pw.println();
                pw.flush();
            }
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        pw.write(format.format(now));
        returnStr += format.format(now);
        pw.flush();
        return returnStr;
    }

    /**
     * method that prints the quiz file in a random order and
     * allows the user to answer the questions and calls writeAnsToFile
     *
     * @param quiz
     * @param name
     * @throws IOException
     */
    public static String takeQuiz(String[][] quiz, String name) throws IOException {
        String returnStr = "";
        Scanner scanner = new Scanner(System.in);
        String[] recordAns = new String[quiz.length];
        String[] addToAns = new String[quiz.length];
        String ans = "";
        String addF = "";
        String studentF;
        String[][] tempQ = new String[quiz.length][2];
        for (int k = 0; k < quiz.length; k++) {
            tempQ[k][0] = quiz[k][0];
            tempQ[k][1] = quiz[k][1];
        }
        int count = 0;
        int randChoice = 0;
        while (true) {
            randChoice = (int) ((Math.random() * quiz.length));
            if (!tempQ[randChoice][0].isEmpty()) {
                JOptionPane.showMessageDialog(null,(tempQ[randChoice][0]));
                JOptionPane.showMessageDialog(null,(tempQ[randChoice][1]));
                ans = JOptionPane.showInputDialog(null,"Enter Answer:");
                recordAns[randChoice] = ans;
                addF = JOptionPane.showInputDialog(null,"Would you like to add a file? yes/no").toLowerCase();
                if (addF.equals("yes")) {
                    while (true) {
                        //returnStr+=("Please enter the path to the file.")+"\n";
                        studentF = JOptionPane.showInputDialog(null,"Please enter the path to the file.");
                        if (Files.exists(Paths.get(studentF))) {
                            addToAns[count] = studentF;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null,"Error, not a valid file path");
                        }
                    }
                }
                tempQ[randChoice][0] = "";
                tempQ[randChoice][1] = "";
                count++;
            }
            if (count == quiz.length) {
                returnStr = writeAnsToFile(quiz, recordAns, addToAns, name);
                break;
            }
        }
        return returnStr;
    }

    /**
     * Method that runs student methods based on the answer to the prompts the user
     *
     * @throws IOException
     */
    public static String runStudent(String studentChoiceS, String fileName) throws IOException {
        String returnStr = "";
        Scanner scanner = new Scanner(System.in);
        int studentChoice = -1;
        boolean run = true;
        String again = "";
        String quit = "";


        try {
            if (studentChoiceS.equals("takeQuiz")) {
                studentChoice = 1;
            } else if (studentChoiceS.equals("viewQuiz")) {
                studentChoice = 2;
            }

        } catch (NumberFormatException e) {
            returnStr = ("Please enter a valid input.");
        }


        try {
            switch (studentChoice) {
                case 1:
                    File quizTake = new File(fileName);
                    String[][] currQuizT = readQuizFile(quizTake);
                    returnStr = takeQuiz(currQuizT, fileName);
                    break;
                case 2:
                    File quizGrade = new File(fileName);
                    String[][] currQuizG = readQuizFile(quizGrade);
                    returnStr= printQuiz(currQuizG, true);
                    break;
            }
        } catch (FileNotFoundException e) {
            returnStr = "Error, not a valid file";
        }
        return returnStr;
    }

}
