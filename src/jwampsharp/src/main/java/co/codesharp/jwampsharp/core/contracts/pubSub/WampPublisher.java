package co.codesharp.jwampsharp.core.contracts.pubSub;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampPublisher<TMessage> {
    void published(long requestId, long publicationId);
}
