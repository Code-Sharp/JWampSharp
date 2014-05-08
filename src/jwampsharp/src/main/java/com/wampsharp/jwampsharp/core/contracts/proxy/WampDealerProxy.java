package com.wampsharp.jwampsharp.core.contracts.proxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampDealerProxy {
    void register(long requestId, Object options, String procedure);

    void unregister(long requestId, long registrationId);

    void call(long requestId, Object options, String procedure);

    void call(long requestId, Object options, String procedure, Object[] arguments);

    void call(long requestId, Object options, String procedure, Object[] arguments, Object argumentsKeywords);

    void cancel(long requestId, Object options);

    void yield(long requestId, Object options);

    void yield(long requestId, Object options, Object[] arguments);

    void yield(long requestId, Object options, Object[] arguments, Object argumentsKeywords);
}
