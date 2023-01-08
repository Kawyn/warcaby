package checkers.Server.RequestHandlers;

import checkers.Server.Client;

public interface IRequestHandler {

    boolean canProcessRequest(Client client, String request);


    void processRequest(Client client, String request);
}
