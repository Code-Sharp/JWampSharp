package co.codesharp.jwampsharp.defaultBinding.jsr;

import co.codesharp.jwampsharp.core.binding.WampBinaryBinding;
import co.codesharp.jwampsharp.core.message.WampMessage;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Created by Elad on 17/04/2014.
 */
public class WebsocketWampBinaryConnection<TMessage> extends WebsocketWampConnection<TMessage> {
    private final WampBinaryBinding<TMessage> binding;

    public WebsocketWampBinaryConnection(URI serverPath, WampBinaryBinding<TMessage> binding) {
        super(serverPath, binding);
        this.binding = binding;
    }

    @Override
    public void send(WampMessage<TMessage> message) {
        byte[] bytes = binding.format(message);
        this.send(bytes);
    }

    @Override
    protected void onMessage(ByteBuffer bytes) {
        byte[] byteArray = bytes.array();
        WampMessage<TMessage> parsed = binding.parse(byteArray);
        this.raiseMessageArrived(parsed);
    }
}
