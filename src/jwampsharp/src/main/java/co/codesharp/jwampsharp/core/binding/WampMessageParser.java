package co.codesharp.jwampsharp.core.binding;

import co.codesharp.jwampsharp.core.message.WampMessage;

public interface WampMessageParser<TMessage, TRaw>
{
    WampMessage<TMessage> parse(TRaw raw);

    TRaw format(WampMessage<TMessage> message);
}
