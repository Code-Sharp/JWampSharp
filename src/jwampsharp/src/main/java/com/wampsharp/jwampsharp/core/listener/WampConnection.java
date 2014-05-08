package com.wampsharp.jwampsharp.core.listener;

import com.wampsharp.jwampsharp.core.listener.eventArgs.WampConnectionErrorEventArgs;
import com.wampsharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.wampsharp.jwampsharp.core.utilities.EventArgs;
import com.wampsharp.jwampsharp.core.utilities.EventHandler;

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