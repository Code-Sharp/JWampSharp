package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.client.realm.WampRealmProxy;

/**
 * Created by Elad on 16/04/2014.
 */
public interface WampRealmProxyFactory<TMessage> {
    WampRealmProxy build(DefaultWampClient<TMessage> client);
}