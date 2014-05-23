package co.codesharp.jwampsharp.core.utilities;

/**
 * Created by Elad on 15/04/2014.
 */
public interface EventListener<TEventArgs extends EventArgs> {
    void eventOccured(Object sender, TEventArgs e);
}