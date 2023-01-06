package checkers.Server;

public class Client {
    public static int idCounter = 0;
    private int id;
    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
