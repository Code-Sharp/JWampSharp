package Core.Contracts.PubSub;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampSubscriber<TMessage> {
    void subscribed(long requestId, long subscriptionId);

    void unsubscribed(long requestId, long subscriptionId);

    void event(long subscriptionId, long publicationId, TMessage details);

    void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments);

    void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords);
}
