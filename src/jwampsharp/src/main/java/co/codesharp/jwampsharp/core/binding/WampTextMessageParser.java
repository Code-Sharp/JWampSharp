package co.codesharp.jwampsharp.core.binding;

import co.codesharp.jwampsharp.core.message.WampMessage;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampTextMessageParser<TMessage> {
    WampMessage<TMessage> parse(String text);

    String format(WampMessage<TMessage> message);
}