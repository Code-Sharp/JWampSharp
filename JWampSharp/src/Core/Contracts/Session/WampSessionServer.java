package Core.Contracts.Session;

import Core.Contracts.WampClientProxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampSessionServer<TMessage> {
    void hello(WampClientProxy client, String realm, TMessage details);

    void abort(WampClientProxy client, TMessage details, String reason);

    void authenticate(WampClientProxy client, String signature, TMessage extra);

    void goodbye(WampClientProxy client, TMessage details, String reason);

    void heartbeat(WampClientProxy client, int incomingSeq, int outgoingSeq);

    void heartbeat(WampClientProxy client, int incomingSeq, int outgoingSeq, String discard);
}
