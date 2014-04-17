package Client.PubSub;

import java.io.Closeable;
import java.util.concurrent.Future;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampTopicProxy {
    String getTopicUri();

    Future<Long> publish(Object options, Object[] arguments, Object argumentKeywords);
    Future<Long> publish(Object options);
    Future<Long> publish(Object options, Object[] arguments);

    Future<Closeable> Subscribe(WampTopicSubscriber subscriber, Object options);
}
