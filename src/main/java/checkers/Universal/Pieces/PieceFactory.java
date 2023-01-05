package checkers.Universal.Pieces;

public class PieceFactory {

    public static Piece createPiece(String type) {

        Piece piece = new Piece();

        piece.setType(type);

        return piece;
    }
}
