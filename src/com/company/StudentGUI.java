package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
    static JButton submit; // button to submit credentials
  

    static Student student;

    static StudentGUI studentGUI;
    
    static JFrame frameAccount;
    static JFrame frameQuiz;

    static JButton viewQuiz;
    static JButton takeQuiz;

    public void run() {
        //code for the buttons, textfiels, and labels of the interface.
        student = new Student("user", "pass", false);
        frameAccount = new JFrame(" Student Menu");
        frameQuiz = new JFrame("Quiz");

        Container contentAcc = frameAccount.getContentPane();
        Container contentQuiz = frameQuiz.getContentPane();
        contentQuiz.setLayout(new BorderLayout());

        studentGUI = new StudentGUI();

        JPanel panelRegister = new JPanel();

        JPanel panelQuiz = new JPanel();

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
        //action listeners for the buttons
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
                            quizContent.setText(quizContent.getText() + quiz[i][0] + "\n");
                        }
                        if (quiz[i][1] != null) {
                            quizContent.setText(quizContent.getText() + quiz[i][1] + "\n\n");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid file path name", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        takeQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                frameQuiz.setVisible(true);
                try {
                    String serverStuff = Student.takeQuiz(Student.readQuizFile(new File(file.getText())), file.getText());
                    Client.sendStuffToTheServer("ServerFile.txt", serverStuff);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
    
    public StudentGUI() {

    }

    /**
     * Allows the login class to run the GUI
     */
    public static void runStudentGUI() {
        SwingUtilities.invokeLater(new StudentGUI());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());
    }

}
