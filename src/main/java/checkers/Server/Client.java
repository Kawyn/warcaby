package checkers.Server;

import checkers.Client.Interface.Controllers.SystemController;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private SystemController systemController;
    private static BufferedReader in;
    private static PrintWriter out;
    public static int idCounter = 0;
    private final int id;
    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void initConnection(int port){
        try{
            Socket s1 = new Socket("localhost", port);
            out = new PrintWriter(s1.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            out.println(systemController.getWariant());
        }
        catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błąd!");
            alert.setHeaderText(null);
            alert.setContentText("Błąd połączenia z serwerem!");
            alert.showAndWait();
            e.printStackTrace();
            System.out.println("Could not connect. Check the hostname and port.");
            System.exit(0);
        }
    }
}
