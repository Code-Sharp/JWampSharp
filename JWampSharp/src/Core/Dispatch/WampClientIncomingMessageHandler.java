package Core.Dispatch;

import Core.Message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampClientIncomingMessageHandler<TMessage> {
    void handleMessage(WampMessage<TMessage> message);
}
