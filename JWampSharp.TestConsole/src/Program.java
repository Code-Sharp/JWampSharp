import Client.WampChannel;
import Core.Contracts.WampClient;
import Core.Dispatch.DefaultWampClientIncomingMessageHandler;
import Core.Listener.EventArgs.WampMessageArrivedEventArgs;
import Core.Proxy.DefaultWampServerProxy;
import Core.Proxy.WampProtocol;
import Core.Utilities.EventArgs;
import Core.Utilities.EventListener;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elad on 15/04/2014.
 */
public class Program {
    public static void main(String[] args) {

        try {
            JsonNodeChannelFactory factory = new JsonNodeChannelFactory();

            WampChannel<JsonNode> channel = factory.createChannel("realm1", new WebsocketWampTextConnection<JsonNode>(new URI("ws://localhost:8080/ws"),
                    factory.getBinding()));

            channel.open();

        }
        catch (Exception ex) {
        }

        while (true){
            try {
                System.in.read();
            }
            catch (Exception ex)
            {

            }
        }
    }
}
