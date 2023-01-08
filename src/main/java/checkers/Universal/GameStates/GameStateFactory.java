package checkers.Universal.GameStates;

import checkers.Universal.GameLogics.GameLogicFactory;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Pieces.PieceFactory;
import checkers.Universal.PlayerColor;

public class GameStateFactory {

    public static GameState createGameState(GameType type) {

        GameState gameState = new GameState();

        if (type == GameType.ENGLISH || type == GameType.RUSSIAN || type == GameType.TURKISH) {
            gameState.setWidth(8);
            gameState.setHeight(8);

            while (gameState.getPieces().size() < 64) gameState.getPieces().add(null);
        }

        if (type == GameType.ENGLISH || type == GameType.RUSSIAN) {

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 3; y++) {

                    int idx = gameState.getIdxByPosition(x, y);

                    if ((x + y) % 2 != 0) {
                        Piece piece = PieceFactory.createPiece("PAWN");

                        piece.setX(x);
                        piece.setY(y);

                        piece.setColor(PlayerColor.WHITE);

                        gameState.getPieces().set(idx, piece);
                    }
                }

                for (int y = 7; y > 4; y--) {

                    int idx = gameState.getIdxByPosition(x, y);

                    if ((x + y) % 2 != 0) {
                        Piece piece = PieceFactory.createPiece("PAWN");

                        piece.setX(x);
                        piece.setY(y);

                        piece.setColor(PlayerColor.BLACK);

                        gameState.getPieces().set(idx, piece);
                    }
                }
            }

            if (type == GameType.ENGLISH) {
                gameState.gameLogic = GameLogicFactory.createGameLogic("ENGLISH");
                gameState.setWhoseTurn(PlayerColor.BLACK);
            }

            if (type == GameType.RUSSIAN) {
                gameState.gameLogic = GameLogicFactory.createGameLogic("RUSSIAN");
                gameState.setWhoseTurn(PlayerColor.BLACK);
            }
        } else if (type == GameType.TURKISH) {
            gameState.setWhoseTurn(PlayerColor.WHITE);
            for (int x = 0; x < 8; x++) {
                for (int y = 1; y < 3; y++) {

                    int idx = gameState.getIdxByPosition(x, y);

                    Piece piece = PieceFactory.createPiece("PAWN");

                    piece.setX(x);
                    piece.setY(y);

                    piece.setColor(PlayerColor.WHITE);

                    gameState.getPieces().set(idx, piece);
                }

                for (int y = 6; y > 4; y--) {

                    int idx = gameState.getIdxByPosition(x, y);

                    Piece piece = PieceFactory.createPiece("PAWN");

                    piece.setX(x);
                    piece.setY(y);

                    piece.setColor(PlayerColor.BLACK);

                    gameState.getPieces().set(idx, piece);

                    gameState.gameLogic = GameLogicFactory.createGameLogic("TURKISH");
                }
            }
        }

        return gameState;
    }
}
