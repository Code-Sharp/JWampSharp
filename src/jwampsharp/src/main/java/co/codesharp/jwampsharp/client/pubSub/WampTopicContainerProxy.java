package co.codesharp.jwampsharp.client.pubSub;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampTopicContainerProxy {
    WampTopicProxy getTopic(String topicUri);
}

