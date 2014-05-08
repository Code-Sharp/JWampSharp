package com.wampsharp.jwampsharp.core.contracts;

import com.wampsharp.jwampsharp.core.contracts.error.WampError;
import com.wampsharp.jwampsharp.core.contracts.proxy.WampBrokerProxy;
import com.wampsharp.jwampsharp.core.contracts.proxy.WampDealerProxy;
import com.wampsharp.jwampsharp.core.contracts.proxy.WampSessionServerProxy;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServerProxy extends WampSessionServerProxy, WampDealerProxy, WampError<Object>,WampBrokerProxy {

}