package checkers.Server.RequestHandlers;

public class ExampleRequestHandler implements IRequestHandler{

    @Override
    public boolean canProcessRequest(String request) {
        return request.equals("EXAMPLE");
    }

    @Override
    public void processRequest(String Request) {
        System.out.println("Wysłano mi napis EXAMPLE");
    }
}
