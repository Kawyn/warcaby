package checkers.Universal.GameLogics.MoveGenerators;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;

import java.util.ArrayList;

public class QueenTurkish implements IMoveGenerator {
    @Override
    public ArrayList<Move> getPossibleMoves(GameState gameState, Piece piece) {
        ArrayList<Move> result = new ArrayList<>();

        for (int xModifier = -1; xModifier <= 1; xModifier += 2) {
            for (int yModifier = -1; yModifier <= 1; yModifier += 2) {

                for (int i = 1; i < 8; i++) {

                    Vector2D destination = new Vector2D(piece.x, piece.y + i * yModifier);

                    if (gameState.isInBounds(destination)) {

                        Piece target = gameState.getPieceByVector2D(destination);

                        if (target == null) {
                            result.add(new Move(new Vector2D(piece.x, piece.y), destination, new Vector2D(-1, -1)));
                        } else {

                            if (target.getColor().equals(piece.getColor()))
                                break;

                            Vector2D further = new Vector2D(piece.x, piece.y + (i + 1) * yModifier);

                            if (gameState.isInBounds(further)) {

                                if (gameState.getPieceByVector2D(further) == null) {
                                    result.add(new Move(new Vector2D(piece.x, piece.y), further, destination));
                                    break;
                                }
                            }
                            break;
                        }
                    }

                }
                for (int j = 1; j < 8; j++) {
                    Vector2D destinationSide = new Vector2D(piece.x + j * xModifier, piece.y);
                    if (gameState.isInBounds(destinationSide)) {

                        Piece target = gameState.getPieceByVector2D(destinationSide);

                        if (target == null) {
                            result.add(new Move(new Vector2D(piece.x, piece.y), destinationSide, new Vector2D(-1, -1)));
                        } else {

                            if (target.getColor().equals(piece.getColor()))
                                break;

                            Vector2D further = new Vector2D(piece.x + (j + 1) * xModifier, piece.y);

                            if (gameState.isInBounds(further)) {

                                if (gameState.getPieceByVector2D(further) == null) {
                                    result.add(new Move(new Vector2D(piece.x, piece.y), further, destinationSide));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }
}
