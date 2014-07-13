package co.codesharp.jwampsharp.testhelpers;

import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampConnectionErrorEventArgs;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.utilities.EventArgs;
import co.codesharp.jwampsharp.core.utilities.EventHandler;
import co.codesharp.jwampsharp.core.utilities.EventHandlerImpl;
import rx.Observable;
import rx.Observer;
import rx.util.functions.Action1;

/**
 * Created by Elad on 7/13/2014.
 */
public class DirectedConnection<TMessage> implements ControlledWampConnection<TMessage> {

    private final EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>> messageArrived =
            new EventHandlerImplExtended<WampMessageArrivedEventArgs<TMessage>>();
    private final EventHandlerImplExtended<EventArgs> connectionClosed =
            new EventHandlerImplExtended<EventArgs>();
    private final EventHandlerImplExtended<EventArgs> connectionOpen =
            new EventHandlerImplExtended<EventArgs>();
    private final EventHandler<WampConnectionErrorEventArgs> connectionError =
            new EventHandlerImplExtended<WampConnectionErrorEventArgs>();

    private final Observable<WampMessage<TMessage>> incomingMessages;
    private Observer<WampMessage<TMessage>> outgoingMessages;

    public DirectedConnection(Observable<WampMessage<TMessage>> incomingMessages,
                              Observer<WampMessage<TMessage>> outgoingMessages) {
        this.incomingMessages = incomingMessages;

        this.incomingMessages.subscribe(new Action1<WampMessage<TMessage>>() {
            @Override
            public void call(WampMessage<TMessage> message) {
                messageArrived.raiseEvent
                        (this,
                         new WampMessageArrivedEventArgs<TMessage>(message));
            }
        });

        this.outgoingMessages = outgoingMessages;
    }

    @Override
    public void connect() {
        this.connectionOpen.raiseEvent(this, EventArgs.getEmpty());
    }

    @Override
    public void send(WampMessage<TMessage> message) {
        this.outgoingMessages.onNext(message);
    }

    @Override
    public EventHandler<EventArgs> getConnectionOpen() {
        return connectionOpen;
    }

    @Override
    public EventHandler<WampMessageArrivedEventArgs<TMessage>> getMessageArrived() {
        return messageArrived;
    }

    @Override
    public EventHandler<EventArgs> getConnectionClosed() {
        return connectionClosed;
    }

    @Override
    public EventHandler<WampConnectionErrorEventArgs> getConnectionError() {
        return connectionError;
    }

    private class EventHandlerImplExtended<TEventArgs extends EventArgs> extends EventHandlerImpl<TEventArgs> {
        public void raiseEvent(Object sender, TEventArgs e) {
            super.raiseEvent(sender, e);
        }
    }
}