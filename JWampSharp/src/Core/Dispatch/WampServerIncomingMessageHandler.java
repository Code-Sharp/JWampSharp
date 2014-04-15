package Core.Dispatch;

import Core.Contracts.WampClientProxy;
import Core.Message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServerIncomingMessageHandler<TMessage> {
    void handleMessage(WampClientProxy client, WampMessage<TMessage> message);
}

