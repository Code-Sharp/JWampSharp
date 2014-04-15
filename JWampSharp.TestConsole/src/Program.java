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

        Exception ex2 = null;

        try {
            JsonNodeBinding binding = new JsonNodeBinding();

            WampClient<JsonNode> client = new MyClient();

            final DefaultWampClientIncomingMessageHandler<JsonNode> handler =
                    new DefaultWampClientIncomingMessageHandler<JsonNode>
                            (JsonNode.class, JsonNode[].class, binding.getFormatter(),
                                    client);

            WebsocketWampTextConnection connection =
                    new WebsocketWampTextConnection(new URI("ws://localhost:8080/ws"),
                            binding);

            final DefaultWampServerProxy<JsonNode> serverProxy = new DefaultWampServerProxy<JsonNode>(connection,
                    new WampProtocol<JsonNode>(JsonNode.class, binding.getFormatter()));

            connection.getMessageArrived().addListener(new EventListener<WampMessageArrivedEventArgs<JsonNode>>() {
                @Override
                public void eventOccured(Object sender, WampMessageArrivedEventArgs<JsonNode> e) {
                    handler.handleMessage(e.getMessage());
                }
            });

            connection.getConnectionOpen().addListener(new EventListener() {
                @Override
                public void eventOccured(Object sender, EventArgs e) {
                    serverProxy.hello("realm1", new HashMap<String, String>() );
                }
            });

            connection.connect();
        }
        catch (Exception ex) {
            ex2 = ex;
            System.out.println(ex);
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


class MyClient implements WampClient<JsonNode>{

    @Override
    public void challenge(String challenge, JsonNode extra) {

    }

    @Override
    public void welcome(long session, JsonNode details) {

    }

    @Override
    public void abort(JsonNode details, String reason) {

    }

    @Override
    public void goodbye(JsonNode details, String reason) {

    }

    @Override
    public void heartbeat(int incomingSeq, int outgoingSeq) {

    }

    @Override
    public void heartbeat(int incomingSeq, int outgoingSeq, String discard) {

    }

    @Override
    public void registered(long requestId, long registrationId) {

    }

    @Override
    public void unregistered(long requestId) {

    }

    @Override
    public void invocation(long requestId, long registrationId, JsonNode details) {

    }

    @Override
    public void invocation(long requestId, long registrationId, JsonNode details, JsonNode[] arguments) {

    }

    @Override
    public void invocation(long requestId, long registrationId, JsonNode details, JsonNode[] arguments, JsonNode argumentsKeywords) {

    }

    @Override
    public void interrupt(long requestId, JsonNode options) {

    }

    @Override
    public void result(long requestId, JsonNode details) {

    }

    @Override
    public void result(long requestId, JsonNode details, JsonNode[] arguments) {

    }

    @Override
    public void result(long requestId, JsonNode details, JsonNode[] arguments, JsonNode argumentsKeywords) {

    }

    @Override
    public void published(long requestId, long publicationId) {

    }

    @Override
    public void subscribed(long requestId, long subscriptionId) {

    }

    @Override
    public void unsubscribed(long requestId, long subscriptionId) {

    }

    @Override
    public void event(long subscriptionId, long publicationId, JsonNode details) {

    }

    @Override
    public void event(long subscriptionId, long publicationId, JsonNode details, JsonNode[] arguments) {

    }

    @Override
    public void event(long subscriptionId, long publicationId, JsonNode details, JsonNode[] arguments, JsonNode argumentsKeywords) {

    }

    @Override
    public void error(int requestType, long requestId, JsonNode details, String error) {

    }

    @Override
    public void error(int requestType, long requestId, JsonNode details, String error, JsonNode[] arguments) {

    }

    @Override
    public void error(int requestType, long requestId, JsonNode details, String error, JsonNode[] arguments, JsonNode argumentsKeywords) {

    }
}
