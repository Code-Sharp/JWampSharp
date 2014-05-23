package co.codesharp.jwampsharp.defaultBinding;

import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampConnectionErrorEventArgs;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.utilities.EventArgs;
import co.codesharp.jwampsharp.core.utilities.EventHandlerImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Created by Elad on 17/04/2014.
 */
public abstract class WebsocketWampConnection<TMessage> implements ControlledWampConnection<TMessage> {
    private final Socket socket;
    private EventHandlerImplExtended<EventArgs> connectionOpen = new EventHandlerImplExtended<EventArgs>();
    private EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>> messageArrived =
            new EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>>();
    private EventHandlerImplExtended<WampConnectionErrorEventArgs> connectionError =
            new EventHandlerImplExtended<WampConnectionErrorEventArgs>();
    private EventHandlerImplExtended<EventArgs> connectionClosed = new EventHandlerImplExtended<EventArgs>();

    public WebsocketWampConnection(URI serverURI) {
        this.socket = new Socket(serverURI, this);
    }

    @Override
    public void connect() {
        socket.connect();
    }

    private void onOpen(ServerHandshake serverHandshake) {
        this.connectionOpen.raiseEvent(this, EventArgs.getEmpty());
    }

    protected void onMessage(String message) {
    }

    protected void onMessage(ByteBuffer bytes) {
    }

    private void onError(Exception ex) {
        this.connectionError.raiseEvent(this, new WampConnectionErrorEventArgs(ex));
    }

    private void onClose(int code, String reason, boolean remote) {
        this.connectionClosed.raiseEvent(this, EventArgs.getEmpty());
    }

    @Override
    public EventHandlerImpl<EventArgs> getConnectionOpen() {
        return connectionOpen;
    }

    @Override
    public EventHandlerImpl<WampMessageArrivedEventArgs<TMessage>> getMessageArrived() {
        return messageArrived;
    }

    @Override
    public EventHandlerImpl<WampConnectionErrorEventArgs> getConnectionError() {
        return connectionError;
    }

    @Override
    public EventHandlerImpl<EventArgs> getConnectionClosed() {
        return connectionClosed;
    }

    protected void send(String text) {
        socket.send(text);
    }

    protected void send(byte[] bytes) {
        socket.send(bytes);
    }

    protected void raiseMessageArrived(WampMessage<TMessage> parsed) {
        messageArrived.raiseEvent(this, new WampMessageArrivedEventArgs<TMessage>(parsed));
    }

    private class EventHandlerImplExtended<TEventArgs extends EventArgs> extends EventHandlerImpl<TEventArgs> {
        public void raiseEvent(Object sender, TEventArgs e) {
            super.raiseEvent(sender, e);
        }
    }

    protected class Socket extends WebSocketClient {
        private final WebsocketWampConnection<TMessage> parent;

        public Socket(URI serverURI, WebsocketWampConnection<TMessage> parent) {
            super(serverURI);
            this.parent = parent;
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {
            this.parent.onOpen(serverHandshake);
        }

        @Override
        public void onMessage(String s) {
            this.parent.onMessage(s);
        }

        @Override
        public void onMessage(ByteBuffer bytes) {
            parent.onMessage(bytes);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            this.parent.onClose(code, reason, remote);
        }

        @Override
        public void onError(Exception e) {
            this.parent.onError(e);
        }
    }
}
