package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.client.pubSub.WampTopicProxy;
import rx.Observer;
import rx.Subscription;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/12/2014.
 */
class WampClientSubject extends WampSubject {
    private final WampTopicProxy topic;
    private static final Map<String, Object> EmptyOptions = new HashMap<String, Object>();

    public WampClientSubject(WampTopicProxy topic) {
        super(null);
        this.topic = topic;
    }

    public Subscription subscribe(Observer<? super WampSerializedEvent> observer) {
        return new AutoCloseableSubscription(topic.subscribe(new RawSubscriber(observer), EmptyOptions));
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(WampEvent wampEvent) {
        Map<String, Object> options = wampEvent.getOptions();
        Object[] arguments = wampEvent.getArguments();
        Map<String, Object> argumentsKeywords = wampEvent.getArgumentsKeywords();

        if (options == null) {
            options = EmptyOptions;
        }

        if (argumentsKeywords != null) {
            this.topic.publish(options,
                    arguments,
                    wampEvent.getArgumentsKeywords());
        }
        else if (arguments != null) {
            this.topic.publish(options, arguments);
        }
        else{
            this.topic.publish(options);
        }
    }

    private class AutoCloseableSubscription implements Subscription {

        private final AutoCloseable autoCloseable;

        public AutoCloseableSubscription(CompletionStage<AutoCloseable> subscribe) {
            try {
                autoCloseable = subscribe.toCompletableFuture().get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void unsubscribe() {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}