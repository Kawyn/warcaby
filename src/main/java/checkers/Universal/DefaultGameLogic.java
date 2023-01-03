package checkers.Universal;

import checkers.Utils.Position;

import java.util.ArrayList;

public class DefaultGameLogic implements IGameLogic{

    @Override
    public ArrayList<Move> GetPossibleMoves(Piece piece) {

        ArrayList<Move> result = new ArrayList<>();

        for (Position pos : piece.getMovePattern()) {

            int x = piece.x + pos.x;
            int y = piece.y + pos.y;
            System.out.println("x: " + x + ", y" + y);
            if (x < 8 && x > 0 && y < 8 && y > 0)
                result.add(new Move(x, y));
        }

        return result;
    }

    @Override
    public void TryToMove(Move move) {
        System.out.println("Try to move");
    }

    @Override
    public int IsGameOver() {
        return 0;
    }
}
