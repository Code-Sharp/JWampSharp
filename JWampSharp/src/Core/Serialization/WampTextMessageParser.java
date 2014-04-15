package Core.Serialization;

import Core.Message.WampMessage;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampTextMessageParser<TMessage> {
    WampMessage<TMessage> Parse(String text);

    String Format(WampMessage<TMessage> message);
}