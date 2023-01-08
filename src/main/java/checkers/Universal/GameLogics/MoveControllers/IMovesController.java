package checkers.Universal.GameLogics.MoveControllers;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.Structs.Move;

public interface IMovesController {

    boolean isMoveLegal(GameState gameState, Move move);
}
