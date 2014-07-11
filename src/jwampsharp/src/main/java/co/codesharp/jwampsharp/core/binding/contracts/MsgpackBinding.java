package co.codesharp.jwampsharp.core.binding.contracts;

import co.codesharp.jwampsharp.core.binding.WampBinaryBinding;
import co.codesharp.jwampsharp.core.binding.WampBinaryMessageParser;
import co.codesharp.jwampsharp.core.binding.WampTransportBindingBase;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public class MsgpackBinding<TMessage> extends WampTransportBindingBase<TMessage, byte[]> implements WampBinaryBinding<TMessage> {
    protected MsgpackBinding(WampFormatter<TMessage> formatter, WampBinaryMessageParser<TMessage> parser) {
        super(formatter, parser, "wamp.2.msgpack");
    }
}