package co.codesharp.jwampsharp.core.listener;

import co.codesharp.jwampsharp.core.utilities.EventHandler;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampNewConnectionEventArgs;

/**
 * Created by Elad on 17/04/2014.
 */
public interface WampConnectionListener {
    EventHandler<WampNewConnectionEventArgs> getNewConnection();
}