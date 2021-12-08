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

    public static Socket socket;

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

        //WELCOMES THE USER
        try {
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
                socket = new Socket("localhost", 4242);

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

            } catch (Exception e) {
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
            /**
             * TODO: DO YOUR THINGY HERE THAT MAKES THE PROGRAM MOVE ON FROM THIS LINE WHEN THIS METHOD IS DONE RUNNING
             * */
            i = TeacherGUI.runTeacherGUI();
            
            if (i == 1) {
                run = false;
            }
        }

        //PRINTS WHAT THE SERVER SENT BACK - only used for testing
        /*if (run == true) {
            try {
                String s1 = reader.readLine();
                JOptionPane.showMessageDialog(null, s1, "Quiz System",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                run = false;
            }
        }*/

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

    /**
     * Method called in other classes to send things to server
     *
     * @param input String to be sent to server
     * @throws IOException
     */
    public static void sendToServer(String input) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.write(input);
        writer.println();
        writer.flush();
    }
}
