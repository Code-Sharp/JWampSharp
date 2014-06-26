package co.codesharp.jwampsharp.client;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.codesharp.jwampsharp.core.message.WampMessageType;

public class TestErrorForwarder {

    private final static long REQUEST_ID = 33;
    private final static String ERROR = "error";
    private TMessage details = new TMessage();

    @Mock
    DefaultWampClient client;
    private ErrorForwarder errorForwarder;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        errorForwarder = new ErrorForwarder(client);
    }

    @Test
    public void testRegisterError() {
        errorForwarder.error(WampMessageType.REGISTER, REQUEST_ID, details,
                ERROR);
        verify(client).registerError(REQUEST_ID, details, ERROR);
    }

    @Test
    public void testUnregisterError() {
        errorForwarder.error(WampMessageType.UNREGISTER, REQUEST_ID, details,
                ERROR);
        verify(client).unregisterError(REQUEST_ID, details, ERROR);
    }

    @Test
    public void testCallError() {
        errorForwarder.error(WampMessageType.CALL, REQUEST_ID, details, ERROR);
        verify(client).callError(REQUEST_ID, details, ERROR);
    }

    @Test
    public void testSubscribeError() {
        errorForwarder.error(WampMessageType.SUBSCRIBE, REQUEST_ID, details,
                ERROR);
        verify(client).subscribeError(REQUEST_ID, details, ERROR);
    }

    @Test
    public void testUnsubscribeError() {
        errorForwarder.error(WampMessageType.UNSUBSCRIBE, REQUEST_ID, details,
                ERROR);
        verify(client).unsubscribeError(REQUEST_ID, details, ERROR);
    }

    @Test
    public void testPublishError() {
        errorForwarder.error(WampMessageType.PUBLISH, REQUEST_ID, details,
                ERROR);
        verify(client).publishError(REQUEST_ID, details, ERROR);
    }

    private class TMessage {
    }
}