package com.wampsharp.jwampsharp.client;

import com.wampsharp.jwampsharp.core.contracts.WampClient;
import com.wampsharp.jwampsharp.core.contracts.WampServerProxy;
import com.wampsharp.jwampsharp.core.dispatch.DefaultWampClientIncomingMessageHandler;
import com.wampsharp.jwampsharp.core.dispatch.WampClientIncomingMessageHandler;
import com.wampsharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import com.wampsharp.jwampsharp.core.binding.WampBinding;
import com.wampsharp.jwampsharp.core.listener.WampConnection;
import com.wampsharp.jwampsharp.core.proxy.DefaultWampServerProxy;
import com.wampsharp.jwampsharp.core.proxy.WampProtocol;
import com.wampsharp.jwampsharp.core.utilities.EventListener;

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
        private final WampClientIncomingMessageHandler<TMessage> handler;
        private final EventListener<WampMessageArrivedEventArgs<TMessage>> listener;

        public WampServerProxyImpl(WampClient<TMessage> client, WampConnection<TMessage> connection,
                                   WampBinding<TMessage> binding,
                                   Class<TMessage> underlyingMessageType,
                                   Class<TMessage[]> underlyingMessageTypeArray) {
            super(connection, new WampProtocol<TMessage>(underlyingMessageType, binding.getFormatter()));

            this.client = client;
            this.connection = connection;

            this.handler =
                    new DefaultWampClientIncomingMessageHandler<TMessage>
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