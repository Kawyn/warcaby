package checkers.Server;

import checkers.Universal.GameStates.GameState;

public class Game {

    private final Client[] players;
    public int IllegalMovesWarning;
    private GameState gameState;

    public Game(Client[] players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Client[] getPlayers() {
        return players;
    }

    public void broadcast(String message) {

        for (Client player : players)
            player.getOut().println(message);
    }

    public void playercast(int idx, String message) {
        players[idx].getOut().println(message);
    }

    public void playercast(Client client, String message) {
        client.getOut().println(message);
    }

    public void opponentcast(Client client, String message) {

        if (players[0] == client) players[1].getOut().println(message);
        else players[0].getOut().println(message);
    }
}
