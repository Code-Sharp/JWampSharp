package com.wampsharp.jwampsharp.core.dispatch;

import com.wampsharp.jwampsharp.core.contracts.WampClientProxy;
import com.wampsharp.jwampsharp.core.message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServerIncomingMessageHandler<TMessage> {
    void handleMessage(WampClientProxy client, WampMessage<TMessage> message);
}

