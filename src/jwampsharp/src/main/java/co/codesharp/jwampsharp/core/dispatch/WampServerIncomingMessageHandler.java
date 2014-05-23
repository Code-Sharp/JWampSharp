package co.codesharp.jwampsharp.core.dispatch;

import co.codesharp.jwampsharp.core.contracts.WampClientProxy;
import co.codesharp.jwampsharp.core.message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServerIncomingMessageHandler<TMessage> {
    void handleMessage(WampClientProxy client, WampMessage<TMessage> message);
}

