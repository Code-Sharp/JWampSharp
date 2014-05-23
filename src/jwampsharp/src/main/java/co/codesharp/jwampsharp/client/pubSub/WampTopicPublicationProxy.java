package co.codesharp.jwampsharp.client.pubSub;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 18/04/2014.
 */
interface WampTopicPublicationProxy {
    CompletionStage<Long> publish(String topicUri, Object options);
    CompletionStage<Long> publish(String topicUri, Object options, Object[] arguments);
    CompletionStage<Long> publish(String topicUri, Object options, Object[] arguments, Object argumentKeywords);
}