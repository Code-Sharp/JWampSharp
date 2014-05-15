package com.wampsharp.jwampsharp.core.contracts.error;

/**
 * Created by Elad on 16/05/2014.
 */
public interface WampSubscriberError<TMessage> {
    void subscribeError(long requestId, TMessage details, String error);

    void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments);

    void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);

    void unsubscribeError(long requestId, TMessage details, String error);

    void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments);

    void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
