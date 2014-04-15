package Core.Listener;

import Core.Message.WampMessage;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampTextBinding<TMessage> extends WampBinding<TMessage> {
    WampMessage<TMessage> parse(String message);
}
