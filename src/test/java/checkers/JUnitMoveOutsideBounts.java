package checkers;

import checkers.Universal.GameLogics.MoveControllers.MoveControllerBasic;
import checkers.Universal.GameStates.GameStateFactory;
import checkers.Universal.GameStates.GameType;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;
import org.junit.Assert;
import org.junit.Test;

public class JUnitMoveOutsideBounts {

    @Test
    public void testOutsideBounds() {

        boolean result = new MoveControllerBasic().isMoveLegal(
                GameStateFactory.createGameState(GameType.ENGLISH),
                new Move(new Vector2D(0, 0), new Vector2D(10, 10), new Vector2D(-1, -1))
        );

        Assert.assertTrue(result);
    }
}
