import Core.Binding.WampBinaryBinding;
import Core.Message.WampMessage;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Created by Elad on 17/04/2014.
 */
public class WebsocketWampBinaryConnection<TMessage> extends WebsocketWampConnection<TMessage> {
    private final WampBinaryBinding<TMessage> binding;

    public WebsocketWampBinaryConnection(URI serverURI, WampBinaryBinding<TMessage> binding) {
        super(serverURI);
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
