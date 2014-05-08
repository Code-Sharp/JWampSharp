package com.wampsharp.jwampsharp.core.listener.eventArgs;

import com.wampsharp.jwampsharp.core.utilities.EventArgs;

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