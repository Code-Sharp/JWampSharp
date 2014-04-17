package Core.Binding.Contracts;

import Core.Binding.*;
import Core.Message.WampMessage;
import Core.Serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public class MsgpackBinding<TMessage> extends WampBindingBase<TMessage> implements WampBinaryBinding<TMessage> {
    private final WampBinaryMessageParser<TMessage> parser;

    protected MsgpackBinding(WampFormatter<TMessage> formatter, WampBinaryMessageParser<TMessage> parser) {
        super("wamp.2.msgpack", formatter);
        this.parser = parser;
    }

    @Override
    public WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message) {
        return getRawBinaryMessage(message);
    }

    private BinaryMessage<TMessage> getRawBinaryMessage(WampMessage<TMessage> message) {
        BinaryMessage<TMessage> result;

        if (message instanceof BinaryMessage) {
            result = (BinaryMessage<TMessage>)message;
        } else {
            result = new BinaryMessage<TMessage>(message);
            result.setBytes(parser.format(message));
        }

        return result;
    }

    @Override
    public WampMessage<TMessage> parse(byte[] message) {
        return parser.parse(message);
    }

    @Override
    public byte[] format(WampMessage<TMessage> message) {
        BinaryMessage<TMessage> rawMessage = getRawBinaryMessage(message);
        return rawMessage.getBytes();
    }

    private class BinaryMessage<TMessage> extends WampMessage<TMessage> {
        private byte[] bytes;

        public BinaryMessage(WampMessage<TMessage> other) {
            super(other);
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }
    }
}