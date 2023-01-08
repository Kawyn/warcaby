package checkers.Server.RequestHandlers;

import checkers.Server.Client;
import checkers.Server.Data;

public class LeaveQueueRequestHandler implements IRequestHandler {
    @Override
    public boolean canProcessRequest(Client client, String request) {
        return request.startsWith("LEAVE_QUEUE");
    }

    @Override
    public void processRequest(Client client, String request) {

        String type = request.split("_")[2];
        Data.getInstance().getQueues().remove(type);
    }
}
