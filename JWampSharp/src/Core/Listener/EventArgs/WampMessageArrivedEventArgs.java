package Core.Listener.EventArgs;

import Core.Message.WampMessage;
import Core.Utilities.EventArgs;

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
