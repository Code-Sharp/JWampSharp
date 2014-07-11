package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;
import co.codesharp.jwampsharp.core.utilities.EventArgs;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import co.codesharp.jwampsharp.core.utilities.EventListener;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Elad on 16/04/2014.
 */
public class WampChannelImpl<TMessage> implements WampChannel {
    private final ControlledWampConnection<TMessage> connection;
    private final DefaultWampClient<TMessage> client;
    private final WampServerProxy server;
    private final EventListener<EventArgs> connectionOpenListener;
    private final AtomicBoolean connectCalled = new AtomicBoolean();
    private final EventListener<EventArgs> connectionClosedListener;

    public WampChannelImpl(ControlledWampConnection<TMessage> connection, DefaultWampClient<TMessage> client) {
        this.connection = connection;
        this.client = client;
        server = client.getRealm().getProxy();

        connectionOpenListener = new EventListener<EventArgs>() {
            @Override
            public void eventOccured(Object sender, EventArgs e) {
                onConnectionOpen(sender, e);
            }
        };

        this.connection.getConnectionOpen()
                .addListener(connectionOpenListener);

        connectionClosedListener = new EventListener<EventArgs>() {
            @Override
            public void eventOccured(Object sender, EventArgs e) {
                onConnectionClosed(sender, e);
            }
        };

        this.connection.getConnectionClosed()
                .addListener(connectionClosedListener);
    }

    private void onConnectionOpen(Object sender, EventArgs e) {
        client.onConnectionOpen();
    }

    private void onConnectionClosed(Object sender, EventArgs e) {
        client.onConnectionClosed();
    }

    public WampServerProxy getServer() {
        return server;
    }

    public WampRealmProxy getRealmProxy() {
        return client.getRealm();
    }

    public CompletionStage open() throws Exception {
        if (!connectCalled.compareAndSet(false, true)) {
            // Throw something that says that "open was already called."
            throw new Exception();
        } else {
            connection.connect();
            return client.getOpenTask();
        }
    }
}