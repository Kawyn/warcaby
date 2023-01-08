package checkers;

import checkers.Server.Client;
import checkers.Server.Data;
import checkers.Server.Game;
import checkers.Server.RequestHandlers.RequestManager;
import checkers.Universal.GameStates.GameState;
import checkers.Universal.GameStates.GameStateFactory;
import checkers.Universal.GameStates.GameType;
import checkers.Universal.Structs.Vector2D;
import org.junit.Assert;
import org.junit.Test;

public class JUnitMove {

    @Test
    public void testMoveEnglish() {

        Client client = new Client(null, "");
        Game game = new Game(new Client[]{client, client});

        GameState gameState = GameStateFactory.createGameState(GameType.ENGLISH);
        game.setGameState(gameState);

        Data.getInstance().getGames().add(game);

        RequestManager.processRequest(client, "MOVE_2_2_3_3_-1_-1");

        Assert.assertNull(gameState.getPieceByVector2D(new Vector2D(2, 2)));
    }

    @Test
    public void testMoveTurkish() {

        Client client = new Client(null, "");
        Game game = new Game(new Client[]{client, client});

        GameState gameState = GameStateFactory.createGameState(GameType.TURKISH);
        game.setGameState(gameState);

        Data.getInstance().getGames().add(game);

        RequestManager.processRequest(client, "MOVE_2_2_3_3_-1_-1");

        Assert.assertNull(gameState.getPieceByVector2D(new Vector2D(2, 2)));
    }

    @Test
    public void testMoveRussian() {

        Client client = new Client(null, "");
        Game game = new Game(new Client[]{client, client});

        GameState gameState = GameStateFactory.createGameState(GameType.RUSSIAN);
        game.setGameState(gameState);

        Data.getInstance().getGames().add(game);

        RequestManager.processRequest(client, "MOVE_2_2_3_3_-1_-1");

        Assert.assertNull(gameState.getPieceByVector2D(new Vector2D(2, 2)));
    }
}
