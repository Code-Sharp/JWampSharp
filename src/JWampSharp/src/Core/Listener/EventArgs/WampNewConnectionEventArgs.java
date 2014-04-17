package Core.Listener.EventArgs;

import Core.Listener.WampConnection;
import Core.Utilities.EventArgs;

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