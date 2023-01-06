package checkers.Server;

import checkers.Server.Client;
import checkers.Client.MessageCommand;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class SerwerThread extends Thread{

    private RequestManager requestManager = new RequestManager();

    private Socket socket;
    private Client client;
    OutputStream output;
    PrintWriter out;

    SerwerThread(Socket socket , int id){
        this.socket = socket;
        this.client = new Client(id);
    }

    public void run(){
        try {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            output = socket.getOutputStream();
            out = new PrintWriter(output, true);

            String line;
            do {
                line = in.readLine();
                processCommand(line);

            } while (!line.equals("bye"));
            socket.close();

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void processCommand(String line) {
        this.out.println(line);

        // Zrobiłbym coś w tym stylu, tylko jeszcze powinno być przekazwyane poza line jakieś ID klienta itp. (patrz komentarz w serwer)
        int id= client.getId();
        RequestManager.processRequest(line, id);

        //sendToOther(MessageCommand...);
        System.out.println(line);
    }

    protected void sendToOther(String message){
        for(SerwerThread thread : Serwer.threads)
            if(thread!=this){
                thread.out.println(message);
            }
    }
}
