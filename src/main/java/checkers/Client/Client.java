package checkers.Client;

import checkers.Universal.Config;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private static final Client instance = new Client();
    static BufferedReader in;
    static PrintWriter out;
    private static Socket socket;

    public static void request(String message) {

        out.println(message);
    }

    public static Client getInstance() {
        return instance;
    }

    public void run() {

        try {
            socket = new Socket(Config.SERVER_ADDRESS, Config.PORT);
            InputStream input = socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(input));

            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                line = in.readLine();
                System.out.println(line);
                ClientData.getInstance().getLastRequest().setValue(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}