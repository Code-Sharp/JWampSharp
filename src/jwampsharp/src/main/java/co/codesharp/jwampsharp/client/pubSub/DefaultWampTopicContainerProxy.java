package co.codesharp.jwampsharp.client.pubSub;

import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.contracts.error.WampSubscriberError;
import co.codesharp.jwampsharp.core.contracts.pubSub.WampSubscriber;
import co.codesharp.jwampsharp.core.contracts.error.WampPublisherError;
import co.codesharp.jwampsharp.core.contracts.pubSub.WampPublisher;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class DefaultWampTopicContainerProxy<TMessage> implements WampTopicContainerProxy,
        WampSubscriber<TMessage>, WampPublisher<TMessage>,
        WampSubscriberError<TMessage>, WampPublisherError<TMessage> {
    private final ConcurrentMap<String, WampTopicProxy> topicUriToTopicProxy =
            new ConcurrentHashMap<String, WampTopicProxy>();

    private final WampSubscriberClient<TMessage> subscriber;
    private final WampPublisherClient<TMessage> publisher;

    public DefaultWampTopicContainerProxy(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.subscriber = new WampSubscriberClient<TMessage>(proxy, formatter);
        this.publisher = new WampPublisherClient<TMessage>();
    }

    @Override
    public WampTopicProxy getTopic(String topicUri) {
        return topicUriToTopicProxy.computeIfAbsent(topicUri,
                new Function<String, WampTopicProxy>() {
                    @Override
                    public WampTopicProxy apply(String uri) {
                        return new DefaultWampTopicProxy(uri,
                                subscriber,
                                publisher,
                                new RemoveFromContainerCloseable(uri));
                    }
                }
        );
    }

    @Override
    public void subscribed(long requestId, long subscriptionId) {
        subscriber.subscribed(requestId, subscriptionId);
    }

    @Override
    public void unsubscribed(long requestId, long subscriptionId) {
        subscriber.unsubscribed(requestId, subscriptionId);
    }

    @Override
    public void event(long subscriptionId, long publicationId, TMessage details) {
        subscriber.event(subscriptionId, publicationId, details);
    }

    @Override
    public void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments) {
        subscriber.event(subscriptionId, publicationId, details, arguments);
    }

    @Override
    public void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        subscriber.event(subscriptionId, publicationId, details, arguments, argumentsKeywords);
    }

    @Override
    public void published(long requestId, long publicationId) {
        publisher.published(requestId, publicationId);
    }

    @Override
    public void subscribeError(long requestId, TMessage details, String error) {
        subscriber.subscribeError(requestId, details, error);
    }

    @Override
    public void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments) {
        subscriber.subscribeError(requestId, details, error, arguments);
    }

    @Override
    public void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        subscriber.subscribeError(requestId, details, error, arguments, argumentsKeywords);
    }

    @Override
    public void unsubscribeError(long requestId, TMessage details, String error) {
        subscriber.unsubscribeError(requestId, details, error);
    }

    @Override
    public void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments) {
        subscriber.unsubscribeError(requestId, details, error, arguments);
    }

    @Override
    public void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        subscriber.unsubscribeError(requestId, details, error, arguments, argumentsKeywords);
    }

    @Override
    public void publishError(long requestId, TMessage details, String error) {
        publisher.publishError(requestId, details, error);
    }

    @Override
    public void publishError(long requestId, TMessage details, String error, TMessage[] arguments) {
        publisher.publishError(requestId, details, error, arguments);
    }

    @Override
    public void publishError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        publisher.publishError(requestId, details, error, arguments, argumentsKeywords);
    }

    private class RemoveFromContainerCloseable implements AutoCloseable {
        private String topicUri;

        private RemoveFromContainerCloseable(String topicUri) {
            this.topicUri = topicUri;
        }

        @Override
        public void close() throws Exception {
            topicUriToTopicProxy.remove(topicUri);
        }
    }
}