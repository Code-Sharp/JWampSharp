package Core.Contracts;

import Core.Contracts.Error.WampErrorServer;
import Core.Contracts.PubSub.WampBroker;
import Core.Contracts.Rpc.WampDealer;
import Core.Contracts.Session.WampSessionServer;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServer<TMessage> extends WampSessionServer<TMessage>,WampDealer<TMessage>,WampBroker<TMessage>,WampErrorServer<TMessage> {

}