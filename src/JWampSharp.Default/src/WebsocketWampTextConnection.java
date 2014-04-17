import Core.Listener.ControlledWampConnection;
import Core.Listener.EventArgs.WampConnectionErrorEventArgs;
import Core.Listener.EventArgs.WampMessageArrivedEventArgs;
import Core.Binding.WampTextBinding;
import Core.Message.WampMessage;
import Core.Utilities.EventArgs;
import Core.Utilities.EventHandlerImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by Elad on 15/04/2014.
 */
public class WebsocketWampTextConnection<TMessage> implements ControlledWampConnection<TMessage> {

    private final Socket socket;
    private final WampTextBinding<TMessage> binding;

    private EventHandlerImplExtended<EventArgs> connectionOpen = new EventHandlerImplExtended<EventArgs>();

    private EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>> messageArrived =
            new EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>>();

    private EventHandlerImplExtended<WampConnectionErrorEventArgs> connectionError =
            new EventHandlerImplExtended<WampConnectionErrorEventArgs>();

    private EventHandlerImplExtended<EventArgs> connectionClosed = new EventHandlerImplExtended<EventArgs>();

    public WebsocketWampTextConnection(URI serverURI, WampTextBinding<TMessage> binding) {
        this.binding = binding;
        this.socket = new Socket(serverURI, this);
    }

    @Override
    public void send(WampMessage<TMessage> message) {
        String raw = this.binding.format(message);
        socket.send(raw);
    }

    @Override
    public void connect() {
        socket.connect();
    }

    private void onOpen(ServerHandshake serverHandshake) {
        this.connectionOpen.raiseEvent(this, EventArgs.getEmpty());
    }

    private void onMessage(String message) {
        WampMessage<TMessage> wampMessage = this.binding.parse(message);
        this.messageArrived.raiseEvent(this, new WampMessageArrivedEventArgs<TMessage>(wampMessage));
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

    private class EventHandlerImplExtended<TEventArgs extends EventArgs> extends EventHandlerImpl<TEventArgs> {
        public void raiseEvent(Object sender, TEventArgs e) {
            super.raiseEvent(sender, e);
        }
    }

    private class Socket extends WebSocketClient {
        private final WebsocketWampTextConnection<TMessage> parent;

        public Socket(URI serverURI, WebsocketWampTextConnection<TMessage> parent) {
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
        public void onClose(int code, String reason, boolean remote) {
            this.parent.onClose(code, reason, remote);
        }

        @Override
        public void onError(Exception e) {
            this.parent.onError(e);
        }
    }
}