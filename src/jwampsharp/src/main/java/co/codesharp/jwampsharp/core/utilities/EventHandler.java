package co.codesharp.jwampsharp.core.utilities;

public interface EventHandler<TEventArgs extends EventArgs>
{
    void addListener(EventListener<TEventArgs> listener);
    boolean removeListener(EventListener<TEventArgs> listener);
}
