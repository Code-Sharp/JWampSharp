package Client.PubSub;

import Core.Contracts.PubSub.WampPublisher;
import Core.Contracts.PubSub.WampSubscriber;
import Core.Contracts.WampServerProxy;
import Core.Serialization.WampFormatter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class DefaultWampTopicContainerProxy<TMessage> implements WampTopicContainerProxy,
        WampSubscriber<TMessage>, WampPublisher<TMessage> {

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