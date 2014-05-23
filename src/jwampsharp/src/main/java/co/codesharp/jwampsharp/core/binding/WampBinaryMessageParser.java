package co.codesharp.jwampsharp.core.binding;

import co.codesharp.jwampsharp.core.message.WampMessage;

public interface WampBinaryMessageParser<TMessage>
{
    WampMessage<TMessage> parse(byte[] bytes);

    byte[] format(WampMessage<TMessage> message);
}
