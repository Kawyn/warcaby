package checkers.Server;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

    private static final Data instance = new Data();
    private final ArrayList<Game> games = new ArrayList<>();
    private final ArrayList<Client> clients = new ArrayList<>();
    private final HashMap<String, Client> queues = new HashMap<>();

    public static Data getInstance() {
        return instance;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public Game getGameByPlayer(Client client) {
        for (Game game : games) {
            if (game.getPlayers()[0] == client || game.getPlayers()[1] == client) return game;
        }
        return null;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public HashMap<String, Client> getQueues() {
        return queues;
    }
}