package Core.Listener;

public interface ControlledWampConnection<TMessage> extends WampConnection<TMessage>
{
    void connect();
}
