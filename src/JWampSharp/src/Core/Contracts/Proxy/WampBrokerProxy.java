package Core.Contracts.Proxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampBrokerProxy {
    void publish(long requestId, Object options, String topicUri);

    void publish(long requestId, Object options, String topicUri, Object[] arguments);

    void publish(long requestId, Object options, String topicUri, Object[] arguments, Object argumentKeywords);

    void subscribe(long requestId, Object options, String topicUri);

    void unsubscribe(long requestId, long subscriptionId);
}
