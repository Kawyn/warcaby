package checkers.Client;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.PlayerColor;
import checkers.Utils.ObservableValue;

public class Data {

    private PlayerColor currentColor;

    public PlayerColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(PlayerColor currentColor) {
        this.currentColor = currentColor;
    }

    private final ObservableValue<String> getLastRequest = new ObservableValue<>("");

    public ObservableValue<String> getLastRequest() {
        return getLastRequest;
    }

    private static final Data instance = new Data();
    private GameState gameState;

    public static Data getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
