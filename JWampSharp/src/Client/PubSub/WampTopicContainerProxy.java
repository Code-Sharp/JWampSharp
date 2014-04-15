package Client.PubSub;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampTopicContainerProxy {
    WampTopicProxy getTopic(String topicUri);
}
