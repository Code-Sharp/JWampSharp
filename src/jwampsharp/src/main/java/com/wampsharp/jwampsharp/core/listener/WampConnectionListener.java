package com.wampsharp.jwampsharp.core.listener;

import com.wampsharp.jwampsharp.core.listener.eventArgs.WampNewConnectionEventArgs;
import com.wampsharp.jwampsharp.core.utilities.EventHandler;

/**
 * Created by Elad on 17/04/2014.
 */
public interface WampConnectionListener {
    EventHandler<WampNewConnectionEventArgs> getNewConnection();
}