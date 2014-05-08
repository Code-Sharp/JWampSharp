package com.wampsharp.jwampsharp.core.binding;

import com.wampsharp.jwampsharp.core.message.WampMessage;

public interface WampBinaryMessageParser<TMessage>
{
    WampMessage<TMessage> parse(byte[] bytes);

    byte[] format(WampMessage<TMessage> message);
}
