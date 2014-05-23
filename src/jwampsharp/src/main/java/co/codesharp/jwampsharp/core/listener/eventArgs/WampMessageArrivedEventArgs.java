package co.codesharp.jwampsharp.core.listener.eventArgs;

import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.utilities.EventArgs;

/**
 * Created by Elad on 15/04/2014.
 */
public class WampMessageArrivedEventArgs<TMessage> extends EventArgs {
    private final WampMessage<TMessage> message;

    public WampMessageArrivedEventArgs(WampMessage<TMessage> message) {
        this.message = message;
    }

    public WampMessage<TMessage> getMessage() {
        return message;
    }
}
