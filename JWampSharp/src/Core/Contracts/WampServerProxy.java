package Core.Contracts;

import Core.Contracts.Error.WampError;
import Core.Contracts.Proxy.WampBrokerProxy;
import Core.Contracts.Proxy.WampDealerProxy;
import Core.Contracts.Proxy.WampSessionServerProxy;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServerProxy extends WampSessionServerProxy, WampDealerProxy, WampError<Object>,WampBrokerProxy {

}