package co.codesharp.jwampsharp.core.binding.contracts;

import co.codesharp.jwampsharp.core.binding.WampTextBinding;
import co.codesharp.jwampsharp.core.binding.WampTextMessageParser;
import co.codesharp.jwampsharp.core.binding.WampTransportBindingBase;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public abstract class JsonBinding<TMessage> extends WampTransportBindingBase<TMessage, String> implements WampTextBinding<TMessage> {
    protected JsonBinding(WampFormatter<TMessage> formatter, WampTextMessageParser<TMessage> parser) {
        super(formatter, parser, "wamp.2.json");
    }
}