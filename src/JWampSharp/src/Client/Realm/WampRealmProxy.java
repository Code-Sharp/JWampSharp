package Client.Realm;

import Client.Rpc.WampRpcOperationCatalogProxy;
import Client.PubSub.WampTopicContainerProxy;
import Core.Contracts.WampServerProxy;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampRealmProxy {

    String getName();

    WampTopicContainerProxy getTopicContainer();

    WampRpcOperationCatalogProxy getRpcCatalog();

    WampServerProxy getProxy();
}