import Client.WampChannelFactory;
import Core.Listener.WampBinding;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Elad on 16/04/2014.
 */
public class JsonNodeChannelFactory extends WampChannelFactory<JsonNode> {
    public JsonNodeChannelFactory() {
        super(new JsonNodeBinding(), JsonNode.class, JsonNode[].class);
    }
}