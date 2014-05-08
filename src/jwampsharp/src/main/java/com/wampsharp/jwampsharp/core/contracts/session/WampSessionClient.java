package com.wampsharp.jwampsharp.core.contracts.session;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampSessionClient<TMessage> {
    void challenge(String challenge, TMessage extra);

    void welcome(long session, TMessage details);

    void abort(TMessage details, String reason);

    void goodbye(TMessage details, String reason);

    void heartbeat(int incomingSeq, int outgoingSeq);

    void heartbeat(int incomingSeq, int outgoingSeq, String discard);
}
