package checkers.Universal.GameLogics.MoveControllers;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Structs.Move;

import java.util.ArrayList;

public class MoveControllerBasic implements IMovesController {

    @Override
    public boolean isMoveLegal(GameState gameState, Move move) {

        ArrayList<Piece> pieces = gameState.getPiecesByColor(gameState.getWhoseTurn());


        if (move.getCapture().x == -1) {

            for (Piece piece : gameState.getPiecesByColor(gameState.getPieceByVector2D(move.getStart()).getColor())) {
                if (gameState.gameLogic.getPossibleCaptures(gameState, piece).size() > 0) {
                    return false;
                }
            }
        }
        if (gameState.previousMove != null) {
            if (!gameState.isInBounds(move.getCapture())) return false;

            return move.getStart().x == gameState.previousMove.getDestination().x && move.getStart().y == gameState.previousMove.getDestination().y;
        }
        return true;
    }
}
