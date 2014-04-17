package Core.Binding;

import Core.Message.WampMessage;

public interface WampBinaryMessageParser<TMessage>
{
    WampMessage<TMessage> parse(byte[] bytes);

    byte[] format(WampMessage<TMessage> message);
}
