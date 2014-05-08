package com.wampsharp.jwampsharp.client.realm;

import com.wampsharp.jwampsharp.client.rpc.WampRpcOperationCatalogProxy;
import com.wampsharp.jwampsharp.client.pubSub.WampTopicContainerProxy;
import com.wampsharp.jwampsharp.core.contracts.WampServerProxy;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampRealmProxy {

    String getName();

    WampTopicContainerProxy getTopicContainer();

    WampRpcOperationCatalogProxy getRpcCatalog();

    WampServerProxy getProxy();
}