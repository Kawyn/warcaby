package checkers.Server.RequestHandlers;

public interface IRequestHandler {

    public boolean canProcessRequest(String request) ;

    // Najpewniej powinno być do proces request przekazywany jakiś identyfikator klienta
    public void processRequest(String Request);
}
