package checkers.Universal.GameLogics.MoveGenerators;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;

import java.util.ArrayList;

public class QueenBasic implements IMoveGenerator {

    @Override
    public ArrayList<Move> getPossibleMoves(GameState gameState, Piece piece) {

        ArrayList<Move> result = new ArrayList<>();

        for (int xModifier = -1; xModifier <= 1; xModifier += 2) {
            for (int yModifier = -1; yModifier <= 1; yModifier += 2) {

                for (int i = 1; i < 8; i++) {

                    Vector2D destination = new Vector2D(piece.x + i * xModifier, piece.y + i * yModifier);

                    if (gameState.isInBounds(destination)) {

                        Piece target = gameState.getPieceByVector2D(destination);

                        if (target == null) {
                            result.add(new Move(new Vector2D(piece.x, piece.y), destination, null));
                        } else {

                            if (target.getColor().equals(piece.getColor()))
                                break;

                            Vector2D further = new Vector2D(piece.x + (i + 1) * xModifier, piece.y + (i + 1) * yModifier);

                            if (gameState.isInBounds(further)) {

                                if (gameState.getPieceByVector2D(further) == null) {
                                    result.add(new Move(new Vector2D(piece.x, piece.y), further, target));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
