package Client.Realm;

import Client.PubSub.DefaultWampTopicContainerProxy;
import Client.PubSub.WampTopicContainerProxy;
import Client.Rpc.DefaultWampRpcOperationCatalogProxy;
import Client.Rpc.WampRpcOperationCatalogProxy;
import Core.Contracts.WampServerProxy;
import Core.Binding.WampBinding;
import Core.Serialization.WampFormatter;

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
}