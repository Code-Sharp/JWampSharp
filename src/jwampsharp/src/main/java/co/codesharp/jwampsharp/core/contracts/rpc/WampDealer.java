package co.codesharp.jwampsharp.core.contracts.rpc;

import co.codesharp.jwampsharp.core.contracts.WampClientProxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampDealer<TMessage> {
    void register(WampClientProxy client, long requestId, TMessage options, String procedure);

    void unregister(WampClientProxy client, long requestId, long registrationId);

    void call(WampClientProxy client, long requestId, TMessage options, String procedure);

    void call(WampClientProxy client, long requestId, TMessage options, String procedure, TMessage[] arguments);

    void call(WampClientProxy client, long requestId, TMessage options, String procedure, TMessage[] arguments, TMessage argumentsKeywords);

    void cancel(WampClientProxy client, long requestId, TMessage options);

    void yield(WampClientProxy client, long requestId, TMessage options);

    void yield(WampClientProxy client, long requestId, TMessage options, TMessage[] arguments);

    void yield(WampClientProxy client, long requestId, TMessage options, TMessage[] arguments, TMessage argumentsKeywords);
}
