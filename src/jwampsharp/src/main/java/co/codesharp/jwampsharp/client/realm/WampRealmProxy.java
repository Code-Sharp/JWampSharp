package co.codesharp.jwampsharp.client.realm;

import co.codesharp.jwampsharp.api.WampRealmServiceProvider;
import co.codesharp.jwampsharp.client.pubSub.WampTopicContainerProxy;
import co.codesharp.jwampsharp.client.rpc.WampRpcOperationCatalogProxy;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.utilities.EventHandler;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampRealmProxy {

    String getName();

    WampTopicContainerProxy getTopicContainer();

    WampRpcOperationCatalogProxy getRpcCatalog();

    WampServerProxy getProxy();

    WampRealmServiceProvider getServices();

    EventHandler<WampSessionEventArgs> getConnectionEstablished();
    EventHandler<WampSessionCloseEventArgs> getConnectionBroken();
}