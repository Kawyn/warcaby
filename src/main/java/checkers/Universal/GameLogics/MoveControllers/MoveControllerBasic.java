package checkers.Universal.GameLogics.MoveControllers;

import checkers.Universal.Structs.Move;

public class MoveControllerBasic implements IMovesController{

    @Override
    public boolean isMoveLegal(Move move) {
        return true;
    }
}
