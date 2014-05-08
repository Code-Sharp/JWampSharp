package com.wampsharp.jwampsharp.core.contracts;

import com.wampsharp.jwampsharp.core.contracts.error.WampError;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampPublisher;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampSubscriber;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCallee;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCaller;
import com.wampsharp.jwampsharp.core.contracts.session.WampSessionClient;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampClient<TMessage> extends WampSessionClient<TMessage>,WampCallee<TMessage>,WampCaller<TMessage>,WampPublisher, WampSubscriber<TMessage>,WampError<TMessage> {


}