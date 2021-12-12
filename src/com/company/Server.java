/**
 *
 * Old Server Class, DO NOT USE
 *
 * */



//package com.company;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//
///**
// * Manages the running of the server for the system. Allows clients to
// * connect and then manages data for them.
// *
// * @author Brayden Brackett, sec L12
// * @version 11/22/2021
// */
//public class Server {
//    /**
//     * Main method the server runs from.
//     *
//     * @param args default constructor for main method.
//     * @throws IOException
//     */
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = null; //Reads in the data
//        PrintWriter writer = null; //Prints out the data
//        try {
//            ServerSocket serverSocket = new ServerSocket(4242);
//            //Server has a timeout of 30 seconds
//            serverSocket.setSoTimeout(30 * 1000);
//            Socket socket = serverSocket.accept();
//
//            /**
//             * Note: Loop is only terminated after server timeout or if an error
//             * is thrown. Errors will be thrown if the client disconnects.
//             * */
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            writer = new PrintWriter(socket.getOutputStream());
//            while (true) {
//                //Reads in the two lines that are always sent
//                String filepath = reader.readLine();
//                String message = reader.readLine();
//                //Writes those contents to a given file, unless the message is blank
//                if(!message.equals("") && !message.equals("*")) {
//                    writeToFile(filepath, message);
//                } else if (message.equals("")) {
//                    clearFile(filepath);
//                }
//                //Sends that file with the updated info back to the client
//                String toServer = readFile(filepath);
//                writer.write(toServer);
//                writer.println();
//                writer.flush();
//            }
//        } catch (Exception e) {
//            if (writer != null && reader != null) {
//                writer.close();
//                reader.close();
//            }
//            return;
//        }
//    }
//
//    /**
//     * @param filename .txt name of file to be read from
//     * @return a string with ','s between lines of file
//     */
//    public static String readFile(String filename){
//        String rtn = "";
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(filename));
//            String readIn;
//            while ((readIn = br.readLine()) != null) {
//                rtn += readIn + ",";
//            }
//        } catch (Exception e){
//            System.out.println("You didn't put in a correct filepath");
//        }
//        return rtn;
//    }
//
//    /**
//     * @param filename .txt name of file to be written to
//     * @param contents string line to be added to file, will sometimes create empty rows at the front of the list
//     */
//    public static void writeToFile(String filename, String contents){
//        try{
//            FileWriter fileWriter = new FileWriter(filename, true);
//            fileWriter.write(contents + "\n");
//            fileWriter.close();
//        } catch (Exception e){
//            System.out.println("You didn't put in a correct filepath");
//        }
//    }
//
//    /**
//     * @param filename path to file that's being cleared
//     */
//    public static void clearFile(String filename){
//        try{
//            FileWriter fileWriter = new FileWriter(filename, false);
//            fileWriter.write("");
//            fileWriter.close();
//        } catch (Exception e){
//            System.out.println("You didn't put in a correct filepath");
//        }
//    }
//}