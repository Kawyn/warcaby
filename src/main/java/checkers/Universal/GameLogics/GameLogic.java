package checkers.Universal.GameLogics;

import checkers.Universal.GameLogics.MoveControllers.IMovesController;
import checkers.Universal.GameLogics.MoveControllers.MoveControllerBasic;
import checkers.Universal.GameLogics.MoveGenerators.IMoveGenerator;
import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.PlayerColor;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {

    private IMoveGenerator queenMoveGenerator;
    private IMoveGenerator pawnMoveGenerator;

    private final IMovesController moveController = new MoveControllerBasic();

    public void setPawnMoveGenerator(IMoveGenerator pawnMoveGenerator) {
        this.pawnMoveGenerator = pawnMoveGenerator;
    }

    public void setQueenMoveGenerator(IMoveGenerator queenMoveGenerator) {
        this.queenMoveGenerator = queenMoveGenerator;
    }

    public boolean isMoveLegal(Move move) {
        return moveController.isMoveLegal(move);
    }

    public ArrayList<Move> getPossibleMoves(GameState gameState, Piece piece) {

        if (piece.getType().equals("QUEEN")) return queenMoveGenerator.getPossibleMoves(gameState, piece);

        if (piece.getType().equals("PAWN")) return pawnMoveGenerator.getPossibleMoves(gameState, piece);

        return null;
    }

    public void move(GameState gameState, Move move) {

        Piece piece = gameState.getPieceByVector2D(move.getStart());
        piece.x = move.getDestination().x;
        piece.y = move.getDestination().y;

        gameState.getPieces().set(gameState.getIdxByVector(move.getStart()), null);
        gameState.getPieces().set(gameState.getIdxByVector(move.getDestination()), piece);

        if (gameState.isInBounds(move.getCapture()))
            gameState.getPieces().set(gameState.getIdxByVector(new Vector2D(move.getCapture().x, move.getCapture().y)), null);

        promote(piece);
    }

    public void promote(Piece piece) {

        if (piece.y == 0 && piece.color == PlayerColor.BLACK) piece.setType("QUEEN");

        if (piece.y == 7 && piece.color == PlayerColor.WHITE) piece.setType("QUEEN");
    }

    public GameResult getGameResult(GameState gameState) {

        ArrayList<Piece> A = gameState.getPiecesByColor("000000");

        int movesA = 0;

        for (Piece a : A)
            if (getPossibleMoves(gameState, a).size() != 0) movesA++;

        ArrayList<Piece> B = gameState.getPiecesByColor("FFFFFF");

        int movesB = 0;

        for (Piece b : B)
            if (getPossibleMoves(gameState, b).size() != 0) movesB++;

        if (movesA == 0) return GameResult.WHITE_WON;
        if (movesB == 0) return GameResult.BLACK_WON;

        else return GameResult.IN_PROGRESS;
    }
}
