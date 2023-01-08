package checkers.Server;

import checkers.Server.RequestHandlers.RequestManager;

import java.io.*;
import java.net.Socket;


public class Client implements Runnable {
    private  BufferedReader in;
    private  PrintWriter out;

    public PrintWriter getOut() {
        return out;
    }

    OutputStream output;

    private final String ID;

    public String getID() {
        return ID;
    }

    private final Socket socket;
    public Client(Socket socket, String ID) {
        this.socket = socket;
        this.ID = ID;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            output = socket.getOutputStream();
            out = new PrintWriter(output, true);

            String line;
            do {
                line = in.readLine();
                RequestManager.processRequest(this, line);
            } while (!line.equals("EXIT"));

            socket.close();

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
