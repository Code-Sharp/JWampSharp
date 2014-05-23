package co.codesharp.jwampsharp.defaultBinding;

import co.codesharp.jwampsharp.core.binding.WampTextBinding;
import co.codesharp.jwampsharp.core.message.WampMessage;

import java.net.URI;

/**
 * Created by Elad on 15/04/2014.
 */
public class WebsocketWampTextConnection<TMessage> extends WebsocketWampConnection<TMessage> {

    private final WampTextBinding<TMessage> binding;

    public WebsocketWampTextConnection(URI serverURI, WampTextBinding<TMessage> binding) {
        super(serverURI);
        this.binding = binding;
    }

    @Override
    public void send(WampMessage<TMessage> message) {
        String raw = this.binding.format(message);
        this.send(raw);
    }

    @Override
    protected void onMessage(String message) {
        WampMessage<TMessage> parsed = this.binding.parse(message);
        this.raiseMessageArrived(parsed);
    }
}