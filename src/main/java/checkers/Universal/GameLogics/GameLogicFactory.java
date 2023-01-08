package checkers.Universal.GameLogics;

import checkers.Universal.GameLogics.MoveGenerators.PawnBasic;
import checkers.Universal.GameLogics.MoveGenerators.PawnTurkish;
import checkers.Universal.GameLogics.MoveGenerators.QueenBasic;
import checkers.Universal.GameLogics.MoveGenerators.QueenTurkish;

public class GameLogicFactory {

    public static GameLogic createGameLogic(String type) {

        GameLogic gameLogic = new GameLogic();

        //TODO
        switch (type) {
            case "ENGLISH":
                gameLogic.setPawnMoveGenerator(new PawnBasic());
                gameLogic.setQueenMoveGenerator(new QueenBasic());
                break;
            case "RUSSIAN":
             //   gameLogic.setPawnMoveGenerator(new PawnBasic());
             //   gameLogic.setQueenMoveGenerator(new QueenBasic());
                break;
            case "TURKISH":
                gameLogic.setPawnMoveGenerator(new PawnTurkish());
                gameLogic.setQueenMoveGenerator(new QueenTurkish());
                break;
        }

        return gameLogic;
    }
}
