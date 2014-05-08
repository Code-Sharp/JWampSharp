package com.wampsharp.jwampsharp.client;

import com.wampsharp.jwampsharp.client.realm.WampRealmProxy;

/**
 * Created by Elad on 16/04/2014.
 */
public interface WampRealmProxyFactory<TMessage> {
    WampRealmProxy build(DefaultWampClient<TMessage> client);
}