package co.codesharp.jwampsharp.defaultBinding;

import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.api.client.WampChannelFactoryImpl;
import co.codesharp.jwampsharp.core.binding.WampTextBinding;
import co.codesharp.jwampsharp.defaultBinding.jsr.WebsocketWampTextConnection;

import java.net.URI;

/**
 * Created by Elad on 7/11/2014.
 */
public class DefaultWampChannelFactory extends WampChannelFactoryImpl {
    public <TMessage> WampChannel createChannel
            (URI address,
             String realm,
             WampTextBinding<TMessage> binding) {
        WebsocketWampTextConnection<TMessage> connection =
                new WebsocketWampTextConnection<TMessage>(address, binding);

        return this.createChannel(realm, connection, binding);
    }

    public WampChannel createJsonChannel(URI address,
                                          String realm) {
        JsonNodeBinding binding = new JsonNodeBinding();

        return this.createChannel(address, realm, binding);
    }
}