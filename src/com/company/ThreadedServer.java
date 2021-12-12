import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer extends Thread{

    private ServerSocket serverSocket;
    private boolean serverRunning = false;
    private int port;
    
    //Main method, once client connects, the rest of the code is executed.
    public static void main(String[] args) {
        ThreadedServer server = new ThreadedServer(4242);
        server.startSever();
    }
    
    public ThreadedServer(int port) {
        this.port = port;
    }

    public void startSever() {
        try {
            serverSocket = new ServerSocket(4242);
            this.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        serverRunning = true;
        while (serverRunning == true) {
            try {
                Socket socket = serverSocket.accept();

                // Pass the socket to the RequestHandler thread for processing
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Handles all server requests
    class RequestHandler extends Thread {
        private Socket socket;

        RequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Get input and output streams
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                while (true) {
                    //Reads in the two lines that are always sent
                    String filepath = reader.readLine();
                    String message = reader.readLine();
                    //Writes those contents to a given file, unless the message is blank
                    if (!message.equals("")) {
                        writeToFile(filepath, message);
                    } else {
                        clearFile(filepath);
                    }
                    //Sends that file with the updated info back to the client
                    String toServer = readFile(filepath);
                    writer.write(toServer);
                    writer.println();
                    writer.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param filename .txt name of file to be read from
     * @return a string with ','s between lines of file
     */
    public static String readFile(String filename){
        String rtn = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String readIn;
            while ((readIn = br.readLine()) != null) {
                rtn += readIn + ",";
            }
        } catch (Exception e){
            System.out.println("You didn't put in a correct filepath");
        }
        return rtn;
    }

    /**
     * @param filename .txt name of file to be written to
     * @param contents string line to be added to file, will sometimes create empty rows at the front of the list
     */
    public static void writeToFile(String filename, String contents){
        try{
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write(contents + "\n");
            fileWriter.close();
        } catch (Exception e){
            System.out.println("You didn't put in a correct filepath");
        }
    }

    /**
     * @param filename path to file that's being cleared
     */
    public static void clearFile(String filename){
        try{
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.write("");
            fileWriter.close();
        } catch (Exception e){
            System.out.println("You didn't put in a correct filepath");
        }
    }
}