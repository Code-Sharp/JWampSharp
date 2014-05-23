package co.codesharp.jwampsharp.core.contracts.error;

/**
 * Created by Elad on 16/05/2014.
 */
public interface WampCallerError<TMessage> {
    void callError(long requestId, TMessage details, String error);

    void callError(long requestId, TMessage details, String error, TMessage[] arguments);

    void callError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
