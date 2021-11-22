package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Manages the running of the server for the system. Allows clients to
 * connect and then manages data for them.
 *
 * @author Brayden Brackett, sec L12
 * @version 11/22/2021
 */
public class Server {
    /**
     * Main method the server runs from.
     *
     * @param args default constructor for main method.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null; //Reads in the data
        PrintWriter writer = null; //Prints out the data
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            //Server has a timeout of 30 seconds
            serverSocket.setSoTimeout(30 * 1000);
            Socket socket = serverSocket.accept();

            /**
             * Note: Loop is only terminated after server timeout or if an error
             * is thrown. Errors will be thrown if the client disconnects.
             * */
            while (true) {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

                String message = reader.readLine();

                String response = "Server was given: " + message;

                writer.write(response);
                writer.println();
                writer.flush();

            }
        } catch (Exception e) {
            if (writer != null && reader != null) {
                writer.close();
                reader.close();
            }
            return;
        }
    }
}
