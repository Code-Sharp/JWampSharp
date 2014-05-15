package com.wampsharp.jwampsharp.client.pubSub;

import com.wampsharp.jwampsharp.core.contracts.error.WampPublisherError;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampPublisher;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 18/04/2014.
 */
public class WampPublisherClient<TMessage> implements WampPublisher<TMessage>, WampPublisherError<TMessage>, WampTopicPublicationProxy {
    @Override
    public void published(long requestId, long publicationId) {

    }

    @Override
    public CompletionStage<Long> publish(String topicUri, Object options) {
        return null;
    }

    @Override
    public CompletionStage<Long> publish(String topicUri, Object options, Object[] arguments) {
        return null;
    }

    @Override
    public CompletionStage<Long> publish(String topicUri, Object options, Object[] arguments, Object argumentKeywords) {
        return null;
    }

    @Override
    public void publishError(long requestId, TMessage details, String error) {

    }

    @Override
    public void publishError(long requestId, TMessage details, String error, TMessage[] arguments) {

    }

    @Override
    public void publishError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {

    }
}