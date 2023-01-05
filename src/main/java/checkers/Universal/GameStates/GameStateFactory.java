package checkers.Universal.GameStates;

import checkers.Universal.GameLogics.GameLogic;
import checkers.Universal.GameLogics.GameLogicFactory;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Pieces.PieceFactory;
import checkers.Universal.PlayerColor;
import checkers.Utils.CheckersMath;

public class GameStateFactory {

    public enum GameStateType {
        ENGLISH,
        RUSSIAN,
        TURKISH
    }

    public static GameState createGameStateByType(GameStateType type) {

        GameState gameState = new GameState();

        switch (type) {
            case ENGLISH:

                gameState.setWidth(8);
                gameState.setHeight(8);

                for (int i = 0; gameState.getPieces().size() < 64; i++)
                    gameState.getPieces().add(null);

                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 3; y++) {
                        int idx = CheckersMath.squarePositionToIdx(x, y);
                        if ((x + y) % 2 == 0) {

                            Piece piece = PieceFactory.createPieceByType("PAWN");

                            piece.setX(x);
                            piece.setY(y);

                            piece.setColor(PlayerColor.WHITE);

                            gameState.getPieces().set(idx, piece);
                        }
                    }
                }

                for (int x = 0; x < 8; x++) {
                    for (int y = 7; y > 4; y--) {
                        int idx = CheckersMath.squarePositionToIdx(x, y);
                        if ((x +y) % 2 == 0) {

                            Piece piece = PieceFactory.createPieceByType("PAWN");

                            piece.setX(x);
                            piece.setY(y);

                            piece.setColor(PlayerColor.BLACK);

                            gameState.getPieces().set(idx, piece);
                        }
                    }
                }

                gameState.gameLogic = GameLogicFactory.createGameLogic("ENGLISH");



                break;
            case RUSSIAN:
                break;

            case TURKISH:
                break;

        }

        return gameState;
    }
}
