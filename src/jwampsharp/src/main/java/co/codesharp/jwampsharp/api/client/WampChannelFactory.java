package co.codesharp.jwampsharp.api.client;

import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;

/**
 * Created by Elad on 7/11/2014.
 */
public interface WampChannelFactory {
    <TMessage> WampChannel createChannel
            (String realm,
             ControlledWampConnection<TMessage> connection,
             WampBinding<TMessage> binding);
}
