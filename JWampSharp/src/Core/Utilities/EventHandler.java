package Core.Utilities;

public interface EventHandler<TEventArgs extends EventArgs>
{
    void addListener(EventListener<TEventArgs> listener);
    boolean removeListener(EventListener<TEventArgs> listener);
}
