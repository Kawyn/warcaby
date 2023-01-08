package checkers.Server.RequestHandlers;

import checkers.Server.Client;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RequestManager {

    private static final IRequestHandler[] requestHandlers = new IRequestHandler[]{new JoinQueueRequestHandler(), new LeaveQueueRequestHandler(), new MoveRequestHandler(), new ExitRequestHandler(), new SurrenderRequestHandler()};

    public static void processRequest(Client client, String request) {

        System.out.println("[" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + "] Request '" + request + "' from client " + client.getID());

        for (IRequestHandler handler : requestHandlers) {

            if (handler.canProcessRequest(client, request)) {

                handler.processRequest(client, request);
                return;
            }
        }
    }
}
