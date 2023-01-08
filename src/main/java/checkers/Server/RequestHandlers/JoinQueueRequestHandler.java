package checkers.Server.RequestHandlers;

import checkers.Server.Client;
import checkers.Server.Data;
import checkers.Server.Game;
import checkers.Universal.GameStates.GameStateFactory;
import checkers.Universal.GameStates.GameType;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class JoinQueueRequestHandler implements IRequestHandler {
    @Override
    public boolean canProcessRequest(Client client, String request) {
        return request.startsWith("JOIN_QUEUE");
    }

    @Override
    public void processRequest(Client client, String request) {

        String type = request.split("_")[2];

        HashMap<String, Client> queues = Data.getInstance().getQueues();

        if (queues.containsValue(client)) return;

        if (queues.containsKey(type)) {

            ArrayList<Game> games = Data.getInstance().getGames();

            System.out.println("[" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + "] Starting game of '" + type.toLowerCase() + "' variant for clients " + queues.get(type).getID() + " && " + client.getID());

            Game game = new Game(new Client[]{queues.get(type), client});

            game.setGameState(GameStateFactory.createGameState(GameType.valueOf(type)));

            games.add(game);

            game.playercast(0, "START_GAME_WHITE");
            game.playercast(1, "START_GAME_BLACK");

            queues.remove(type);

        } else {
            queues.put(type, client);
        }
    }
}
