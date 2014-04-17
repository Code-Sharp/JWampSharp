package Core.Listener;

import Core.Listener.EventArgs.WampConnectionErrorEventArgs;
import Core.Listener.EventArgs.WampMessageArrivedEventArgs;
import Core.Message.WampMessage;
import Core.Utilities.EventArgs;
import Core.Utilities.EventHandler;

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