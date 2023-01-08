package checkers.Client;

import checkers.Universal.GameStates.GameState;
import checkers.Utils.ObservableValue;

public class ClientData {

    private final ObservableValue<String> getLastRequest = new ObservableValue<>("");

    public ObservableValue<String> getLastRequest() {
        return getLastRequest;
    }

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
