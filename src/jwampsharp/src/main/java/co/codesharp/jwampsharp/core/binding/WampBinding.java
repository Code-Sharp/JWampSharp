package co.codesharp.jwampsharp.core.binding;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.core.message.WampMessage;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampBinding<TMessage> {
    String getName();

    WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message);

    WampFormatter<TMessage> getFormatter();
}

