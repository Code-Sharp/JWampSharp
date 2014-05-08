package com.wampsharp.jwampsharp.core.contracts.rpc;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampCaller<TMessage> {
    void result(long requestId, TMessage details);

    void result(long requestId, TMessage details, TMessage[] arguments);

    void result(long requestId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords);
}
