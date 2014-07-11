package co.codesharp.jwampsharp.core.binding;

import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 7/10/2014.
 */
public class WampTransportBindingBase<TMessage, TRaw> extends WampBindingBase<TMessage> implements WampTransportBinding<TMessage, TRaw> {
    private final WampMessageParser<TMessage, TRaw> parser;

    protected WampTransportBindingBase(WampFormatter<TMessage> formatter, WampMessageParser<TMessage, TRaw> parser, String protocolName) {
        super(protocolName, formatter);
        this.parser = parser;
    }

    @Override
    public WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message) {
        return getFormattedMessage(message);
    }

    private RawMessage<TMessage, TRaw> getFormattedMessage(WampMessage<TMessage> message) {
        RawMessage<TMessage, TRaw> result;

        if (message instanceof RawMessage) {
            result = (RawMessage<TMessage, TRaw>)message;
        } else {
            result = new RawMessage<TMessage, TRaw>(message);
            result.setRaw(parser.format(message));
        }

        return result;
    }

    public WampMessage<TMessage> parse(TRaw message) {
        return parser.parse(message);
    }

    public TRaw format(WampMessage<TMessage> message) {
        RawMessage<TMessage, TRaw> rawMessage = getFormattedMessage(message);
        return rawMessage.getRaw();
    }

    private class RawMessage<TMessage, TRaw> extends WampMessage<TMessage> {
        private TRaw raw;

        public RawMessage(WampMessage<TMessage> other) {
            super(other);
        }

        public TRaw getRaw() {
            return raw;
        }

        public void setRaw(TRaw raw) {
            this.raw = raw;
        }
    }
}