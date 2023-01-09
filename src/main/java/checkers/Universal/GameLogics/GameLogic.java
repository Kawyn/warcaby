package checkers.Universal.GameLogics;

import checkers.Universal.GameLogics.MoveControllers.IMovesController;
import checkers.Universal.GameLogics.MoveControllers.MoveControllerBasic;
import checkers.Universal.GameLogics.MoveGenerators.IMoveGenerator;
import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.PlayerColor;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;

import java.util.ArrayList;

public class GameLogic {

    private final IMovesController moveController = new MoveControllerBasic();
    private IMoveGenerator queenMoveGenerator;
    private IMoveGenerator pawnMoveGenerator;

    public void setPawnMoveGenerator(IMoveGenerator pawnMoveGenerator) {
        this.pawnMoveGenerator = pawnMoveGenerator;
    }

    public void setQueenMoveGenerator(IMoveGenerator queenMoveGenerator) {
        this.queenMoveGenerator = queenMoveGenerator;
    }

    public boolean isMoveLegal(GameState gameState, Move move) {
        return moveController.isMoveLegal(gameState, move);
    }

    public ArrayList<Move> removeIllegalMoves(GameState gameState, ArrayList<Move> moves) {

        ArrayList<Move> result = new ArrayList<>();

        for (Move move : moves)
            if (isMoveLegal(gameState, move)) result.add(move);

        return result;
    }

    public ArrayList<Move> getPossibleMoves(GameState gameState, Piece piece) {

        ArrayList<Move> result;

        result = getPossibleCaptures(gameState, piece);
        if (result.size() == 0) result = getPossibleMovesInternal(gameState, piece);

        return removeIllegalMoves(gameState, result);
    }

    private ArrayList<Move> getPossibleMovesInternal(GameState gameState, Piece piece) {

        if (piece.getType().equals("QUEEN")) return queenMoveGenerator.getPossibleMoves(gameState, piece);
        if (piece.getType().equals("PAWN")) return pawnMoveGenerator.getPossibleMoves(gameState, piece);

        return new ArrayList<>();
    }

    public ArrayList<Move> getPossibleCaptures(GameState gameState, Piece piece) {

        ArrayList<Move> result = new ArrayList<>();

        for (Move move : getPossibleMovesInternal(gameState, piece)) {
            if (gameState.isInBounds(move.getCapture())) result.add(move);
        }
        return removeIllegalMoves(gameState, result);
    }

    public void move(GameState gameState, Move move) {

        Piece piece = gameState.getPieceByVector2D(move.getStart());
        piece.x = move.getDestination().x;
        piece.y = move.getDestination().y;

        gameState.getPieces().set(gameState.getIdxByVector(move.getStart()), null);
        gameState.getPieces().set(gameState.getIdxByVector(move.getDestination()), piece);

        if (gameState.isInBounds(move.getCapture())) {
            gameState.getPieces().set(gameState.getIdxByVector(new Vector2D(move.getCapture().x, move.getCapture().y)), null);


            gameState.previousMove = move;

            ArrayList<Move> moves = getPossibleCaptures(gameState, piece);
            System.out.println(moves.size());
            if (moves.size() == 0) {

                gameState.previousMove = null;
                gameState.setWhoseTurn(PlayerColor.values()[Math.abs(gameState.getWhoseTurn().ordinal() - 1)]);

                System.out.println("ee" + gameState.getWhoseTurn());
                promote(piece);
            }
        } else {
            gameState.previousMove = null;
            gameState.setWhoseTurn(PlayerColor.values()[Math.abs(gameState.getWhoseTurn().ordinal() - 1)]);
            System.out.println(gameState.getWhoseTurn());
            promote(piece);
        }
    }

    public void promote(Piece piece) {

        if (piece.y == 0 && piece.color == PlayerColor.BLACK) piece.setType("QUEEN");

        if (piece.y == 7 && piece.color == PlayerColor.WHITE) piece.setType("QUEEN");
    }

    public GameResult getGameResult(GameState gameState) {

        ArrayList<Piece> A = gameState.getPiecesByColor(PlayerColor.BLACK);

        if (A.size() == 0) return GameResult.WHITE_WON;

        int movesA = 0;

        for (Piece a : A)
            if (getPossibleMoves(gameState, a).size() != 0) movesA++;

        ArrayList<Piece> B = gameState.getPiecesByColor(PlayerColor.WHITE);

        if (B.size() == 0) return GameResult.BLACK_WON;

        int movesB = 0;

        for (Piece b : B)
            if (getPossibleMoves(gameState, b).size() != 0) movesB++;

        if (gameState.getWhoseTurn() == PlayerColor.BLACK) if (movesA == 0) return GameResult.WHITE_WON;
        if (gameState.getWhoseTurn() == PlayerColor.WHITE) if (movesB == 0) return GameResult.BLACK_WON;

        return GameResult.IN_PROGRESS;
    }
}
