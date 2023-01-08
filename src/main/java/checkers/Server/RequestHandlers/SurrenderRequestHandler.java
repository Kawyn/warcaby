package checkers.Server.RequestHandlers;

import checkers.Server.Client;
import checkers.Server.Data;
import checkers.Server.Game;

public class SurrenderRequestHandler implements IRequestHandler {
    @Override
    public boolean canProcessRequest(Client client, String request) {
        return request.startsWith("SURRENDER");
    }

    @Override
    public void processRequest(Client client, String request) {

        Game game = Data.getInstance().getGameByPlayer(client);

        if (game == null) return;

        game.playercast(client, "LOST");
        game.opponentcast(client, "WON");
    }
}
