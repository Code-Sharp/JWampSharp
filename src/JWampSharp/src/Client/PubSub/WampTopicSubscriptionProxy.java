package Client.PubSub;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 18/04/2014.
 */
interface WampTopicSubscriptionProxy {
    CompletionStage<AutoCloseable> subscribe(WampRawTopicSubscriber subscriber, Object options, String topicUri);
}
