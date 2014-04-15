package Client;

import Core.Contracts.WampClient;
import Core.Contracts.WampServerProxy;
import Core.Dispatch.DefaultWampClientIncomingMessageHandler;
import Core.Dispatch.WampClientIncomingMessageHandler;
import Core.Listener.EventArgs.WampMessageArrivedEventArgs;
import Core.Listener.WampBinding;
import Core.Listener.WampConnection;
import Core.Proxy.DefaultWampServerProxy;
import Core.Proxy.WampProtocol;
import Core.Utilities.EventListener;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampServerProxyBuilder<TMessage> implements WampServerProxyBuilder<TMessage> {
    private WampBinding<TMessage> binding;
    private Class<TMessage> underlyingMessageType;
    private Class<TMessage[]> underlyingMessageTypeArray;

    public DefaultWampServerProxyBuilder(WampBinding<TMessage> binding, Class<TMessage> underlyingMessageType, Class<TMessage[]> underlyingMessageTypeArray) {
        this.binding = binding;
        this.underlyingMessageType = underlyingMessageType;
        this.underlyingMessageTypeArray = underlyingMessageTypeArray;
    }

    @Override
    public WampServerProxy Create(WampClient<TMessage> client, WampConnection<TMessage> connection) {
        return new WampServerProxyImpl(client, connection, this.binding, this.underlyingMessageType,
                this.underlyingMessageTypeArray);
    }

    private class WampServerProxyImpl extends DefaultWampServerProxy<TMessage> {
        private final WampClient<TMessage> client;
        private final WampConnection<TMessage> connection;
        private final WampClientIncomingMessageHandler handler;
        private final EventListener<WampMessageArrivedEventArgs<TMessage>> listener;

        public WampServerProxyImpl(WampClient<TMessage> client, WampConnection<TMessage> connection,
                                   WampBinding<TMessage> binding,
                                   Class<TMessage> underlyingMessageType,
                                   Class<TMessage[]> underlyingMessageTypeArray) {
            super(connection, new WampProtocol<TMessage>(underlyingMessageType, binding.getFormatter()));

            this.client = client;
            this.connection = connection;

            this.handler =
                    new DefaultWampClientIncomingMessageHandler
                    (underlyingMessageType, underlyingMessageTypeArray, binding.getFormatter(), client);

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