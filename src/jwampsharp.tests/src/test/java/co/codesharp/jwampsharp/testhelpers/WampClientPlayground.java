package co.codesharp.jwampsharp.testhelpers;

import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.api.client.WampChannelFactory;
import co.codesharp.jwampsharp.api.client.WampChannelFactoryImpl;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.contracts.WampServer;
import co.codesharp.jwampsharp.core.dispatch.DefaultWampServerIncomingMessageHandler;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;
import co.codesharp.jwampsharp.core.listener.eventArgs.WampMessageArrivedEventArgs;
import co.codesharp.jwampsharp.core.proxy.DefaultWampClientProxy;
import co.codesharp.jwampsharp.core.proxy.WampProtocol;
import co.codesharp.jwampsharp.core.utilities.EventListener;

/**
 * Created by Elad on 7/13/2014.
 */
public class WampClientPlayground {
    private WampChannelFactory channelFactory;

    public WampClientPlayground(WampChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public WampClientPlayground(){
        this(new WampChannelFactoryImpl());
    }

    public <TMessage> WampChannel getChannel(WampServer<TMessage> serverMock, String realm, WampBinding<TMessage> binding) {
        MockedConnection<TMessage> connection = new MockedConnection<TMessage>();

        ControlledWampConnection<TMessage> serverConnection = connection.getSideBToSideA();

        final DefaultWampClientProxy clientProxy = new DefaultWampClientProxy<TMessage>(serverConnection,
                new WampProtocol<TMessage>(binding));

        final DefaultWampServerIncomingMessageHandler<TMessage> handler =
                new DefaultWampServerIncomingMessageHandler<TMessage>
                        (binding, serverMock);

        serverConnection.getMessageArrived().addListener(new EventListener<WampMessageArrivedEventArgs<TMessage>>() {
            @Override
            public void eventOccured(Object sender, WampMessageArrivedEventArgs<TMessage> e) {
                handler.handleMessage(clientProxy, e.getMessage());
            }
        });

        return channelFactory.createChannel(realm, connection.getSideAToSideB(), binding);
    }
}