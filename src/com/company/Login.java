import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * import java.io.*;
 * import java.util.ArrayList;
 * import java.util.Scanner;
 * <p>
 * /**
 * Class that handles account management and logging into accounts
 * <p>
 * Handles:
 * -Logging into accounts
 * -Managing file that keeps list of accounts
 * -Account interface upon logging into the project
 */

/**
 * Login
 * <p>
 * runs the min method
 *
 * @author Harshini Musku and Brandon Kingma, L12
 * @version November 14 2021
 */
public class Login extends JComponent implements Runnable{
    /**
     * FOR THE PERSON WHO WRITES THE MAIN PROGRAM - enter in the path to the .txt file that stores the accounts;
     */
    private static String filepath = "Accounts.txt";

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


    static JTextField username; // text field for username
    static JTextField password; // text field for password
    JButton submit; // button to submit credentials

    JTextField username1; // text field for username
    JTextField password1; // text field for password
    JButton submit2; // button to submit credentials

    JButton registerHere; // button to submit credentials
    JButton loginHere; // button to submit credentials

    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate

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
    static String teacherYN;

    /**
     * Main runner method that should be called in main.
     * Right now it either creates a new account or logs in the account with the given details
     * provided by the user of this program.
     */
    public void run() {

        randGen = new Random();
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
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String un = username.getText();
                String pw = password.getText();
                System.out.println(un);
                System.out.println(pw);
                frameLogin.setVisible(false);
                //frameAccount.setVisible(true);
                frameEditDel.setVisible(true);
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
            }
        });
        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAccount.setVisible(false);
                teacherYN = "n";
                try {
                    login();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
                editAccount(username.getText(), us.getText(),pw.getText());
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
            }
        });
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameEdit.setVisible(false);
                frameAccount.setVisible(true);
                frameEditDel.setVisible(false);
            }
        });
    }

    public void loginFrame() {
        frameLogin.setVisible(true);
        frameRegister.setVisible(false);
        frameMenu.setVisible(false);
        username.setText("");
        password.setText("");
    }

    public void registerFrame() {
        frameLogin.setVisible(false);
        frameRegister.setVisible(true);
        frameMenu.setVisible(false);
        username1.setText("");
        password1.setText("");
    }

    protected void paintComponent(Graphics g) {
        // if (image == null) {
        //  image = createImage(getSize().width, getSize().height);

        // this lets us draw on the image (ie. the canvas)
        //graphics2D = (Graphics2D) image.getGraphics();

        // gives us better rendering quality for the drawing lines
        //  graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        //  RenderingHints.VALUE_ANTIALIAS_ON);

        // set canvas to white with default paint color
        // graphics2D.setPaint(Color.white);
        //graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        //graphics2D.setPaint(Color.black);
        //repaint();
    }
    //g.drawImage(image, 0, 0, null);



    public void draw(int size) {
        graphics2D.setStroke(new BasicStroke(size));
    }

    public Login() {
    }

    public void runAccountLoginProcess() {
        Scanner scanner = new Scanner(System.in);
        boolean runLoginProcess = true;
        Account loggedInAccount;

        System.out.println(welcome);
        while (runLoginProcess) {
            String usernme =username.getText() ;

            String psswrd = password.getText();

            System.out.println(teacherPrompt);
            String isTeacher = scanner.nextLine();
            if (isTeacher.equals("") || usernme.equals("") || psswrd.equals("")) {
                System.out.println(credentialsError);
            } else if (!isTeacher.toLowerCase().equals("y") && !isTeacher.toLowerCase().equals("n")) {
                System.out.println(credentialsError);
            } else if (usernme.contains(",") || usernme.contains(",")) {
                System.out.println(credentialsError);
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
     * Creates a new account based on passed parameters and adds account to
     * file that stores all the accounts
     *
     * @param password - password of user
     * @param username - username of user
     * @param tempAcc  -  username
     * @return returns a new account of given parameters
     */
    public static void editAccount(String username, String password, String tempAcc) {
        ArrayList<Account> accounts = readInAccounts();
        System.out.println(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(tempAcc)) {
                accounts.get(i).setUsername(username);
                accounts.get(i).setPassword(password);
            }
        }
        addAccountsToFile(accounts);
    }

    public static void deleteAccount(String tempAcc) {
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
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);
            fileWriter.write(account.getUsername() + ","
                    + account.getPassword() + "," + account.isTeacher() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Given filepath is invalid, please try again...");
        } catch (Exception e) {
            System.out.println("Error writing to file...");
        }
    }

    public static void addAccountsToFile(ArrayList<Account> accounts) {
        try {
            FileWriter fw = new FileWriter("Accounts.txt", false);
            String write = "";
            for (int i = 0; i < accounts.size(); i++) {
                write += accounts.get(i).getUsername() + ","
                        + accounts.get(i).getPassword() + "," + accounts.get(i).isTeacher() + "\n";
            }
            fw.write(write);
            fw.flush();
            fw.close();

        } catch (Exception e) {

            System.out.println("File does not exist.");

        }
    }

    /**
     * Creates a new account based on passed parameters and adds account to
     * file that stores all the accounts
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
            String line;
            boolean isTeacher;
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    String[] accountDetails = line.split(",");
                    isTeacher = accountDetails[2].equals("true");
                    accounts.add(createNewAccount(accountDetails[0], accountDetails[1], isTeacher));
                }
            }
        } catch (Exception e) {
            System.out.println("There was an error reading in the accounts...");
            return null;
        }

        return accounts;
    }
    public void login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean runLoginProcess = true;
        Account loggedInAccount = new Account();
        boolean isTeacherFlag = false;
        Teacher teacher = null;
        String usrnme = "";
        String psswrd = "";

        System.out.println(welcome);
        while (runLoginProcess) {
            usrnme = username.getText();

            psswrd = password.getText();

            String isTeacher = teacherYN;


            if (isTeacher.equals("") || usrnme.equals("") || psswrd.equals("")) {
                JOptionPane.showMessageDialog(null,credentialsError);
            } else if (!isTeacher.toLowerCase().equals("y") && !isTeacher.toLowerCase().equals("n")) {
                JOptionPane.showMessageDialog(null,credentialsError);
            } else if (psswrd.contains(",") || usrnme.contains(",")) {
                JOptionPane.showMessageDialog(null,credentialsError);
            } else {
                runLoginProcess = false;
                isTeacherFlag = isTeacher.toLowerCase().equals("y");
                if (!doesAccountExist(usrnme, psswrd, isTeacherFlag)) {
                    loggedInAccount = createNewAccount(usrnme, psswrd, isTeacherFlag);
                    addAccountToFile(loggedInAccount);
                    if (isTeacherFlag) {
                        teacher = new Teacher(usrnme, psswrd, true);
                    }
                }
            }
        }

        if (isTeacherFlag) {
            TeacherGUI.runTeacherGUI();
        } else {
            StudentGUI.runStudentGUI();
        }
    }
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Login());

    }
}
