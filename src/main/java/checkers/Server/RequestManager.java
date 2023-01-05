package checkers.Server;

import checkers.Server.RequestHandlers.ExampleRequestHandler;
import checkers.Server.RequestHandlers.IRequestHandler;

public class RequestManager {

    private static final IRequestHandler[] requestHandlers = new IRequestHandler[]{
            new ExampleRequestHandler()
    };

    public static void processRequest(String request) {

        for (IRequestHandler handler : requestHandlers) {

            if (handler.canProcessRequest(request)) {

                handler.processRequest(request);
                return;
            }
        }
    }
}
