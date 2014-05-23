package co.codesharp.jwampsharp.core.contracts;

import co.codesharp.jwampsharp.core.contracts.rpc.WampCaller;
import co.codesharp.jwampsharp.core.contracts.error.WampError;
import co.codesharp.jwampsharp.core.contracts.pubSub.WampPublisher;
import co.codesharp.jwampsharp.core.contracts.pubSub.WampSubscriber;
import co.codesharp.jwampsharp.core.contracts.rpc.WampCallee;
import co.codesharp.jwampsharp.core.contracts.session.WampSessionClient;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampClient<TMessage> extends WampSessionClient<TMessage>,WampCallee<TMessage>,WampCaller<TMessage>,WampPublisher, WampSubscriber<TMessage>,WampError<TMessage> {


}