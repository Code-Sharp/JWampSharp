package co.codesharp.jwampsharp.core.listener;

public interface ControlledWampConnection<TMessage> extends WampConnection<TMessage>
{
    void connect();
}
