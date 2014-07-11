package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import co.codesharp.jwampsharp.core.contracts.WampClient;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.dispatch.DefaultWampClientIncomingMessageHandler;
import co.codesharp.jwampsharp.core.dispatch.WampClientIncomingMessageHandler;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.listener.WampConnection;
import co.codesharp.jwampsharp.core.proxy.DefaultWampServerProxy;
import co.codesharp.jwampsharp.core.proxy.WampProtocol;
import co.codesharp.jwampsharp.core.utilities.EventListener;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampServerProxyBuilder<TMessage> implements WampServerProxyBuilder<TMessage> {
    private WampBinding<TMessage> binding;

    public DefaultWampServerProxyBuilder(WampBinding<TMessage> binding) {
        this.binding = binding;
    }

    @Override
    public WampServerProxy Create(WampClient<TMessage> client, WampConnection<TMessage> connection) {
        return new WampServerProxyImpl(client, connection, this.binding
        );
    }

    private class WampServerProxyImpl extends DefaultWampServerProxy<TMessage> {
        private final WampClient<TMessage> client;
        private final WampConnection<TMessage> connection;
        private final WampClientIncomingMessageHandler<TMessage> handler;
        private final EventListener<WampMessageArrivedEventArgs<TMessage>> listener;

        public WampServerProxyImpl(WampClient<TMessage> client, WampConnection<TMessage> connection,
                                   WampBinding<TMessage> binding) {
            super(connection, new WampProtocol<TMessage>(binding.getUnderlyingMessageType(), binding.getFormatter()));

            this.client = client;
            this.connection = connection;

            this.handler =
                    new DefaultWampClientIncomingMessageHandler<TMessage>
                    (binding.getUnderlyingMessageType(), binding.getUnderlyingMessageTypeArray(), binding.getFormatter(), client);

            listener = new EventListener<WampMessageArrivedEventArgs<TMessage>>() {
                @Override
                public void eventOccured(Object sender, WampMessageArrivedEventArgs<TMessage> e) {
                    handler.handleMessage(e.getMessage());
                }
            };

            this.connection.getMessageArrived().addListener(listener);
        }
    }
}