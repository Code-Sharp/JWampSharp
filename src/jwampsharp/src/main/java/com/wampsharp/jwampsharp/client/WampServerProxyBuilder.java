package com.wampsharp.jwampsharp.client;

import com.wampsharp.jwampsharp.core.contracts.WampClient;
import com.wampsharp.jwampsharp.core.contracts.WampServerProxy;
import com.wampsharp.jwampsharp.core.listener.WampConnection;

/**
 * Created by Elad on 16/04/2014.
 */
public interface WampServerProxyBuilder<TMessage> {
    WampServerProxy Create(WampClient<TMessage> client, WampConnection<TMessage> connection);
}