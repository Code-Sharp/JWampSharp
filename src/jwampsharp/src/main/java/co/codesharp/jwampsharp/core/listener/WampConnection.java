package co.codesharp.jwampsharp.core.listener;

import co.codesharp.jwampsharp.core.listener.eventArgs.WampConnectionErrorEventArgs;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import co.codesharp.jwampsharp.core.utilities.EventHandler;
import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.utilities.EventArgs;

/**
 * Created by Elad on 13/04/2014.
 */
public interface WampConnection<TMessage> {
    void send(WampMessage<TMessage> message);

    EventHandler<EventArgs> getConnectionOpen();
    EventHandler<WampMessageArrivedEventArgs<TMessage>> getMessageArrived();
    EventHandler<EventArgs> getConnectionClosed();
    EventHandler<WampConnectionErrorEventArgs> getConnectionError();
}