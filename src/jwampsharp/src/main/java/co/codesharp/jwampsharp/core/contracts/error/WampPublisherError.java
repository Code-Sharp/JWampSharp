package co.codesharp.jwampsharp.core.contracts.error;

/**
 * Created by Elad on 16/05/2014.
 */
public interface WampPublisherError<TMessage> {
    void publishError(long requestId, TMessage details, String error);

    void publishError(long requestId, TMessage details, String error, TMessage[] arguments);

    void publishError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
