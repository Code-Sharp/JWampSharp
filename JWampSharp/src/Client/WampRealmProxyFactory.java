package Client;

import Client.Realm.WampRealmProxy;
import Core.Listener.ControlledWampConnection;

/**
 * Created by Elad on 16/04/2014.
 */
public interface WampRealmProxyFactory<TMessage> {
    WampRealmProxy build(DefaultWampClient<TMessage> client);
}