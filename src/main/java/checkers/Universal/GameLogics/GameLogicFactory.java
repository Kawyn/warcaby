package checkers.Universal.GameLogics;

import checkers.Universal.GameLogics.MoveGenerators.PawnBasic;
import checkers.Universal.GameLogics.MoveGenerators.QueenBasic;

public class GameLogicFactory {

    public static GameLogic createGameLogic(String type) {

        GameLogic gameLogic = new GameLogic();

        if (type.equals("ENGLISH")) {

            gameLogic.setPawnMoveGenerator(new PawnBasic());
            gameLogic.setQueenMoveGenerator(new QueenBasic());
        } else if (type.equals("RUSSIAN")) {

            gameLogic.setPawnMoveGenerator(new PawnBasic());
            gameLogic.setQueenMoveGenerator(new QueenBasic());
        }
        return gameLogic;
    }
}
