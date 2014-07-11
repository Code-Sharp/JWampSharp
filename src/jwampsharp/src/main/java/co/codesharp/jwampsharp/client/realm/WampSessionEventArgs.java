package co.codesharp.jwampsharp.client.realm;

import co.codesharp.jwampsharp.core.utilities.EventArgs;

import java.lang.reflect.Type;

/**
 * Created by Elad on 7/11/2014.
 */
public abstract class WampSessionEventArgs extends EventArgs {
    private final long sessionId;

    public WampSessionEventArgs(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public abstract <TTarget> TTarget deserializeDetails(Class<TTarget> type);
}