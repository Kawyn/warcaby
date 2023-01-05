package checkers.Server.RequestHandlers;

public interface IRequestHandler {

    public boolean canProcessRequest(String request) ;
    public void processRequest(String Request);
}
