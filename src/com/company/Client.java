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
        boolean run = true; //boolean for whether the client should be running

        try {
            //WELCOMES THE USER
            JOptionPane.showMessageDialog(null, "WELCOME", "Quiz System",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            run = false;
        }

        //ATTEMPTS TO CONNECT TO SERVER
        BufferedReader reader = null;
        PrintWriter writer = null;
        if (run == true) {
            try {

                /**
                 * TODO: pretty sure that the hostname and port are going to have the be different, look it up on campuswire and online
                 */
                Socket socket = new Socket("localhost", 4242);

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

            } catch (Exception e) {
                run = false;
            }
        }
        //DISPLAYS CONNECTION MESSAGE
        if (run == true) {
            try {
                JOptionPane.showMessageDialog(null, "Client Connected!!!", "Quiz System",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                run = false;
            }
        }
        //Runs main application
        if (run == true) {
            //All methods that need to be run will be called within here
            TeacherGUI.runTeacherGUI();
            //----------------------------------------------------------
            /**TODO
             * Issue with this. Basically it runs this right after the call above without waiting for it to finish
             * Not sure how to fully fix it, my guess is that it's something to do with concurrency.
             * */
            run = false;
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
