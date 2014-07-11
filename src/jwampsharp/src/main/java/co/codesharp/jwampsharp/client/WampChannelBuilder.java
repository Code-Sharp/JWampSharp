package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.client.realm.DefaultRealmProxy;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;

/**
 * Created by Elad on 16/04/2014.
 */
public class WampChannelBuilder<TMessage> {

    private final WampBinding<TMessage> binding;
    private WampServerProxyBuilder<TMessage> builder;

    public WampChannelBuilder(WampBinding<TMessage> binding) {
        this.binding = binding;
        this.builder = new DefaultWampServerProxyBuilder<TMessage>(binding);
    }

    public WampChannelImpl<TMessage> createChannel(String realm, ControlledWampConnection<TMessage> connection) {

        WampRealmProxyFactoryImpl wampRealmProxyFactory = new WampRealmProxyFactoryImpl(this, realm, connection);

        DefaultWampClient<TMessage> client =
                new DefaultWampClient<TMessage>(wampRealmProxyFactory);

        return new WampChannelImpl<TMessage>(connection, client);
    }

    private class WampRealmProxyFactoryImpl implements WampRealmProxyFactory<TMessage> {
        private final WampChannelBuilder<TMessage> parent;
        private final String realmName;
        private final ControlledWampConnection<TMessage> connection;

        public WampRealmProxyFactoryImpl(WampChannelBuilder<TMessage> parent, String realmName, ControlledWampConnection<TMessage> connection) {
            this.parent = parent;
            this.realmName = realmName;
            this.connection = connection;
        }

        @Override
        public WampRealmProxy build(DefaultWampClient<TMessage> client) {
            WampServerProxy serverProxy = parent.builder.Create(client, connection);

            DefaultRealmProxy result = new DefaultRealmProxy<TMessage>(this.realmName,
                    serverProxy,
                    parent.binding);

            return result;
        }
    }
}