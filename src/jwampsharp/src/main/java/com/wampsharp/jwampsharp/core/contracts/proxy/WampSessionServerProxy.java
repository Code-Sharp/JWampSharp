package com.wampsharp.jwampsharp.core.contracts.proxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampSessionServerProxy {
    void hello(String realm, Object details);

    void abort(Object details, String reason);

    void authenticate(String signature, Object extra);

    void goodbye(Object details, String reason);

    void heartbeat(int incomingSeq, int outgoingSeq);

    void heartbeat(int incomingSeq, int outgoingSeq, String discard);
}
