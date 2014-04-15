package Core.Listener;

import Core.Message.WampMessage;
import Core.Serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampBinding<TMessage> {
    String getName();

    WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message);

    WampFormatter<TMessage> getFormatter();
}