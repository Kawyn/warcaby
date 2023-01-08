package checkers.Universal.GameLogics.MoveControllers;

import checkers.Universal.Structs.Move;

public interface IMovesController {

    boolean isMoveLegal(Move move);
}
