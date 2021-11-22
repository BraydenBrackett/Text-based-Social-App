package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Manages the running of the client system that connects to the server.
 * Client GUI, options, and different windows are managed from this class.
 *
 * @author Brayden Brackett, sec L12
 * @version 11/22/2021
 */
public class Client {
    public static void makeError(String message) {
        JOptionPane.showMessageDialog(null, message, "Quiz System",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Main method the client runs from.
     *
     * @param args default constructor for main method.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /**
         * TODO: Figure out port and hostname stuff...is it automatic/should there be multiple ports...
         */
        boolean run = true; //boolean for whether the client should be running
        int port = 0; //default port
        String hostName = ""; //default host name

        try {
            //WELCOMES THE USER
            JOptionPane.showMessageDialog(null, "WELCOME", "Quiz System",
                    JOptionPane.PLAIN_MESSAGE);

            //PROMPTS FOR HOSTNAME
            boolean hostnameRun = true;
            do {
                hostName = JOptionPane.showInputDialog(null, "Enter Hostname", "Quiz System",
                        JOptionPane.QUESTION_MESSAGE);
                if (hostName.equals("")) {
                    makeError("Please enter a hostname");
                } else {
                    hostnameRun = false;
                }
            } while (hostnameRun);
        } catch (Exception e) {
            run = false;
        }

        //PROMPTS USER FOR PORT
        if (run == true) {
            boolean portRun = true;
            do {
                String portString = JOptionPane.showInputDialog(null, "Enter a Port", "Quiz System",
                        JOptionPane.QUESTION_MESSAGE);
                if (portString == null || portString.equals(JOptionPane.CANCEL_OPTION) || portString.equals(JOptionPane.CANCEL_OPTION)) {
                    run = false;
                    portRun = false;
                } else {
                    try {
                        port = Integer.parseInt(portString);
                        portRun = false;
                    } catch (Exception e) {
                        makeError("Please enter a port that's a number");
                    }
                }
            } while (portRun);
        }

        //ATTEMPTS TO CONNECT TO SERVER
        BufferedReader reader = null;
        PrintWriter writer = null;
        if (run == true) {
            try {
                Socket socket = new Socket(hostName, port);

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Couldn't connect, goodbye!!!", "Quiz System",
                        JOptionPane.ERROR_MESSAGE);
                run = false;
            }
        }
        if (run == true) {
            //DISPLAYS CONNECTION MESSAGE
            try {
                JOptionPane.showMessageDialog(null, "Client Connected!!!", "Quiz System",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                run = false;
            }
        }
        //PROMPTS USER FOR STRING
        if (run == true) {
            boolean stringRun = true;
            do {
                String stringToServer = JOptionPane.showInputDialog(null, "Type to send something to server", "Quiz System",
                        JOptionPane.QUESTION_MESSAGE);
                if (stringToServer == null || stringToServer.equals(JOptionPane.CANCEL_OPTION) || stringToServer.equals(JOptionPane.CANCEL_OPTION)) {
                    run = false;
                    stringRun = false;
                } else {
                    try {
                        if (stringToServer.equals("")) {
                            throw new Exception();
                        }
                        writer.write(stringToServer);
                        writer.println();
                        writer.flush();
                        stringRun = false;
                    } catch (Exception e) {
                        makeError("Please enter a string");
                    }
                }
            } while (stringRun);
        }

        //PRINTS WHAT THE SERVER SENT BACK
        if (run == true) {
            try {
                String s1 = reader.readLine();
                JOptionPane.showMessageDialog(null, s1, "Quiz System",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                run = false;
            }
        }

        //HANDLES ANY CONNECTION ERRORS AND CLOSES THE CLIENT
        if (run == false) {
            try {
                writer.close();
                reader.close();
                JOptionPane.showMessageDialog(null, "Goodbye!!!", "Quiz System",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Closed due to connection issues, goodbye!!!", "Quiz System",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
