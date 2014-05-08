package com.wampsharp.jwampsharp.core.contracts.error;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampError<TMessage> {
    void error(int requestType, long requestId, TMessage details, String error);

    void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments);

    void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
