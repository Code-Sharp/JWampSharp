package co.codesharp.jwampsharp.core.listener.eventArgs;

import co.codesharp.jwampsharp.core.utilities.EventArgs;

/**
 * Created by Elad on 15/04/2014.
 */
public class WampConnectionErrorEventArgs extends EventArgs {
    private final Throwable throwable;

    public WampConnectionErrorEventArgs(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}