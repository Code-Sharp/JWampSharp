package com.wampsharp.jwampsharp.core.binding;

import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.wampsharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampBinding<TMessage> {
    String getName();

    WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message);

    WampFormatter<TMessage> getFormatter();
}