package co.codesharp.jwampsharp.client.pubSub;

import co.codesharp.jwampsharp.pubSub.WampTopicSubscriber;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampTopicProxy extends AutoCloseable {
    String getTopicUri();

    CompletionStage<Long> publish(Object options, Object[] arguments, Object argumentKeywords);
    CompletionStage<Long> publish(Object options);
    CompletionStage<Long> publish(Object options, Object[] arguments);

    CompletionStage<AutoCloseable> subscribe(WampTopicSubscriber subscriber, Object options);
    CompletionStage<AutoCloseable> subscribe(WampRawTopicSubscriber subscriber, Object options);
}