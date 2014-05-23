package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.core.contracts.WampClient;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.listener.WampConnection;

/**
 * Created by Elad on 16/04/2014.
 */
public interface WampServerProxyBuilder<TMessage> {
    WampServerProxy Create(WampClient<TMessage> client, WampConnection<TMessage> connection);
}