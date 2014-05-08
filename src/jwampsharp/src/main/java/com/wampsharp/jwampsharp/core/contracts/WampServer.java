package com.wampsharp.jwampsharp.core.contracts;

import com.wampsharp.jwampsharp.core.contracts.error.WampErrorServer;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampBroker;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampDealer;
import com.wampsharp.jwampsharp.core.contracts.session.WampSessionServer;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampServer<TMessage> extends WampSessionServer<TMessage>,WampDealer<TMessage>,WampBroker<TMessage>,WampErrorServer<TMessage> {

}