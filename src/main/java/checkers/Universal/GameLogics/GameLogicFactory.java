package checkers.Universal.GameLogics;

import checkers.Universal.GameLogics.MoveGenerators.*;

public class GameLogicFactory {

    public static GameLogic createGameLogic(String type) {

        GameLogic gameLogic = new GameLogic();

        //TODO
        switch (type) {
            case "ENGLISH":
                   gameLogic.setPawnMoveGenerator(new PawnEnglish());
                   gameLogic.setQueenMoveGenerator(new QueenEnglish());
                break;
            case "RUSSIAN":
                gameLogic.setPawnMoveGenerator(new PawnRussian());
                gameLogic.setQueenMoveGenerator(new QueenRussian());
                break;
            case "TURKISH":
                gameLogic.setPawnMoveGenerator(new PawnTurkish());
                gameLogic.setQueenMoveGenerator(new QueenTurkish());
                break;
        }

        return gameLogic;
    }
}
