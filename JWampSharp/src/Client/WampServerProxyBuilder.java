package Client;

import Core.Contracts.WampClient;
import Core.Contracts.WampServerProxy;
import Core.Listener.WampConnection;

/**
 * Created by Elad on 16/04/2014.
 */
public interface WampServerProxyBuilder<TMessage> {
    WampServerProxy Create(WampClient<TMessage> client, WampConnection<TMessage> connection);
}