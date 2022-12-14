package checkers.Server.RequestHandlers;

import checkers.Server.Client;
import checkers.Server.Data;
import checkers.Server.Game;
import checkers.Universal.GameLogics.GameResult;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;

/**
 * Move request have form of: "MOVE_startX_startY_destinationX_destination_Y_captureX_captureY
 */
public class MoveRequestHandler implements IRequestHandler {

    @Override
    public boolean canProcessRequest(Client client, String request) {
        return request.startsWith("MOVE_");
    }

    @Override
    public void processRequest(Client client, String request) {

        Game game = Data.getInstance().getGameByPlayer(client);

        if (game == null) return;
        if (client != game.getPlayers()[game.getGameState().getWhoseTurn().ordinal()]) return;

        String[] args = request.split("_");

        Vector2D start = new Vector2D(args[1], args[2]);
        Vector2D destination = new Vector2D(args[3], args[4]);

        Vector2D capture = new Vector2D(args[5], args[6]);

        Move move = new Move(start, destination, capture);

        if (game.getGameState().gameLogic.isMoveLegal(game.getGameState(), move)) {

            game.getGameState().gameLogic.move(game.getGameState(), move);
            game.broadcast(request);

            GameResult gameResult = game.getGameState().gameLogic.getGameResult(game.getGameState());

            if (gameResult == GameResult.WHITE_WON) {
                game.playercast(0, "WON");
                game.playercast(1, "LOST");
                Data.getInstance().getGames().remove(game);

            } else if (gameResult == GameResult.BLACK_WON) {
                game.playercast(0, "LOST");
                game.playercast(1, "WON");
                Data.getInstance().getGames().remove(game);

            }

        }
    }
}
