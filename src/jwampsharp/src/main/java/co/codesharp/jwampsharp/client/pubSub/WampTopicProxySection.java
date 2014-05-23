package co.codesharp.jwampsharp.client.pubSub;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * Created by Elad on 18/04/2014.
 */
class WampTopicProxySection implements WampRawTopicSubscriber, AutoCloseable {
    private AtomicBoolean subscribed = new AtomicBoolean(false);
    private final WampTopicSubscriptionProxy subscriber;
    private PublishSubject<Publication> subject = PublishSubject.create();
    private final String topicUri;
    private final Object options;
    private final Object lock = new Object();
    private AutoCloseable externalSubscription;

    WampTopicProxySection(String topicUri, WampTopicSubscriptionProxy subscriber, Object options) {
        this.subscriber = subscriber;
        this.topicUri = topicUri;
        this.options = options;
    }

    public CompletionStage<AutoCloseable> subscribe(WampRawTopicSubscriber subscriber)
    {
        Object options = this.options;
        CompletionStage<AutoCloseable> remoteSubscription = subscribeExternal(options);

        final AutoCloseable disposable =
                subscribeInternal(subscriber);

        if (remoteSubscription == null)
        {
            return CompletableFuture.completedFuture(disposable);
        }
        else
        {
            Function<AutoCloseable, AutoCloseable> continuation = new Function<AutoCloseable, AutoCloseable>() {
                @Override
                public AutoCloseable apply(AutoCloseable closeable) {
                    externalSubscription = closeable;
                    return disposable;
                }
            };

            return remoteSubscription.
                    thenApplyAsync(continuation);
        }
    }

    private AutoCloseable subscribeInternal(final WampRawTopicSubscriber subscriber) {
        Subscription subjectSubscription = subject.subscribe(new TopicSubscriberObserver(subscriber));

        Subscription onRemove = new Subscription() {
            @Override
            public void unsubscribe() {
                onSubscriberRemoved(subscriber);
            }
        };

        CompositeSubscription subscription =
                new CompositeSubscription(subjectSubscription, onRemove);

        SubscriptionAutoCloseable result = new SubscriptionAutoCloseable(subscription);
        return result;
    }


    private CompletionStage<AutoCloseable> subscribeExternal(Object options)
    {
        if (subscribed.compareAndSet(false, true))
        {
            return subscriber.subscribe(this, options, this.topicUri);
        }

        return null;
    }

    private void onSubscriberRemoved(WampRawTopicSubscriber subscriber) {
    }

    @Override
    public <TMessage> void event(final WampFormatter<TMessage> formatter, final long publicationId, final TMessage details) {
        Publication publication = new Publication() {
            @Override
            public void publish(WampRawTopicSubscriber subscriber) {
                subscriber.event(formatter, publicationId, details);
            }
        };

        publishInternal(publication);
    }

    @Override
    public <TMessage> void event(final WampFormatter<TMessage> formatter, final long publicationId, final TMessage details, final TMessage[] arguments) {
        Publication publication = new Publication() {
            @Override
            public void publish(WampRawTopicSubscriber subscriber) {
                subscriber.event(formatter, publicationId, details, arguments);
            }
        };

        publishInternal(publication);
    }

    @Override
    public <TMessage> void event(final WampFormatter<TMessage> formatter, final long publicationId, final TMessage details, final TMessage[] arguments, final TMessage argumentsKeywords) {
        Publication publication = new Publication() {
            @Override
            public void publish(WampRawTopicSubscriber subscriber) {
                subscriber.event(formatter, publicationId, details, arguments, argumentsKeywords);
            }
        };

        publishInternal(publication);
    }

    private void publishInternal(Publication publication) {
        subject.onNext(publication);
    }

    @Override
    public void close() throws Exception {
        synchronized (lock) {
            if (this.subject != null) {
                this.subject = null;
                // Call "dispose"?
            }

            if (externalSubscription != null)
            {
                externalSubscription.close();
                externalSubscription = null;
            }
        }
    }

    private interface Publication {
        void publish(WampRawTopicSubscriber subscriber);
    }

    private class TopicSubscriberObserver implements Observer<Publication> {
        private final WampRawTopicSubscriber subscriber;

        public TopicSubscriberObserver(WampRawTopicSubscriber subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable throwable) {
        }

        @Override
        public void onNext(Publication publication) {
            publication.publish(subscriber);
        }
    }

    private class SubscriptionAutoCloseable implements AutoCloseable {
        private final CompositeSubscription subscription;

        public SubscriptionAutoCloseable(CompositeSubscription subscription) {
            this.subscription = subscription;
        }

        @Override
        public void close() throws Exception {
            this.subscription.unsubscribe();
        }
    }
}