package co.codesharp.jwampsharp.core.listener.eventArgs;

import co.codesharp.jwampsharp.core.listener.WampConnection;
import co.codesharp.jwampsharp.core.utilities.EventArgs;

/**
 * Created by Elad on 17/04/2014.
 */
public class WampNewConnectionEventArgs<TMessage> extends EventArgs {
    private final WampConnection<TMessage> connection;

    public WampNewConnectionEventArgs(WampConnection<TMessage> connection) {
        this.connection = connection;
    }

    public WampConnection<TMessage> getConnection() {
        return connection;
    }
}