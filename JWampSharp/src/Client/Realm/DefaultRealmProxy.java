package Client.Realm;

import Client.PubSub.WampTopicContainerProxy;
import Client.Realm.WampRealmProxy;
import Client.Rpc.DefaultWampRpcOperationCatalogProxy;
import Client.Rpc.WampRpcOperationCatalogProxy;
import Core.Contracts.WampServerProxy;
import Core.Listener.WampBinding;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultRealmProxy<TMessage> implements WampRealmProxy {
    private final String name;
    private final WampServerProxy proxy;
    private final WampBinding<TMessage> binding;
    private final WampRpcOperationCatalogProxy rpcCatalog;

    public DefaultRealmProxy(String name, WampServerProxy proxy, WampBinding<TMessage> binding) {
        this.name = name;
        this.proxy = proxy;
        this.binding = binding;
        rpcCatalog = new DefaultWampRpcOperationCatalogProxy<TMessage>(proxy, binding.getFormatter());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public WampTopicContainerProxy getTopicContainer() {
        return null;
    }

    @Override
    public WampRpcOperationCatalogProxy getRpcCatalog() {
        return this.rpcCatalog;
    }

    @Override
    public WampServerProxy getProxy() {
        return this.proxy;
    }
}