package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Login
 * <p>
 * Handles:
 * -Logging into accounts
 * -Managing file that keeps list of accounts
 * -Account interface upon logging into the project
 *
 * @author Harshini Musku and Brandon Kingma, L12
 * @version December 13 2021
 */
public class Login extends JComponent implements Runnable {
    /**
     * FOR THE PERSON WHO WRITES THE MAIN PROGRAM - enter in the path to the .txt file that stores the accounts;
     */
    private static String filepath = "newAccounts.txt";

    private static String welcome = "-----------Welcome to the quiz system-----------\n"
            + "You will be prompted for your details below.\nNOTE: If an account with the provided"
            + " details doesn't exist,\na new one will be created for you.";
    private static String usernamePrompt = "\nUsername (',' is not a valid character):";
    private static String passwordPrompt = "Password (',' is not a valid character):";
    private static String confirmPasswordPrompt = "Confirm Password (',' is not a valid character):";

    private static String teacherPrompt = "Is this a teacher account? (Y/N)";
    private static String credentialsError = "Please ensure that:\n1. Your username and password" +
            " aren't empty\n2. Your username and pass word don't contain ','\n3. "
            + "That you've given a response of y/n/Y/N"
            + " for whether the account is a teacher account.";
    Graphics2D graphics2D;  // this will enable drawing
    JLabel login;
    JLabel unLabel;
    JLabel pwLabel;

    JLabel unLabel1;
    JLabel pwLabel1;

    public static int isTeacher = 3;


    static JTextField username; // text field for username
    static JTextField password; // text field for password
    JButton submit; // button to submit credentials

    JTextField username1; // text field for username
    JTextField password1; // text field for password
    JButton submit2; // button to submit credentials

    JButton registerHere; // button to submit credentials
    JButton loginHere; // button to submit credentials

    Login loginGUI; // variable of the type Paint
    Color color;
    Color filledColor;
    Random randGen;
    JFrame frameLogin;
    JFrame frameRegister;
    JFrame frameMenu;
    JFrame frameAccount;
    JFrame frameEditDel;
    JFrame frameEdit;
    JButton editAcc;
    JButton deleteAcc;
    JButton returnButton;
    JLabel editDelPrompt;

    JLabel accountLabel;
    JButton student;
    JButton teacher;
    static String teacherYN;//holds y/n based on if the account is a student or a teacher

    /**
     * Main runner method that should be called in main.
     * Right now it either creates a new account or logs in the account with the given details
     * provided by the user of this program.
     */
    public void run() {

        randGen = new Random();
        //All the frames used in the login interface
        frameLogin = new JFrame("Login");
        frameRegister = new JFrame("Register");
        frameMenu = new JFrame("Menu");
        frameAccount = new JFrame("Account Selection");
        frameEditDel = new JFrame("Edit/Delete Account");
        frameEdit = new JFrame("Edit Account");

        Container contentAcc = frameAccount.getContentPane();
        Container contentAccEditDel = frameEditDel.getContentPane();

        Container contentLogin = frameLogin.getContentPane();
        contentAccEditDel.setLayout(new BorderLayout());
        contentLogin.setLayout(new BorderLayout());
        loginGUI = new Login();
        contentLogin.add(loginGUI, BorderLayout.CENTER);

        Container contentRegister = frameRegister.getContentPane();
        contentRegister.setLayout(new BorderLayout());
        contentRegister.add(loginGUI, BorderLayout.CENTER);

        Container contentMenu = frameMenu.getContentPane();
        contentMenu.setLayout(new BorderLayout());
        contentMenu.add(loginGUI, BorderLayout.CENTER);
        //GUI for creating all the buttons, lables, and textfields for the frames.
        JPanel panelPrompt = new JPanel();
        JPanel panelEditDel = new JPanel();
        editDelPrompt = new JLabel("Would you like to edit or delete your account?");
        editDelPrompt.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelPrompt.add(editDelPrompt);
        contentAccEditDel.add(panelPrompt, BorderLayout.NORTH);

        editAcc = new JButton("Edit Account");
        panelEditDel.add(editAcc);
        contentAccEditDel.add(panelEditDel, BorderLayout.CENTER);

        deleteAcc = new JButton("Delete Account");
        panelEditDel.add(deleteAcc);
        contentAccEditDel.add(panelEditDel, BorderLayout.CENTER);

        returnButton = new JButton("No");
        JPanel panelNo = new JPanel();
        panelNo.add(returnButton);
        contentAccEditDel.add(panelNo, BorderLayout.SOUTH);

        JPanel panelUN = new JPanel();
        JPanel panelPW = new JPanel();
        JPanel panelSubmit = new JPanel();

        JPanel panelUN1 = new JPanel();
        JPanel panelPW1 = new JPanel();
        JPanel panelSubmit1 = new JPanel();
        JPanel panelLogin = new JPanel();
        JPanel panelRegister = new JPanel();

        JPanel panelAcc1 = new JPanel();
        JPanel panelAcc2 = new JPanel();

        login = new JLabel("LOG IN:");
        login.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelLogin.add(login);
        contentLogin.add(panelLogin, BorderLayout.NORTH);

        JLabel register = new JLabel("REGISTER:");
        register.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelRegister.add(register);
        contentRegister.add(panelRegister, BorderLayout.NORTH);

        Container contentEdit = frameEdit.getContentPane();
        JPanel username123 = new JPanel();
        JPanel password123 = new JPanel();

        JLabel usernameLabel = new JLabel("Enter Username:");
        username123.add(usernameLabel);
        contentEdit.add(username123, BorderLayout.CENTER);

        JTextField us = new JTextField(10);
        username123.add(us);
        contentEdit.add(username123, BorderLayout.CENTER);

        JLabel passwordL = new JLabel("Enter Password:");
        username123.add(passwordL);
        contentEdit.add(username123, BorderLayout.CENTER);

        JTextField pw = new JTextField(10);
        username123.add(pw);
        contentEdit.add(username123, BorderLayout.CENTER);

        JButton done = new JButton("Done");
        JPanel donePanel = new JPanel();
        donePanel.add(done);
        contentEdit.add(donePanel, BorderLayout.SOUTH);


        unLabel = new JLabel("Enter Username:");
        unLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelUN.add(unLabel);
        contentLogin.add(panelUN, BorderLayout.WEST);
        username = new JTextField(10);
        panelUN.add(username);
        contentLogin.add(panelUN, BorderLayout.WEST);

        unLabel1 = new JLabel("Enter Username:");
        unLabel1.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelUN1.add(unLabel1);
        contentRegister.add(panelUN1, BorderLayout.CENTER);

        username1 = new JTextField(10);
        panelUN1.add(username1);
        contentRegister.add(panelUN1, BorderLayout.CENTER);

        pwLabel = new JLabel("Enter Password:");
        pwLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelUN.add(pwLabel);
        contentLogin.add(panelUN, BorderLayout.WEST);

        pwLabel1 = new JLabel("Enter Password:");
        pwLabel1.setFont(new Font("Calibri", Font.PLAIN, 12));
        panelUN1.add(pwLabel1);
        contentRegister.add(panelUN1, BorderLayout.CENTER);


        password = new JTextField(10);
        panelUN.add(password);
        //contentLogin.add(panelUN1, BorderLayout.WEST);

        password1 = new JTextField(10);
        panelUN1.add(password1);
        contentRegister.add(panelUN1, BorderLayout.CENTER);
        //contentRegister.add(panelUN, BorderLayout.CENTER);

        submit = new JButton("SUBMIT");
        panelSubmit.add(submit);
        contentLogin.add(panelSubmit, BorderLayout.SOUTH);

        JButton submit2 = new JButton("SUBMIT");
        panelSubmit1.add(submit2);
        contentRegister.add(panelSubmit1, BorderLayout.SOUTH);

        registerHere = new JButton("Register Here");
        panelSubmit.add(registerHere);

        loginHere = new JButton("Login Here");
        contentLogin.add(panelSubmit, BorderLayout.SOUTH);
        panelSubmit1.add(loginHere);
        contentRegister.add(panelSubmit1, BorderLayout.SOUTH);

        accountLabel = new JLabel("What type of account is this?");
        accountLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        panelAcc1.add(accountLabel);
        contentAcc.add(panelAcc1, BorderLayout.NORTH);

        student = new JButton("Student");
        panelAcc2.add(student);
        contentAcc.add(panelAcc2, BorderLayout.CENTER);
        teacher = new JButton("Teacher");
        panelAcc2.add(teacher);
        contentAcc.add(panelAcc2, BorderLayout.CENTER);
        frameLogin.setSize(485, 130);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameLogin.setVisible(true);

        frameRegister.setSize(300, 180);
        frameRegister.setLocationRelativeTo(null);
        frameRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameEdit.setSize(300, 130);
        frameEdit.setLocationRelativeTo(null);
        frameEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameEditDel.setSize(300, 130);
        frameEditDel.setLocationRelativeTo(null);
        frameEditDel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frameLogin.setVisible(true);

        frameAccount.setSize(300, 100);
        frameAccount.setLocationRelativeTo(null);
        frameAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //action listeners to flow through the gui and run code as buttons are pressed.
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String un = username.getText();
                String pw = password.getText();
                //System.out.println(un);
                //System.out.println(pw);
                frameLogin.setVisible(false);
                frameEditDel.setVisible(false);
                //frameAccount.setVisible(true);
                frameAccount.setVisible(true);
            }
        });
        submit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String un = username.getText();
                String pw = password.getText();
                //System.out.println(un);
                //System.out.println(pw);
                frameRegister.setVisible(false);
                frameEditDel.setVisible(false);
                //frameAccount.setVisible(true);
                frameAccount.setVisible(true);
            }
        });
        registerHere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerFrame();
            }
        });
        loginHere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginFrame();
            }
        });
        teacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                teacherYN = "y";
                //System.out.println(teacherYN);
                String us = username.getText();
                if (us.equals("")) {
                    us = username1.getText();
                }
                String pw = password.getText();
                if (us.equals("")) {
                    pw = password1.getText();
                }
                if (!doesAccountExist(us, pw, true)) {
                    Account acc = createNewAccount(us, pw, true);
                    addAccountToFile(acc);
                }
                frameEditDel.setVisible(true);
            }

        });
        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                teacherYN = "n";
                String us = username.getText();
                if (us.equals("")) {
                    us = username1.getText();
                }
                String pw = password.getText();
                if (us.equals("")) {
                    pw = password1.getText();
                }
                if (!doesAccountExist(us, pw, false)) {
                    Account acc = createNewAccount(us, pw, false);
                    addAccountToFile(acc);
                }


                try {
                    login();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frameEditDel.setVisible(true);
            }
        });

        editAcc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameEdit.setVisible(true);
                frameLogin.setVisible(false);
                frameRegister.setVisible(false);
                frameEditDel.setVisible(false);
            }
        });
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAccount(username.getText(), us.getText(), pw.getText());
                frameEdit.setVisible(false);
                us.setText("");
                pw.setText("");
                frameEditDel.setVisible(true);
            }
        });

        deleteAcc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAccount(username1.getText());
                //frameEdit.setVisible(true);
                frameLogin.setVisible(false);
                frameRegister.setVisible(false);
                JOptionPane.showMessageDialog(null, "Account Deleted!");
            }
        });
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameEdit.setVisible(false);
                //frameAccount.setVisible(true);
                frameEditDel.setVisible(false);
                //System.out.println("teacher is:" + teacherYN);
                if (teacherYN.equals("y")) {
                    isTeacher = 1;
                    //TeacherGUI.runTeacherGUI();
                } else {
                    isTeacher = 0;
                    //StudentGUI.runStudentGUI();
                }
            }
        });
    }

    /**
     * Makes the login frame visible and clears the username + password textfields
     */
    public void loginFrame() {
        frameLogin.setVisible(true);
        frameRegister.setVisible(false);
        frameMenu.setVisible(false);
        username.setText("");
        password.setText("");
    }

    /**
     * Makes the register frame visible and clears the username + password textfields
     */
    public void registerFrame() {
        frameLogin.setVisible(false);
        frameRegister.setVisible(true);
        frameMenu.setVisible(false);
        username1.setText("");
        password1.setText("");
    }

    protected void paintComponent(Graphics g) {

    }


    public void draw(int size) {
        graphics2D.setStroke(new BasicStroke(size));
    }

    public Login() {
    }

    /**
     * method used to login users
     */
    public void runAccountLoginProcess() {
        Scanner scanner = new Scanner(System.in);
        boolean runLoginProcess = true;
        Account loggedInAccount;

        //System.out.println(welcome);
        while (runLoginProcess) {
            String usernme = username.getText();

            String psswrd = password.getText();

            //System.out.println(teacherPrompt);
            String isTeacher = scanner.nextLine();
            if (isTeacher.equals("") || usernme.equals("") || psswrd.equals("")) {
                JOptionPane.showMessageDialog(null, credentialsError);
            } else if (!isTeacher.toLowerCase().equals("y") && !isTeacher.toLowerCase().equals("n")) {
                JOptionPane.showMessageDialog(null, credentialsError);
            } else if (usernme.contains(",") || usernme.contains(",")) {
                JOptionPane.showMessageDialog(null, credentialsError);
            } else {
                runLoginProcess = false;
                boolean isTeacherFlag = isTeacher.toLowerCase().equals("y");
                if (!doesAccountExist(usernme, psswrd, isTeacherFlag)) {
                    loggedInAccount = createNewAccount(usernme, psswrd, isTeacherFlag);
                    addAccountToFile(loggedInAccount);
                }
            }
        }
        /**
         * At this point we've logged in a returning or new user we have:
         * - A file with the details of each user
         * - An account ;[[object for our logged in user @Account loggedInAccount
         *
         * Next steps should be:
         * - Account management interfaces (editing and deleting accounts)
         * - Adding and removing courses to accounts
         * */

    }

    /**
     * edits an account based on passed parameters and adds account to
     *
     * @param password - new password of user
     * @param username - new username of user
     * @param tempAcc  -  old username
     */
    public static void editAccount(String username, String password, String tempAcc) {
        Client.sendStuffToTheServer("Accounts", "*");
        ArrayList<Account> accounts = readInAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(tempAcc)) {
                accounts.get(i).setUsername(username);
                accounts.get(i).setPassword(password);
            }
        }
        addAccountsToFile(accounts);
    }

    /**
     * deletes an account based on passed parameters and adds account to
     *
     * @param tempAcc -  old username
     */
    public static void deleteAccount(String tempAcc) {
        Client.sendStuffToTheServer("Accounts", "*");
        ArrayList<Account> accounts = readInAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(tempAcc)) {
                accounts.remove(i);
            }
        }
        addAccountsToFile(accounts);
    }

    /**
     * Creates a new account based on passed parameters and adds account to
     * file that stores all the accounts
     *
     * @param password  - password of user
     * @param username  - username of user
     * @param isTeacher -  boolean of whether given user is a teacher
     * @return returns a new account of given parameters
     */
    public static Account createNewAccount(String username, String password, boolean isTeacher) {
        return new Account(username, password, isTeacher);
    }

    /**
     * Writes an account to the end file that stores all of them
     *
     * @param account - account to be stored to file
     * @Important each account is on its own line in the format: username,password,isTeacher
     */
    public static void addAccountToFile(Account account) {
        Client.sendStuffToTheServer("Accounts", account.getUsername() + "," +
                account.getPassword() + "," +
                account.isTeacher() + "\n");
    }

    /**
     * adds all the accounts to the server
     *
     * @param Arraylist of all the accounts
     */
    public static void addAccountsToFile(ArrayList<Account> accounts) {
        Client.sendStuffToTheServer("Accounts", "");
        for (Account account : accounts) {
            addAccountToFile(account);
        }
    }

    /**
     * checks if the user account already exists.
     *
     * @param password  - password of user
     * @param username  - username of user
     * @param isTeacher -  boolean of whether given user is a teacher
     * @return true or false based on whether an account with the given parameters exists
     */
    public static boolean doesAccountExist(String username, String password, boolean isTeacher) {
        ArrayList<Account> accountsFromFile = readInAccounts();
        if (accountsFromFile == null) {
            return false;
        }

        Account credentialsWereChecking = new Account(username, password, isTeacher);
        for (int i = 0; i < accountsFromFile.size(); i++) {
            if (credentialsWereChecking.equals(accountsFromFile.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads in the accounts from the file that stores all of them and returns them
     * as an arraylist in the order they were stored in the file.
     *
     * @return accounts from file as an arraylist in the order they were stored in the file.
     */
    public static ArrayList<Account> readInAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            boolean isTeacher;
            BufferedReader br = new BufferedReader(new FileReader("newAccounts"));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    String[] accountDetails = line.split(",");
                    isTeacher = accountDetails[2].equals("true");
                    accounts.add(createNewAccount(accountDetails[0], accountDetails[1], isTeacher));
                }
            }
        } catch (Exception e) {
            return null;
        }

        return accounts;
    }

    /**
     * Logs in a user and displays an errors on JOptionPance
     */
    public void login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean runLoginProcess = true;
        Account loggedInAccount = new Account();
        boolean isTeacherFlag = false;
        Teacher teacher = null;
        String usrnme = "";
        String psswrd = "";

        //System.out.println(welcome);
        while (runLoginProcess) {
            usrnme = username.getText();

            psswrd = password.getText();

            String isTeacher = teacherYN;
            //System.out.println(isTeacher);

            if (isTeacher.equals("") || usrnme.equals("") || psswrd.equals("")) {
                JOptionPane.showMessageDialog(null, credentialsError);
            } else if (!isTeacher.toLowerCase().equals("y") && !isTeacher.toLowerCase().equals("n")) {
                JOptionPane.showMessageDialog(null, credentialsError);
            } else if (psswrd.contains(",") || usrnme.contains(",")) {
                JOptionPane.showMessageDialog(null, credentialsError);
            } else {
                runLoginProcess = false;
                isTeacherFlag = isTeacher.toLowerCase().equals("y");
                if (!doesAccountExist(usrnme, psswrd, isTeacherFlag)) {
                    loggedInAccount = createNewAccount(usrnme, psswrd, isTeacherFlag);
                    //Client.sendStuffToTheServer("Accounts.txt",addAccountToFile(loggedInAccount));
                    if (isTeacherFlag) {
                        teacher = new Teacher(usrnme, psswrd, true);
                    }
                }
            }
        }
    }

    /**
     * runs the GUI of Login interface
     */
    public static int runLogin() throws IOException {
        SwingUtilities.invokeLater(new Login());
        while (isTeacher == 3) {
            System.out.println("");
        }
        return isTeacher;
    }
}
