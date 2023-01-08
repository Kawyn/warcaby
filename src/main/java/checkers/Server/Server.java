package checkers.Server;

import checkers.Universal.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(200);

        try (ServerSocket serverSocket = new ServerSocket(Config.PORT)) {

            System.out.println("[CHECKERS_SERVER] listening on port*: " + Config.PORT);

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("[CHECKERS_SERVER] New client!");

                Client client = new Client(socket, UUID.randomUUID().toString());
                Data.getInstance().getClients().add(client);
                pool.submit(client);
            }
        } catch (IOException ex) {

            System.out.println("[CHECKERS_SERVER] " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
