package co.codesharp.jwampsharp.core.contracts;

import co.codesharp.jwampsharp.core.contracts.error.WampError;
import co.codesharp.jwampsharp.core.contracts.proxy.WampBrokerProxy;
import co.codesharp.jwampsharp.core.contracts.proxy.WampDealerProxy;
import co.codesharp.jwampsharp.core.contracts.proxy.WampSessionServerProxy;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServerProxy extends WampSessionServerProxy, WampDealerProxy, WampError<Object>,WampBrokerProxy {

}