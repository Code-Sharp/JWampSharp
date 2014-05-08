package com.wampsharp.jwampsharp.core.listener.eventArgs;

import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.wampsharp.jwampsharp.core.utilities.EventArgs;

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
