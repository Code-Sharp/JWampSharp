package co.codesharp.jwampsharp.client.realm;

import co.codesharp.jwampsharp.api.WampRealmServiceProvider;
import co.codesharp.jwampsharp.client.pubSub.DefaultWampTopicContainerProxy;
import co.codesharp.jwampsharp.client.pubSub.WampTopicContainerProxy;
import co.codesharp.jwampsharp.client.rpc.DefaultWampRpcOperationCatalogProxy;
import co.codesharp.jwampsharp.client.rpc.WampRpcOperationCatalogProxy;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.core.utilities.EventHandler;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultRealmProxy<TMessage> implements WampRealmProxy {
    private final String name;
    private final WampServerProxy proxy;
    private final WampBinding<TMessage> binding;
    private final WampRpcOperationCatalogProxy rpcCatalog;
    private final WampTopicContainerProxy topicContainer;

    public DefaultRealmProxy(String name, WampServerProxy proxy, WampBinding<TMessage> binding) {
        this.name = name;
        this.proxy = proxy;
        this.binding = binding;
        WampFormatter<TMessage> formatter = binding.getFormatter();
        rpcCatalog = new DefaultWampRpcOperationCatalogProxy<TMessage>(proxy, formatter);
        topicContainer = new DefaultWampTopicContainerProxy(proxy, formatter);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public WampTopicContainerProxy getTopicContainer() {
        return this.topicContainer;
    }

    @Override
    public WampRpcOperationCatalogProxy getRpcCatalog() {
        return this.rpcCatalog;
    }

    @Override
    public WampServerProxy getProxy() {
        return this.proxy;
    }

    @Override
    public WampRealmServiceProvider getServices() {
        // TODO
        return null;
    }

    @Override
    public EventHandler<WampSessionEventArgs> getConnectionEstablished() {
        // TODO
        return null;
    }

    @Override
    public EventHandler<WampSessionCloseEventArgs> getConnectionBroken() {
        // TODO
        return null;
    }
}