package co.codesharp.jwampsharp.api.client;

import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/11/2014.
 */
public interface WampChannel {
    WampServerProxy getServer();

    WampRealmProxy getRealmProxy();

    CompletionStage open();
}
