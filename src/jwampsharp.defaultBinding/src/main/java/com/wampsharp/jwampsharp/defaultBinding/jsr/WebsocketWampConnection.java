package com.wampsharp.jwampsharp.defaultBinding.jsr;

import com.wampsharp.jwampsharp.core.binding.WampBinding;
import com.wampsharp.jwampsharp.core.listener.ControlledWampConnection;
import com.wampsharp.jwampsharp.core.listener.eventArgs.WampConnectionErrorEventArgs;
import com.wampsharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.wampsharp.jwampsharp.core.utilities.EventArgs;
import com.wampsharp.jwampsharp.core.utilities.EventHandlerImpl;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class WebsocketWampConnection<TMessage> implements ControlledWampConnection<TMessage> {
    private final WebSocketEndPoint endPoint;
    private final URI serverPath;
    private final WampBinding<TMessage> binding;
    private EventHandlerImplExtended<EventArgs> connectionOpen = new EventHandlerImplExtended<EventArgs>();
    private EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>> messageArrived =
            new EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>>();
    private EventHandlerImplExtended<WampConnectionErrorEventArgs> connectionError =
            new EventHandlerImplExtended<WampConnectionErrorEventArgs>();
    private EventHandlerImplExtended<EventArgs> connectionClosed = new EventHandlerImplExtended<EventArgs>();

    public WebsocketWampConnection(URI serverPath, WampBinding<TMessage> binding) {
        this.serverPath = serverPath;
        this.binding = binding;
        this.endPoint = new WebSocketEndPoint(this);
    }

    @Override
    public void connect() {
        String bindingName = this.binding.getName();

        ClientEndpointConfig endpointConfig = ClientEndpointConfig.Builder.create()
                .preferredSubprotocols(Arrays.asList(bindingName))
                .build();

        WebSocketContainer webSocketContainer =
                ContainerProvider.getWebSocketContainer();

        try {
            webSocketContainer.connectToServer(this.endPoint,
                    endpointConfig,
                    serverPath);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(),
                    ex);
        }
    }


    private void onOpen(Session session, EndpointConfig config) {
        this.connectionOpen.raiseEvent(this, EventArgs.getEmpty());
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

    protected void onMessage(String message) {
    }

    protected void onMessage(ByteBuffer message) {
    }

    protected void send(String text) {
        endPoint.sendText(text);
    }

    protected void send(byte[] bytes) {
        endPoint.sendBinary(bytes);
    }

    private void onError(Session session, Throwable thr) {
        this.connectionError.raiseEvent(this, new WampConnectionErrorEventArgs(thr));
    }

    private void onClose(Session session, CloseReason closeReason) {
        this.connectionClosed.raiseEvent(this, EventArgs.getEmpty());
    }

    protected void raiseMessageArrived(WampMessage<TMessage> parsed) {
        messageArrived.raiseEvent(this, new WampMessageArrivedEventArgs<TMessage>(parsed));
    }

    private class EventHandlerImplExtended<TEventArgs extends EventArgs> extends EventHandlerImpl<TEventArgs> {
        public void raiseEvent(Object sender, TEventArgs e) {
            super.raiseEvent(sender, e);
        }
    }

    private class WebSocketEndPoint extends Endpoint implements MessageHandler.Whole<String> {
        private final WebsocketWampConnection<TMessage> parent;
        private MessageHandler.Whole<String> textMessageHandler;
        private MessageHandler.Whole<ByteBuffer> binaryMessageHandler;
        private Session session;

        public WebSocketEndPoint(final WebsocketWampConnection<TMessage> parent) {
            this.parent = parent;

            this.textMessageHandler = new MessageHandler.Whole<String>() {

                @Override
                public void onMessage(String message) {
                    parent.onMessage(message);
                }
            };

            this.binaryMessageHandler = new MessageHandler.Whole<ByteBuffer>() {

                @Override
                public void onMessage(ByteBuffer message) {
                    parent.onMessage(message);
                }
            };
        }

        @Override
        public void onOpen(Session session, EndpointConfig config) {
            this.session = session;
            this.parent.onOpen(session, config);
            session.addMessageHandler(this);
        }

        public void onMessage(String message) {
            this.parent.onMessage(message);
        }

        public void onMessage(ByteBuffer message) {
            this.parent.onMessage(message);
        }


        @Override
        public void onError(Session session, Throwable thr) {
            this.parent.onError(session, thr);
        }

        @Override
        public void onClose(Session session, CloseReason closeReason) {
            this.parent.onClose(session, closeReason);
        }

        public void sendText(String text) {
            try {
                this.session.getBasicRemote().sendText(text);
            } catch (IOException ex) {
                // Log about it.
            }
        }

        public void sendBinary(byte[] bytes) {
            try {
                this.session.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
            } catch (IOException ex) {
                // Log about it.
            }
        }
    }
}