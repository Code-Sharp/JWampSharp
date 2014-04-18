package PubSub;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampTopicSubscriber {
    void event(long publicationId, Object details, Object[] arguments);
    void event(long publicationId, Object details);
    void event(long publicationId, Object details, Object[] arguments, Object argumentsKeywords);
}
