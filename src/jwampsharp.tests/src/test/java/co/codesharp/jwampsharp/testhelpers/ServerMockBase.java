package co.codesharp.jwampsharp.testhelpers;

import co.codesharp.jwampsharp.core.contracts.WampClientProxy;
import co.codesharp.jwampsharp.core.contracts.WampServer;

import java.util.HashMap;

/**
 * Created by Elad on 7/13/2014.
 */
public abstract class ServerMockBase<TMessage> implements WampServer<TMessage> {
    private final long sessionId;

    protected ServerMockBase(long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void hello(WampClientProxy client, String realm, TMessage details) {
        client.welcome(sessionId, new HashMap<String, Object>());
    }
}
