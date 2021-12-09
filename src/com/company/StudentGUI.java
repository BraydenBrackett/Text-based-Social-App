package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

public class StudentGUI extends JComponent implements Runnable{
    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    static JLabel login;
    static JLabel unLabel;
    static JLabel pwLabel;
    static JLabel fileNamee;
    static JLabel unLabel1;
    static JLabel pwLabel1;


    static JTextField username; // text field for username
    static JTextField password; // text field for password
    static JButton submit; // button to submit credentials

    static JTextField username1; // text field for username
    static JTextField password1; // text field for password
    static JButton submit2; // button to submit credentials

    static JButton registerHere; // button to switch to register frame
    static JButton loginHere; // button to switch to log-in frame

    static Student student;

    static StudentGUI studentGUI; // variable of the type Paint
    static  Color color;
    static  Color filledColor;
    static Random randGen;
    static  JFrame frameLogin;
    static  JFrame frameRegister;
    static  JFrame frameMenu;
    static JFrame frameAccount;
    static JFrame frameQuiz;

    static JLabel accountLabel;
    static JButton viewQuiz;
    static JButton takeQuiz;
    static String teacherYN;

    public void run() {
        student = new Student("user","pass",false);
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
        contentLogin.add(studentGUI, BorderLayout.CENTER);

        Container contentRegister = frameRegister.getContentPane();
        contentRegister.setLayout(new BorderLayout());
        studentGUI = new StudentGUI();
        contentRegister.add(studentGUI, BorderLayout.CENTER);

        Container contentMenu = frameMenu.getContentPane();
        contentMenu.setLayout(new BorderLayout());
        studentGUI = new StudentGUI();
        contentMenu.add(studentGUI, BorderLayout.CENTER);

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
        JPanel panelAcc2  = new JPanel();
        JPanel panelAcc3  = new JPanel();


        JLabel quizContent = new JLabel("");
        quizContent.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelQuiz.add(quizContent);
        contentQuiz.add(panelRegister, BorderLayout.NORTH);

        fileNamee = new JLabel("Enter Quiz Name:");
        fileNamee.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelAcc3.add(fileNamee);
        contentAcc.add(panelAcc3, BorderLayout.WEST);

        submit = new JButton("Done");
        panelQuiz.add(submit);
        contentQuiz.add(panelQuiz, BorderLayout.SOUTH);

        JButton submit2 = new JButton("Quit");
        panelAcc3.add(submit2);
        contentAcc.add(panelAcc3, BorderLayout.SOUTH);

        registerHere = new JButton("Register Here");
        panelSubmit.add(registerHere);

        loginHere = new JButton("Login Here");
        contentLogin.add(panelSubmit, BorderLayout.SOUTH);
        panelSubmit1.add(loginHere);
        contentRegister.add(panelSubmit1, BorderLayout.SOUTH);

        accountLabel = new JLabel("What type of account is this?");
        accountLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        panelAcc1.add(accountLabel);


        viewQuiz = new JButton("View Quiz");
        panelAcc2.add(viewQuiz);
        contentAcc.add(panelAcc2, BorderLayout.CENTER);

        takeQuiz = new JButton("Take Quiz");
        panelAcc2.add(takeQuiz);
        contentAcc.add(panelAcc2, BorderLayout.CENTER);


        viewQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    frameAccount.setVisible(false);
                    frameQuiz.setVisible(true);
                    quizContent.setText( student.runStudent("viewQuiz", username.getText()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        takeQuiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    quizContent.setText( student.runStudent("takeQuiz", username.getText()));
                    frameAccount.setVisible(false);
                    frameQuiz.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
                JOptionPane.showMessageDialog(null, "Thank you for using the program!");


            }
        });
        frameAccount.setSize(300,180);
        frameAccount.setLocationRelativeTo(null);
        frameAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAccount.setVisible(true);
    }




    public void setStudent(String username, String password, boolean isTeacher) {
        student = new Student(username,password,false);

    }
    public StudentGUI() {

    }


    public static void runStudentGUI() {
        System.out.println("hiii");
        SwingUtilities.invokeLater(new StudentGUI());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());
    }

}
