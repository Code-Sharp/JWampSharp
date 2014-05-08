package com.wampsharp.jwampsharp.core.binding;

import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.wampsharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public abstract class WampBindingBase<TMessage> implements WampBinding<TMessage> {
    private final String name;
    private final WampFormatter<TMessage> formatter;

    protected WampBindingBase(String name, WampFormatter<TMessage> formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public abstract WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message);

    @Override
    public WampFormatter<TMessage> getFormatter() {
        return formatter;
    }
}