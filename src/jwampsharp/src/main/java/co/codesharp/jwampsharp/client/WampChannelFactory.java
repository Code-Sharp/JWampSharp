package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.client.realm.DefaultRealmProxy;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;

/**
 * Created by Elad on 16/04/2014.
 */
public abstract class WampChannelFactory<TMessage> {

    private final WampBinding<TMessage> binding;
    private WampServerProxyBuilder<TMessage> builder;

    public WampChannelFactory(WampBinding<TMessage> binding, Class<TMessage> messageType, Class<TMessage[]> messageTypeArray) {
        this.binding = binding;
        this.builder = new DefaultWampServerProxyBuilder<TMessage>(binding, messageType, messageTypeArray);
    }

    public WampChannel<TMessage> createChannel(String realm, ControlledWampConnection<TMessage> connection) {

        WampRealmProxyFactoryImpl wampRealmProxyFactory = new WampRealmProxyFactoryImpl(this, realm, connection);

        DefaultWampClient<TMessage> client =
                new DefaultWampClient<TMessage>(wampRealmProxyFactory);

        return new WampChannel<TMessage>(connection, client);
    }

    private class WampRealmProxyFactoryImpl implements WampRealmProxyFactory<TMessage> {
        private final WampChannelFactory<TMessage> parent;
        private final String realmName;
        private final ControlledWampConnection<TMessage> connection;

        public WampRealmProxyFactoryImpl(WampChannelFactory<TMessage> parent, String realmName, ControlledWampConnection<TMessage> connection) {
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