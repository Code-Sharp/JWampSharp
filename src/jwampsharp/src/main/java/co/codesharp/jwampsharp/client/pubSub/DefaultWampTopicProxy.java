package co.codesharp.jwampsharp.client.pubSub;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.pubSub.WampTopicSubscriber;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Created by Elad on 18/04/2014.
 */
public class DefaultWampTopicProxy implements WampTopicProxy {
    private final String topicUri;
    private final WampTopicSubscriptionProxy subscriber;
    private final WampTopicPublicationProxy publisher;
    private final AutoCloseable conatinerDisposable;

    private final Object lock = new Object();

    private ConcurrentMap<Object, WampTopicProxySection> optionsToSection =
            new ConcurrentHashMap<Object, WampTopicProxySection>();

    public DefaultWampTopicProxy(String topicUri,
                                 WampTopicSubscriptionProxy subscriber,
                                 WampTopicPublicationProxy publisher,
                                 AutoCloseable conatinerDisposable) {
        this.topicUri = topicUri;
        this.subscriber = subscriber;
        this.publisher = publisher;
        this.conatinerDisposable = conatinerDisposable;
    }

    @Override
    public String getTopicUri() {
        return topicUri;
    }

    @Override
    public CompletionStage<Long> publish(Object options) {
        return this.publisher.publish(this.getTopicUri(), options);
    }

    @Override
    public CompletionStage<Long> publish(Object options, Object[] arguments) {
        return this.publisher.publish(this.getTopicUri(), options, arguments);
    }

    @Override
    public CompletionStage<Long> publish(Object options, Object[] arguments, Object argumentKeywords) {
        return this.publisher.publish(this.getTopicUri(), options, arguments, argumentKeywords);
    }

    @Override
    public CompletionStage<AutoCloseable> subscribe(WampTopicSubscriber subscriber, Object options) {
        RawSubscriberAdapter adapter = new RawSubscriberAdapter(subscriber);
        return subscribe(adapter, options);
    }

    @Override
    public CompletionStage<AutoCloseable> subscribe(WampRawTopicSubscriber subscriber, Object options) {
        synchronized (lock) {
            Function<Object, WampTopicProxySection> topicProxySectionGenerator =
                    new Function<Object, WampTopicProxySection>() {
                        @Override
                        public WampTopicProxySection apply(Object givenOptions) {
                            return createTopicProxySection(givenOptions);
                        }
                    };

            WampTopicProxySection section =
                    this.optionsToSection.computeIfAbsent(options,
                            topicProxySectionGenerator);

            return section.subscribe(subscriber);
        }
    }

    private WampTopicProxySection createTopicProxySection(Object options) {
        return new WampTopicProxySection(topicUri, subscriber, options);
        // TODO: subscribe to "SectionEmpty" event and remove this from hashmap when empty.
    }

    @Override
    public void close() throws Exception {
        synchronized (lock) {
            // TODO: clean hashmap.
        }
    }

    private class RawSubscriberAdapter implements WampRawTopicSubscriber {
        private final WampTopicSubscriber subscriber;

        public RawSubscriberAdapter(WampTopicSubscriber subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details) {
            this.subscriber.event(publicationId, details);
        }

        @Override
        public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments) {
            this.subscriber.event(publicationId, details, arguments);
        }

        @Override
        public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
            this.subscriber.event(publicationId, details, arguments, argumentsKeywords);
        }
    }
}