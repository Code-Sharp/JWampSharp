package co.codesharp.jwampsharp.core.contracts.error;

/**
 * Created by Elad on 16/05/2014.
 */
public interface WampCalleeError<TMessage> {
    void registerError(long requestId, TMessage details, String error);

    void registerError(long requestId, TMessage details, String error, TMessage[] arguments);

    void registerError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);

    void unregisterError(long requestId, TMessage details, String error);

    void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments);

    void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
