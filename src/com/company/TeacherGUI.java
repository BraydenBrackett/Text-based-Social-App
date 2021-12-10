package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TeacherGUI extends Thread implements Runnable {
    //Return value variable
    //private static int returnValue = 0;

    //All buttons and frames for teacher options
    JFrame frame;
    JFrame readFrame;
    JFrame createFrame;
    JFrame deleteFrame;
    JFrame modifyFrame;
    JFrame gradeFrame;

    JButton readQuizButton = new JButton("Read Quiz");
    JButton createQuizButton = new JButton("Create Quiz");
    JButton deleteQuizButton = new JButton("Delete Quiz");
    JButton modifyQuizButton = new JButton("Modify Quiz");
    JButton gradeQuizButton = new JButton("grade Quiz");
    JButton quitButton = new JButton("Quit");
    JButton returnButton = new JButton("Return");

    //Read quiz GUI
    JButton readEnterButton = new JButton("Enter");
    JTextField readFileText = new JTextField("", 8);
    JTextArea readQuizArea = new JTextArea("Enter the quiz you wish to read above", 15, 30);
    JButton readReturnButton = new JButton("Return");

    //Create quiz GUI
    JButton createEnterButton = new JButton("Enter");
    JButton createReturnButton = new JButton("Return");
    JTextArea createQuizArea = new JTextArea(
            "Please enter the quiz name in the box below.\n" +
                    "Press submit when you are finished creating the quiz",
            3, 35);
    JTextField createEnterText = new JTextField("", 25);
    private int createCount = 0;
    private int insideCount = 0;
    private String mc1 = "";
    private String mc2 = "";
    private String mc3 = "";
    private String mc4 = "";
    private Quiz readQuiz;
    private File readFile;


    //delete quiz GUI
    JButton deleteEnterButton = new JButton("Delete");
    JTextArea deleteQuizArea = new JTextArea("Enter the quiz path name you wish to delete below", 1, 25);
    JTextField deleteQuizText = new JTextField("", 10);
    JButton deleteReturnButton = new JButton("Return");

    //modify quiz GUI
    JButton modifyEnterButton = new JButton("Enter");
    JTextField modifyQuizText = new JTextField("Enter the quiz you wish to modify here.", 25);
    JTextArea modifyQuizArea = new JTextArea("", 40, 30);
    JButton modifyReturnButton = new JButton("Return");
    private int modifyCount = 0;

    //grade quiz GUI
    JButton gradeEnterButton = new JButton("Enter");
    JTextArea gradeQuizArea = new JTextArea("Enter the quiz you want to grade above", 20, 30);
    JTextField gradeQuizText = new JTextField("", 15);
    private int gradeCount = 0;


    private String input;
    private int intInput;
    private String name = "";


    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //read quiz
            if (e.getSource() == readQuizButton) {
                //EXAMPLE OF HOW TO WRITE TO THE SERVER
                //Client.sendStuffToTheServer("ServerFile.txt","*");

                frame.setVisible(false);
                readFrame.setVisible(true);
            }
            if (e.getSource() == readEnterButton) {
                setInput(readFileText.getText());
                runReadQuiz();
            }
            if (e.getSource() == readReturnButton) {
                frame.setVisible(true);
                readFrame.setVisible(false);
                readQuizArea.setText("Enter the quiz you wish to read above");
                readFileText.setText("");
            }
            //create quiz
            if (e.getSource() == createQuizButton) {
                frame.setVisible(false);
                createFrame.setVisible(true);
            }
            if (e.getSource() == createEnterButton) {
                setInput(createEnterText.getText());
                runCreateQuiz();
            }
            if (e.getSource() == createReturnButton) {
                createFrame.setVisible(false);
                createCount = 0;
                insideCount = 0;
                createQuizArea.setText("Please enter the quiz name in the box below.\n" +
                        "Press submit when you are finished creating the quiz");
                createEnterText.setText("");
                frame.setVisible(true);
            }

            //delete quiz
            if (e.getSource() == deleteQuizButton) {
                frame.setVisible(false);
                deleteFrame.setVisible(true);
            }
            if (e.getSource() == deleteEnterButton) {
                setInput(deleteQuizText.getText());
                runDeleteQuiz();
            }
            if (e.getSource() == deleteReturnButton) {
                frame.setVisible(true);
                deleteFrame.setVisible(false);
                deleteQuizArea.setText("Enter the quiz path name you wish to delete below");
            }
            //modify quiz
            if (e.getSource() == modifyQuizButton) {
                frame.setVisible(false);
                modifyFrame.setVisible(true);
            }
            if (e.getSource() == modifyEnterButton) {
                runModifyQuiz();
            }
            if (e.getSource() == modifyReturnButton) {
                frame.setVisible(true);
                modifyFrame.setVisible(false);
                modifyQuizText.setText("Enter the quiz you wish to modify here.");
                modifyCount = 0;
            }

            //grade quiz
            if (e.getSource() == gradeQuizButton) {
                frame.setVisible(false);
                gradeFrame.setVisible(true);
            }
            if (e.getSource() == gradeEnterButton) {
                runGradeQuiz();
            }

            if (e.getSource() == quitButton) {
                frame.setVisible(false);
                frame.dispose();
                readFrame.dispose();
                createFrame.dispose();
                modifyFrame.dispose();
                gradeFrame.dispose();
                deleteFrame.dispose();
                //returnValue = 1;
            }
            if (e.getSource() == returnButton) {
                frame.setVisible(true);
                gradeFrame.setVisible(false);
                gradeCount = 0;
                gradeQuizArea.setText("Enter the quiz you want to grade above");
                gradeQuizText.setText("");
            }
        }
    };

    public void runReadQuiz() {//needs a way to read in file from client
        try {
            //read in file to print out
            //Client.sendStuffToTheServer(input, "");
            //cant read just erases
            File file = new File("new" + input);
            String[][] quiz = Teacher.readQuizFile(file);
            readQuizArea.setText("");
            for (int i = 0; i < quiz.length; i++) {
                if (quiz[i][0] != null) {
                    readQuizArea.append(quiz[i][0] + "\n");
                }
                if (quiz[i][1] != null) {
                    readQuizArea.append(quiz[i][1] + "\n\n");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Invalid file path name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //right now it uses the teacher class methods to write to a file
    //could possibly make use of that for the server
    public void runCreateQuiz() {
        switch (createCount) {
            case 0:
                //would write file with the name to server
                name = input;
                readQuiz = new Quiz(name);
                createQuizArea.setText("What type of question would you like to add to the quiz?\n" +
                        "1. Multiple Choice Question\n" +
                        "2. Free Response Question\n" +
                        "3. Fill In the Blank Question\n");
                createEnterText.setText("");
                createCount = 1;
                break;
            case 1:
                try {
                    if (!(insideCount >= 1 && insideCount <= 6)) {
                        intInput = Integer.parseInt(input);
                    }
                    switch (intInput) {
                        case 1:
                            if (insideCount == 0) {
                                createQuizArea.setText("Please enter the question.");
                                createEnterText.setText("");
                            } else if (insideCount == 1) {
                                input = createEnterText.getText();
                                createQuizArea.setText("Please enter answer choice 1.");
                                createEnterText.setText("");
                            } else if (insideCount == 2) {
                                mc1 = createEnterText.getText();
                                createQuizArea.setText("Please enter answer choice 2.");
                                createEnterText.setText("");
                            } else if (insideCount == 3) {
                                mc2 = createEnterText.getText();
                                createQuizArea.setText("Please enter answer choice 3.");
                                createEnterText.setText("");
                            } else if (insideCount == 4) {
                                mc3 = createEnterText.getText();
                                createQuizArea.setText("Please enter answer choice 4.");
                                createEnterText.setText("");
                            } else if (insideCount == 5) {
                                mc4 = createEnterText.getText();
                                createEnterText.setText("");
                                //MCQ is not needed now
                                //readQuiz.addQuestion(new MCQuestion(input, mc1, mc2, mc3, mc4));
                                Client.sendStuffToTheServer(name, "MC: " + input);
                                Client.sendStuffToTheServer(name, String.format("A: %s B: %s C: %s D: %s", mc1, mc2, mc3, mc4));
                                Client.sendStuffToTheServer(name, " ");
                                createQuizArea.setText("Would you like to add another question? (Y/N)");
                            } else if (insideCount == 6) {
                                input = createEnterText.getText().toLowerCase();
                                if (input.equals("y") || input.equals("yes")) {
                                    createQuizArea.setText("What type of question would you like to add to the quiz?\n" +
                                            "1. Multiple Choice Question\n" +
                                            "2. Free Response Question\n" +
                                            "3. Fill In the Blank Question\n");
                                    createEnterText.setText("");
                                    insideCount = -1;
                                } else {
                                    createQuizArea.setText("Would you like to randomize the order" +
                                            " of questions for this quiz? (Y/N)");
                                    createEnterText.setText("");
                                    insideCount = -1;
                                    createCount = 2;
                                }
                            }
                            insideCount ++;
                            break;
                        case 2:
                            if (insideCount == 0) {
                                createQuizArea.setText("Please enter the question.");
                                createEnterText.setText("");
                            } else if (insideCount == 1) {
                                input = createEnterText.getText();
                                //FRQ not needed now
                                //readQuiz.addQuestion(new FRQQuestion(input));
                                Client.sendStuffToTheServer(name, "FRQ: " + input);
                                Client.sendStuffToTheServer(name, "Answer: ");
                                Client.sendStuffToTheServer(name, " ");
                                createQuizArea.setText("Would you like to add another question? (Y/N)");
                                createEnterText.setText("");
                            } else if (insideCount == 2) {
                                input = createEnterText.getText().toLowerCase();
                                if (input.equals("y") || input.equals("yes")) {
                                    createQuizArea.setText("What type of question would you like to add to the quiz?\n" +
                                            "1. Multiple Choice Question\n" +
                                            "2. Free Response Question\n" +
                                            "3. Fill In the Blank Question\n");
                                    createEnterText.setText("");
                                    insideCount = -1;
                                } else {
                                    createQuizArea.setText("Would you like to randomize the order" +
                                            " of questions for this quiz? (Y/N)");
                                    createEnterText.setText("");
                                    insideCount = -1;
                                    createCount = 2;
                                }
                            }
                            insideCount ++;
                            break;
                        case 3:
                            if (insideCount == 0) {
                                createQuizArea.setText("Please enter the question.");
                                createEnterText.setText("");
                            } else if (insideCount == 1) {
                                input = createEnterText.getText();
                                //FIB not needed now
                                //readQuiz.addQuestion(new FillInBlankQuestion(input));
                                Client.sendStuffToTheServer(name, "Fill in the blank: " + input);
                                Client.sendStuffToTheServer(name, "Answer: _________");
                                Client.sendStuffToTheServer(name, " ");
                                createQuizArea.setText("Would you like to add another question? (Y/N)");
                                createEnterText.setText("");
                            } else if (insideCount == 2) {
                                input = createEnterText.getText().toLowerCase();
                                if (input.equals("y") || input.equals("yes")) {
                                    createQuizArea.setText("What type of question would you like to add to the quiz?\n"
                                            + "1. Multiple Choice Question\n"
                                            + "2. Free Response Question\n"
                                            + "3. Fill In the Blank Question\n");
                                    createEnterText.setText("");
                                    insideCount = -1;
                                } else {
                                    createQuizArea.setText("Would you like to randomize the order" +
                                            " of questions for this quiz? (Y/N)");
                                    createEnterText.setText("");
                                    insideCount = -1;
                                    createCount = 2;
                                }
                            }
                            insideCount ++;
                            break;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Not a valid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                if (insideCount == 0) {
                    input = createEnterText.getText();
                    if (input.equals("y") || input.equals("yes")) {
                        createQuizArea.setText("Quiz randomized!" +
                                "\nYou have successfully created the quiz!" +
                                "\nPlease enter the course pathname you would like to add this quiz to.");
                        Client.sendStuffToTheServer(name, "y");

                    } else {
                        createQuizArea.setText("The quiz was not randomized." +
                                "\nYou have successfully created the quiz!" +
                                "\nPlease enter the course pathname you would like to add this quiz to.");
                    }
                } else if (insideCount == 1) {
                    input = createEnterText.getText();
                    createEnterText.setText("");
                    try {
                        //Don't need to add to course now
                        //Teacher.addToCourse(new File(input), readQuiz.getQuizFileName());
                        Client.sendStuffToTheServer(input, name);
                        createQuizArea.setText("Please press return to go back to the teacher menu.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,
                                "Not a valid course path", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                insideCount ++;
                break;
        }

    }

    public void runDeleteQuiz() {
        try {
            Client.sendStuffToTheServer(input, "");
            deleteQuizArea.setText("Quiz successfully deleted.");
            deleteQuizText.setText("");
        } catch (Exception ex) {
            deleteQuizArea.setText("Quiz was not deleted.");
            JOptionPane.showMessageDialog(null,
                    "Invalid file, does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void runModifyQuiz() {
        switch (modifyCount) {
            case 0:
                try {
                    setInput(modifyQuizText.getText());
                    //needs to read in from server
                    File file = new File(input);
                    //------------
                    String[][] quiz = Teacher.readQuizFile(file);
                    for (int i = 0; i < quiz.length; i++) {
                        modifyQuizArea.append(quiz[i][0] + "\n");
                        modifyQuizArea.append(quiz[i][1] + "\n\n");
                    }
                    modifyQuizText.setText("Make any changes and hit enter");
                    modifyCount = 1;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid file, does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                //write this to server replacing old quiz
                String[] temp = modifyQuizArea.getText().split("\\n");
                for (String s : temp) {
                    Client.sendStuffToTheServer(input, s);
                }
                break;
        }
    }

    public void runGradeQuiz() {
        switch (gradeCount) {
            case 0:
                try {
                    input = gradeQuizText.getText();
                    //would read from a file from the server here
                    File file = new File(input);
                    String[][] quiz = Teacher.readQuizTakenFile(file);
                    gradeQuizArea.setText("Enter the points earned below each question.\n");
                    for (int i = 0; i < quiz.length; i++) {
                        gradeQuizArea.append((i + 1) + " " + quiz[i][0] + "\n");
                        gradeQuizArea.append(quiz[i][1] + "\n");
                        gradeQuizArea.append(quiz[i][2] + "\n\n");
                    }
                    gradeCount = 1;
                    gradeQuizText.setText("Press enter when done.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid file, does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                gradeQuizArea.getText();
                //would write to the server with gradeQuizArea get text
                //that should give the entire quiz as graded
                String[] temp = gradeQuizArea.getText().split("\\n");
                for (String s : temp) {
                    Client.sendStuffToTheServer(input, s);
                }
                break;
        }
    }

    @Override
    public void run() {
        //Frame for all teacher options
        frame = new JFrame("Teacher");

        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        readQuizButton.addActionListener(actionListener);
        panel.add(readQuizButton);
        createQuizButton.addActionListener(actionListener);
        panel.add(createQuizButton);
        deleteQuizButton.addActionListener(actionListener);
        panel.add(deleteQuizButton);
        modifyQuizButton.addActionListener(actionListener);
        panel.add(modifyQuizButton);
        gradeQuizButton.addActionListener(actionListener);
        panel.add(gradeQuizButton);
        quitButton.addActionListener(actionListener);
        panel.add(quitButton);

        frame.add(panel, BorderLayout.CENTER);

        //frame for teacher viewing quiz
        readFrame = new JFrame("Read Quiz");
        readFrame.setSize(400, 335);
        readFrame.setLayout(new BorderLayout());
        readFrame.setLocationRelativeTo(null);
        readFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel readPanel = new JPanel();
        readPanel.add(readFileText);
        readPanel.add(readEnterButton);
        readPanel.add(readReturnButton);
        readPanel.add(readQuizArea);
        readEnterButton.addActionListener(actionListener);
        readReturnButton.addActionListener(actionListener);

        readFrame.add(readPanel, BorderLayout.CENTER);

        //frame for teacher creating a quiz
        createFrame = new JFrame("Create Quiz");
        createFrame.setSize(455, 200);
        createFrame.setLayout(new BorderLayout());
        createFrame.setLocationRelativeTo(null);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel createPanel = new JPanel();
        createPanel.add(createQuizArea);
        createPanel.add(createEnterText);
        createPanel.add(createEnterButton);
        createEnterButton.addActionListener(actionListener);
        createPanel.add(createReturnButton);
        createReturnButton.addActionListener(actionListener);

        createFrame.add(createPanel, BorderLayout.CENTER);

        //frame for teacher deleting quiz
        deleteFrame = new JFrame("Delete Quiz");
        deleteFrame.setSize(350, 150);
        deleteFrame.setLayout(new BorderLayout());
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel deletePanel = new JPanel();
        deletePanel.add(deleteQuizArea);
        deletePanel.add(deleteQuizText);
        deletePanel.add(deleteEnterButton);
        deletePanel.add(deleteReturnButton);
        deleteReturnButton.addActionListener(actionListener);
        deleteEnterButton.addActionListener(actionListener);

        deleteFrame.add(deletePanel, BorderLayout.CENTER);

        //frame for teacher modifying quiz
        modifyFrame = new JFrame("Modify Quiz");
        modifyFrame.setSize(400, 500);
        modifyFrame.setLayout(new BorderLayout());
        modifyFrame.setLocationRelativeTo(null);
        modifyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel modifyPanel = new JPanel();
        modifyPanel.add(modifyQuizText);
        modifyPanel.add(modifyEnterButton);
        modifyPanel.add(modifyReturnButton);
        modifyPanel.add(modifyQuizArea);
        modifyReturnButton.addActionListener(actionListener);
        modifyEnterButton.addActionListener(actionListener);

        modifyFrame.add(modifyPanel, BorderLayout.CENTER);

        //frame for grading quiz
        gradeFrame = new JFrame("Grade Quiz");
        gradeFrame.setSize(400, 400);
        gradeFrame.setLayout(new BorderLayout());
        gradeFrame.setLocationRelativeTo(null);
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel gradePanel = new JPanel();
        gradePanel.add(gradeQuizText);
        gradePanel.add(gradeEnterButton);
        gradePanel.add(returnButton);
        gradePanel.add(gradeQuizArea);
        returnButton.addActionListener(actionListener);
        gradeEnterButton.addActionListener(actionListener);

        gradeFrame.add(gradePanel, BorderLayout.CENTER);
    }

    //this will replace the current runTeacher call in the main method for the client
    public static void runTeacherGUI() {
        SwingUtilities.invokeLater(new TeacherGUI());
        //need to invoke runnable here

        /*while (returnValue == 0) {
            System.out.print("");
        }*/
        //return returnValue;
    }
}
