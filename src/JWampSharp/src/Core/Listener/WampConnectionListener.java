package Core.Listener;

import Core.Listener.EventArgs.WampNewConnectionEventArgs;
import Core.Utilities.EventHandler;

/**
 * Created by Elad on 17/04/2014.
 */
public interface WampConnectionListener {
    EventHandler<WampNewConnectionEventArgs> getNewConnection();
}