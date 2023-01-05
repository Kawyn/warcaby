package checkers.Server;

import checkers.Server.SerwerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serwer extends Thread{
    static ArrayList<SerwerThread> threads = new ArrayList<>();
    int port = 8080;

    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("SERVER: Server is listening on port: " + port);

            while (threads.size()<2) {
                Socket socket = serverSocket.accept();
                System.out.println("SERVER: New client connected");

                SerwerThread thread = new SerwerThread(socket);
                thread.start();
                threads.add(thread);
            }
        }
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
