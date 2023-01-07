package checkers.Server;

import checkers.Client.Interface.Controllers.SystemController;
import checkers.Server.SerwerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serwer extends Thread{
    private SystemController systemController;
    static ArrayList<SerwerThread> threads = new ArrayList<>();
    int port;

    public Serwer(int port){
        this.port = port;
    }

    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("SERVER: Server is listening on port: " + port);

            // A co jeżeli chcą zagrać w inne rodzaje szachów?
            while (threads.size()<2) {
                Socket socket = serverSocket.accept();
                System.out.println("SERVER: New client connected");
                if (!(Integer.parseInt(String.valueOf(socket.getInputStream())) ==systemController.getWariant())){
                    serverSocket.close();
                }
                else {
                SerwerThread thread = new SerwerThread(socket, ++Client.idCounter);
                thread.start();
                threads.add(thread);}
            }
        }
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
