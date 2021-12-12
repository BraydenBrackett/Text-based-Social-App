package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
/**
 * Student GUI
 * <p>
 * Interface allows student user to view and take quizzes.
 *
 * @author Harshini Musku, L12
 * @version December 11, 2021
 */
public class StudentGUI implements Runnable {
    static JLabel fileNamee;
    static JTextField username; // text field for username
    static JButton submit; // button to submit credentials
    static JButton registerHere; // button to submit credentials
    static JButton loginHere; // button to submit credentials

    static Student student;

    static StudentGUI studentGUI; // variable of the type Paint
    static Color color;
    static Color filledColor;
    static Random randGen;
    static JFrame frameLogin;
    static JFrame frameRegister;
    static JFrame frameMenu;
    static JFrame frameAccount;
    static JFrame frameQuiz;

    static JLabel accountLabel;
    static JButton viewQuiz;
    static JButton takeQuiz;
    static String teacherYN;

    public void run() {
        student = new Student("user", "pass", false);
        randGen = new Random();
        frameLogin = new JFrame("Login");
        frameRegister = new JFrame("Register");
        frameMenu = new JFrame("Menu");
        frameAccount = new JFrame(" Student Menu");
        frameQuiz = new JFrame("Quiz");

        Container contentAcc = frameAccount.getContentPane();
        Container contentQuiz = frameQuiz.getContentPane();
        contentQuiz.setLayout(new BorderLayout());

        Container contentLogin = frameLogin.getContentPane();
        contentLogin.setLayout(new BorderLayout());
        studentGUI = new StudentGUI();
       // studentGUI.add(studentGUI,
              //  BorderLayout.CENTER);

        Container contentRegister = frameRegister.getContentPane();
        contentRegister.setLayout(new BorderLayout());
        studentGUI = new StudentGUI();
        //contentRegister.add(studentGUI, BorderLayout.CENTER);

        Container contentMenu = frameMenu.getContentPane();
        contentMenu.setLayout(new BorderLayout());
        studentGUI = new StudentGUI();
        //contentMenu.add(studentGUI, BorderLayout.CENTER);

        JPanel panelLogin = new JPanel();
        JPanel panelRegister = new JPanel();

        JPanel panelQuiz = new JPanel();
        JPanel panelUN = new JPanel();
        JPanel panelPW = new JPanel();
        JPanel panelSubmit = new JPanel();

        JPanel panelUN1 = new JPanel();
        JPanel panelPW1 = new JPanel();
        JPanel panelSubmit1 = new JPanel();

        JPanel panelAcc1 = new JPanel();
        JPanel panelAcc2 = new JPanel();
        JPanel panelAcc3 = new JPanel();


        fileNamee = new JLabel("Enter Quiz Name:");
        fileNamee.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelAcc1.add(fileNamee);
        contentAcc.add(panelAcc1, BorderLayout.NORTH);

        JTextField file = new JTextField(10);
        panelAcc1.add(file);
        contentAcc.add(panelAcc1, BorderLayout.NORTH);

        viewQuiz = new JButton("View Quiz");
        panelAcc2.add(viewQuiz);
        contentAcc.add(panelAcc2, BorderLayout.CENTER);

        takeQuiz = new JButton("Take Quiz");
        panelAcc2.add(takeQuiz);
        contentAcc.add(panelAcc2, BorderLayout.CENTER);
        JButton submit2 = new JButton("Quit");
        panelAcc3.add(submit2);
        contentAcc.add(panelAcc3, BorderLayout.SOUTH);

        JLabel quizContent = new JLabel("");
        quizContent.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelQuiz.add(quizContent);
        contentQuiz.add(panelRegister, BorderLayout.NORTH);

        submit = new JButton("Done");
        panelQuiz.add(submit);
        contentQuiz.add(panelQuiz, BorderLayout.SOUTH);

        viewQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                frameQuiz.setVisible(true);
                submit2.setVisible(true);
                quizContent.setVisible(true);
                try {
                    //read in file to print out
                    File file1 = new File(file.getText());
                    String[][] quiz = Student.readQuizFile(file1);
                    for (int i = 0; i < quiz.length; i++) {
                        if (quiz[i][0] != null) {
                            quizContent.setText(quizContent.getText()+ quiz[i][0] + "\n");
                        }
                        if (quiz[i][1] != null) {
                            quizContent.setText(quizContent.getText()+ quiz[i][1] + "\n\n");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid file path name", "Error", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println(quizContent.getText());
            }
        });
        takeQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                frameQuiz.setVisible(true);
                try {
                    String serverStuff = Student.takeQuiz(Student.readQuizFile(new File(file.getText())), file.getText());
                    Client.sendStuffToTheServer("ServerFile.txt",serverStuff);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //quizContent.setText(student.runStudent("takeQuiz", file.getText()));
                submit2.setVisible(true);
            }
        });
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(true);
                frameQuiz.setVisible(false);

            }
        });
        submit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                frameMenu.setVisible(false);
                frameQuiz.setVisible(false);
                JOptionPane.showMessageDialog(null, "Thank you for using the program!");


            }
        });
        frameAccount.setSize(300, 180);
        frameAccount.setLocationRelativeTo(null);
        frameAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameQuiz.setSize(300, 180);
        frameQuiz.setLocationRelativeTo(null);
        frameQuiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAccount.setVisible(true);
    }


    public void setStudent(String username, String password, boolean isTeacher) {
        student = new Student(username, password, false);

    }

    public StudentGUI() {

    }


    public static void runStudentGUI() {
        SwingUtilities.invokeLater(new StudentGUI());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());
    }

}
