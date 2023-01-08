package checkers.Server.RequestHandlers;

import checkers.Server.Client;

public class ExitRequestHandler implements IRequestHandler{
    @Override
    public boolean canProcessRequest(Client client, String request) {
        return request.startsWith("EXIT");
    }

    @Override
    public void processRequest(Client client, String request) {

    }
}
