package checkers.Client;

import checkers.Universal.GameStates.GameState;

public class ClientData {

    public  static GameState currentGameState;
    private static ClientData instance = new ClientData();

    public static ClientData getInstance() {
        return instance;
    }
}
