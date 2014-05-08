package com.wampsharp.jwampsharp.client;

import com.wampsharp.jwampsharp.client.realm.WampRealmProxy;
import com.wampsharp.jwampsharp.core.contracts.WampServerProxy;
import com.wampsharp.jwampsharp.core.listener.ControlledWampConnection;
import com.wampsharp.jwampsharp.core.utilities.EventArgs;
import com.wampsharp.jwampsharp.core.utilities.EventListener;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Elad on 16/04/2014.
 */
public class WampChannel<TMessage> {
    private final ControlledWampConnection<TMessage> connection;
    private final DefaultWampClient<TMessage> client;
    private final WampServerProxy server;
    private final EventListener<EventArgs> connectionOpenListener;
    private final AtomicBoolean connectCalled = new AtomicBoolean();
    private final EventListener<EventArgs> connectionClosedListener;

    public WampChannel(ControlledWampConnection<TMessage> connection, DefaultWampClient<TMessage> client) {
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
