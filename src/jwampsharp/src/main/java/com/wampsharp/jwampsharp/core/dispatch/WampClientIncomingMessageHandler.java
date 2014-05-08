package com.wampsharp.jwampsharp.core.dispatch;

import com.wampsharp.jwampsharp.core.message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampClientIncomingMessageHandler<TMessage> {
    void handleMessage(WampMessage<TMessage> message);
}
