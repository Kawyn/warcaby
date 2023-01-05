package checkers.Client;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.PlayerColor;

public class ClientData {

    private static final ClientData instance = new ClientData();
    private GameState gameState;

    public static ClientData getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
