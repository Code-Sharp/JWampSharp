package co.codesharp.jwampsharp.core.utilities;

import java.util.ArrayList;

/**
 * Created by Elad on 15/04/2014.
 */
public class EventHandlerImpl<TEventArgs extends EventArgs> implements EventHandler<TEventArgs> {

    private final ArrayList<EventListener<TEventArgs>> listeners = new ArrayList<EventListener<TEventArgs>>();

    @Override
    public void addListener(EventListener<TEventArgs> listener) {
        listeners.add(listener);
    }

    @Override
    public boolean removeListener(EventListener<TEventArgs> listener) {
        return listeners.remove(listener);
    }

    protected void raiseEvent(Object sender, TEventArgs e) {
        for (EventListener<TEventArgs> listener : listeners) {
            listener.eventOccured(sender, e);
        }
    }
}