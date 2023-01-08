package checkers;

import checkers.Server.RequestHandlers.*;
import org.junit.Assert;
import org.junit.Test;

public class JUintRequestHandlers {

    @Test
    public void testExitRequestHandler() {

        IRequestHandler handler = new ExitRequestHandler();

        Assert.assertTrue(handler.canProcessRequest(null, "EXIT"));
        Assert.assertTrue(handler.canProcessRequest(null, "EXIT_SUFFIX"));

        Assert.assertFalse(handler.canProcessRequest(null, "ABC"));
    }

    @Test
    public void testJoinRequestHandler() {

        IRequestHandler handler = new JoinQueueRequestHandler();

        Assert.assertTrue(handler.canProcessRequest(null, "JOIN_QUEUE"));
        Assert.assertTrue(handler.canProcessRequest(null, "JOIN_QUEUE_SUFFIX"));

        Assert.assertFalse(handler.canProcessRequest(null, "ABC"));
    }

    @Test
    public void testLeaveRequestHandler() {

        IRequestHandler handler = new LeaveQueueRequestHandler();

        Assert.assertTrue(handler.canProcessRequest(null, "LEAVE_QUEUE"));
        Assert.assertTrue(handler.canProcessRequest(null, "LEAVE_QUEUE_SUFFIX"));

        Assert.assertFalse(handler.canProcessRequest(null, "ABC"));
    }

    @Test
    public void testMoveRequestHandler() {

        IRequestHandler handler = new MoveRequestHandler();

        Assert.assertTrue(handler.canProcessRequest(null, "MOVE_0_0"));
        Assert.assertTrue(handler.canProcessRequest(null, "MOVE_SUFFIX"));

        Assert.assertFalse(handler.canProcessRequest(null, "ABC"));
    }

    @Test
    public void testSurrenderRequestHandler() {

        IRequestHandler handler = new SurrenderRequestHandler();

        Assert.assertTrue(handler.canProcessRequest(null, "SURRENDER"));
        Assert.assertTrue(handler.canProcessRequest(null, "SURRENDER_SUFFIX"));

        Assert.assertFalse(handler.canProcessRequest(null, "ABC"));
    }
}
