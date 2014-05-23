package co.codesharp.jwampsharp.core.contracts;

import co.codesharp.jwampsharp.core.contracts.error.WampErrorServer;
import co.codesharp.jwampsharp.core.contracts.pubSub.WampBroker;
import co.codesharp.jwampsharp.core.contracts.rpc.WampDealer;
import co.codesharp.jwampsharp.core.contracts.session.WampSessionServer;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServer<TMessage> extends WampSessionServer<TMessage>,WampDealer<TMessage>,WampBroker<TMessage>,WampErrorServer<TMessage> {

}