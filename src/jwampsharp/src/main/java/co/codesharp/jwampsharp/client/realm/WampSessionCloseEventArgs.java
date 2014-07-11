package co.codesharp.jwampsharp.client.realm;

/**
 * Created by Elad on 7/11/2014.
 */
public abstract class WampSessionCloseEventArgs extends WampSessionEventArgs {
    private final SessionCloseType closeType;
    private final String reason;

    public WampSessionCloseEventArgs(SessionCloseType closeType,
                                     long sessionId,
                                     String reason) {
        super(sessionId);
        this.closeType = closeType;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public SessionCloseType getCloseType() {
        return closeType;
    }
}