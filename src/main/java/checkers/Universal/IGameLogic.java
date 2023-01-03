package checkers.Universal;

import java.util.ArrayList;

public interface IGameLogic {

    public int IsGameOver();
    public ArrayList<Move> GetPossibleMoves(Piece piece);

    public void TryToMove(Move move);
}
