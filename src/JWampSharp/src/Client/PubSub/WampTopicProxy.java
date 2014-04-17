package Client.PubSub;

import java.io.Closeable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampTopicProxy {
    String getTopicUri();

    CompletionStage<Long> publish(Object options, Object[] arguments, Object argumentKeywords);
    CompletionStage<Long> publish(Object options);
    CompletionStage<Long> publish(Object options, Object[] arguments);

    CompletionStage<Closeable> Subscribe(WampTopicSubscriber subscriber, Object options);
}
