package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.core.contracts.session.WampSessionClient;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampSessionClientExtended<TMessage> extends WampSessionClient<TMessage> {
    long getSession();

    WampRealmProxy getRealm();

    CompletionStage getOpenTask();

    void onConnectionOpen();

    void onConnectionClosed();
}