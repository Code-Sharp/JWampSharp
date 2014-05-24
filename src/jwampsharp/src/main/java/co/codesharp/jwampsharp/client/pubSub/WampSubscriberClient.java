package co.codesharp.jwampsharp.client.pubSub;

import co.codesharp.jwampsharp.client.WampPendingRequest;
import co.codesharp.jwampsharp.core.contracts.error.WampSubscriberError;
import co.codesharp.jwampsharp.core.contracts.pubSub.WampSubscriber;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.core.WampIdMapper;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Elad on 18/04/2014.
 */
public class WampSubscriberClient<TMessage> implements WampSubscriber<TMessage>, WampSubscriberError<TMessage>, WampTopicSubscriptionProxy {

    private final WampIdMapper<SubscribeRequest> pendingSubscriptions = new WampIdMapper<SubscribeRequest>();
    private final WampServerProxy proxy;
    private final WampFormatter<TMessage> formatter;
    private final ConcurrentMap<Long, SubscriptionData> subscriptionIdToSubscription =
            new ConcurrentHashMap<Long, SubscriptionData>();
    private final WampIdMapper<UnsubscribeRequest> pendingUnsubscriptions = new WampIdMapper<UnsubscribeRequest>();

    public WampSubscriberClient(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.proxy = proxy;
        this.formatter = formatter;
    }

    @Override
    public CompletionStage<AutoCloseable> subscribe(WampRawTopicSubscriber subscriber, Object options, String topicUri) {
        SubscribeRequest request = new SubscribeRequest(formatter, subscriber, options, topicUri);
        long requestId = this.pendingSubscriptions.add(request);
        request.setRequestId(requestId);

        proxy.subscribe(requestId, options, topicUri);

        return request.getCompletionStage();
    }


    private CompletionStage unsubscribe(long subscriptionId) {
        UnsubscribeRequest request = new UnsubscribeRequest(formatter, subscriptionId);
        long requestId = pendingUnsubscriptions.add(request);
        request.setRequestId(requestId);
        proxy.unsubscribe(requestId, subscriptionId);

        return request.getCompletionStage();
    }

    @Override
    public void subscribed(long requestId, long subscriptionId) {
        SubscribeRequest request = pendingSubscriptions.remove(requestId);

        if (request != null) {
            SubscriptionData subscription = new SubscriptionData(subscriptionId,
                    request.getSubscriber(),
                    request.getOptions(),
                    request.getTopicUri());

            subscriptionIdToSubscription.put(subscriptionId,
                    subscription);

            request.complete(new UnsubscribeAutoCloseable(this, subscriptionId));
        }
    }

    @Override
    public void unsubscribed(long requestId, long subscriptionId) {
        UnsubscribeRequest request = pendingUnsubscriptions.remove(requestId);

        if (request != null) {
            request.complete();
            SubscriptionData subscription = subscriptionIdToSubscription.remove(subscriptionId);
        }
    }

    @Override
    public void event(long subscriptionId, long publicationId, TMessage details) {
        WampRawTopicSubscriber subscriber = getSubscriber(subscriptionId);

        if (subscriber != null) {
            subscriber.event(formatter, publicationId, details);
        }
    }

    @Override
    public void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments) {
        WampRawTopicSubscriber subscriber = getSubscriber(subscriptionId);

        if (subscriber != null) {
            subscriber.event(formatter, publicationId, details, arguments);
        }
    }

    @Override
    public void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        WampRawTopicSubscriber subscriber = getSubscriber(subscriptionId);

        if (subscriber != null) {
            subscriber.event(formatter, publicationId, details, arguments, argumentsKeywords);
        }
    }

    private WampRawTopicSubscriber getSubscriber(long subscriptionId) {
        SubscriptionData subscription = subscriptionIdToSubscription.getOrDefault(subscriptionId, null);

        if (subscription != null) {
            return subscription.getSubscriber();
        }

        return null;
    }

    @Override
    public void subscribeError(long requestId, TMessage details, String error) {
        SubscribeRequest request = pendingSubscriptions.remove(requestId);

        if (request != null) {
            request.error(details, error);
        }
    }

    @Override
    public void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments) {
        SubscribeRequest request = pendingSubscriptions.remove(requestId);

        if (request != null) {
            request.error(details, error, arguments);
        }
    }

    @Override
    public void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        SubscribeRequest request = pendingSubscriptions.remove(requestId);

        if (request != null) {
            request.error(details, error, arguments, argumentsKeywords);
        }
    }

    @Override
    public void unsubscribeError(long requestId, TMessage details, String error) {
        UnsubscribeRequest request = pendingUnsubscriptions.remove(requestId);

        if (request != null) {
            request.error(details, error);
        }
    }

    @Override
    public void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments) {
        UnsubscribeRequest request = pendingUnsubscriptions.remove(requestId);

        if (request != null) {
            request.error(details, error, arguments);
        }
    }

    @Override
    public void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        UnsubscribeRequest request = pendingUnsubscriptions.remove(requestId);

        if (request != null) {
            request.error(details, error, arguments, argumentsKeywords);
        }
    }

    private class BaseSubscription {
        protected final WampRawTopicSubscriber subscriber;
        protected final Object options;
        protected final String topicUri;

        public BaseSubscription(Object options, String topicUri, WampRawTopicSubscriber subscriber) {
            this.options = options;
            this.topicUri = topicUri;
            this.subscriber = subscriber;
        }

        public WampRawTopicSubscriber getSubscriber() {
            return subscriber;
        }

        public Object getOptions() {
            return options;
        }

        public String getTopicUri() {
            return topicUri;
        }
    }

    private class SubscribeRequest extends BaseSubscription {

        private WampPendingRequest<TMessage, AutoCloseable> pendingRequest;

        private SubscribeRequest(WampFormatter<TMessage> formatter, WampRawTopicSubscriber subscriber, Object options, String topicUri) {
            super(options, topicUri, subscriber);
            pendingRequest = new WampPendingRequest<TMessage, AutoCloseable>(formatter);
        }

        public CompletionStage<AutoCloseable> getCompletionStage() {
            return pendingRequest.getCompletionStage();
        }

        public void setRequestId(long requestId) {
            pendingRequest.setRequestId(requestId);
        }

        public void complete(AutoCloseable autoCloseable) {
            pendingRequest.complete(autoCloseable);
        }

        public long getRequestId() {
            return pendingRequest.getRequestId();
        }

        public void error(TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
            pendingRequest.error(details, error, arguments, argumentsKeywords);
        }

        public void error(TMessage details, String error) {
            pendingRequest.error(details, error);
        }

        public void error(TMessage details, String error, TMessage[] arguments) {
            pendingRequest.error(details, error, arguments);
        }
    }

    private class SubscriptionData extends BaseSubscription {
        private final long subscriptionId;

        public SubscriptionData(long subscriptionId, WampRawTopicSubscriber subscriber, Object options, String topicUri) {
            super(options, topicUri, subscriber);
            this.subscriptionId = subscriptionId;
        }

        public long getSubscriptionId() {
            return subscriptionId;
        }
    }

    private class UnsubscribeAutoCloseable implements AutoCloseable {
        private final WampSubscriberClient<TMessage> parent;
        private final long subscriptionId;

        public UnsubscribeAutoCloseable(WampSubscriberClient<TMessage> parent, long subscriptionId) {
            this.parent = parent;
            this.subscriptionId = subscriptionId;
        }

        @Override
        public void close() throws Exception {
            this.parent.unsubscribe(this.subscriptionId);
        }
    }

    private class UnsubscribeRequest extends WampPendingRequest<TMessage, Boolean> {
        private final long subscriptionId;

        private UnsubscribeRequest(WampFormatter<TMessage> formatter, long subscriptionId) {
            super(formatter);
            this.subscriptionId = subscriptionId;
        }

        public void complete() {
            super.complete(true);
        }
    }
}