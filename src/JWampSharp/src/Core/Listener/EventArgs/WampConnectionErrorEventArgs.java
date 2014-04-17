package Core.Listener.EventArgs;

import Core.Utilities.EventArgs;

/**
 * Created by Elad on 15/04/2014.
 */
public class WampConnectionErrorEventArgs extends EventArgs {
    private final Exception exception;

    public WampConnectionErrorEventArgs(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}