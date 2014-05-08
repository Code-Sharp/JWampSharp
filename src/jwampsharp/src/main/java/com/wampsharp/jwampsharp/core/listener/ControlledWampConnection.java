package com.wampsharp.jwampsharp.core.listener;

public interface ControlledWampConnection<TMessage> extends WampConnection<TMessage>
{
    void connect();
}
