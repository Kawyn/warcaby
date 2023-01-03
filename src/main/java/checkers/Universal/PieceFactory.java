package checkers.Universal;

import checkers.Utils.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class PieceFactory {

    public enum PieceType {
        ENG_PAWN,
        ENG_QUEEN,
    }

    public static Piece createPieceByType(PieceType type) {

        Piece piece = new Piece();

        switch (type) {
            case ENG_PAWN:
                piece.setMovePattern(new ArrayList<Position>(Arrays.asList(
                        new Position(1, 1),
                        new Position(-1, 1)
                )));
                break;
            case ENG_QUEEN:
                piece.setMovePattern(new ArrayList<Position>(Arrays.asList(
                        new Position(1, 1),
                        new Position(-1, 1),
                        new Position(-1, 2)
                )));
                break;

        }

        return piece;
    }
}
