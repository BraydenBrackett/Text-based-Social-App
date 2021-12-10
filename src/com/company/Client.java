package com.company;

import javax.swing.*;
import java.io.*;
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
    public static BufferedReader reader = null;

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
            //Login.main(null);
            TeacherGUI.runTeacherGUI();
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

    /**
     * Method called in other classes to send things to server
     *
     * @param line1 String to be sent to server
     * @throws IOException
     */
    public static void sendToServer(String line1, String line2) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.write(line1);
        writer.println();
        writer.write(line2);
        writer.println();
        writer.flush();
    }

    /**
     * @param filename .txt name of file to be written to
     */
    public static void readResponseAndWriteItToAFile(String filename){
        try{
            String s1 = reader.readLine();
            String[] lines = s1.split(",");
            FileWriter fileWriter = new FileWriter("new" + filename);
            for(int i = 0; i < lines.length; i++){
                fileWriter.write(lines[i] + "\n");
            }
            fileWriter.close();
        } catch (Exception e){
            System.out.println("Error reading to and creating a new file");
        }
    }

    /**
     * @param filename .txt name of file from string line to be sent to
     * @param contents string line to be sent to the server and added
     * -contents of "" will clear the file
     * -contents of * will just return the file contents without adding anything
     */
    public static void sendStuffToTheServer(String filename, String contents){
        try{
            Client.sendToServer(filename, contents);
            Client.readResponseAndWriteItToAFile(filename);
        } catch (Exception s){
            System.out.println("Sending and returning from server");
        }
    }

}
