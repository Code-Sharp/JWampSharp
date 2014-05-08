package com.wampsharp.jwampsharp.core.contracts.pubSub;

import com.wampsharp.jwampsharp.core.contracts.WampClientProxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampBroker<TMessage> {
    void publish(WampClientProxy client, long requestId, TMessage options, String topicUri);

    void publish(WampClientProxy client, long requestId, TMessage options, String topicUri, TMessage[] arguments);

    void publish(WampClientProxy client, long requestId, TMessage options, String topicUri, TMessage[] arguments, TMessage argumentKeywords);

    void subscribe(WampClientProxy client, long requestId, TMessage options, String topicUri);

    void unsubscribe(WampClientProxy client, long requestId, long subscriptionId);
}
