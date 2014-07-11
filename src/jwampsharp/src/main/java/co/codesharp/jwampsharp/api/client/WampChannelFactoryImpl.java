package co.codesharp.jwampsharp.api.client;

import co.codesharp.jwampsharp.client.WampChannelBuilder;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Elad on 7/11/2014.
 */
public class WampChannelFactoryImpl implements WampChannelFactory {
    private ConcurrentMap<WampBinding<?>, WampChannelBuilder<?>> bindingToBuilder =
            new ConcurrentHashMap<WampBinding<?>, WampChannelBuilder<?>>();

    @Override
    public <TMessage> WampChannel createChannel(String realm, ControlledWampConnection<TMessage> connection, WampBinding<TMessage> binding) {
        WampChannelBuilder<TMessage> channelBuilder = GetChannelBuilder(binding);

        return channelBuilder.createChannel(realm, connection);
    }

    private <TMessage> WampChannelBuilder<TMessage> GetChannelBuilder(WampBinding<TMessage> binding) {
        WampChannelBuilder<TMessage> builder =
                (WampChannelBuilder<TMessage>) bindingToBuilder.get(binding);

        if (builder == null) {
            final WampChannelBuilder<TMessage> value = new WampChannelBuilder<TMessage>(binding);
            builder = (WampChannelBuilder<TMessage>) bindingToBuilder.putIfAbsent(binding, value);
            if (builder == null) {
                builder = value;
            }
        }

        return builder;
    }
}