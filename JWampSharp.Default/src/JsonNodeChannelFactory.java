import Client.WampChannelFactory;
import Core.Listener.WampBinding;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Elad on 16/04/2014.
 */
public class JsonNodeChannelFactory extends WampChannelFactory<JsonNode> {
    private JsonNodeBinding binding;

    private JsonNodeChannelFactory(JsonNodeBinding binding) {
        super(binding, JsonNode.class, JsonNode[].class);
        this.binding = binding;
    }

    public JsonNodeChannelFactory() {
        this(new JsonNodeBinding());
    }

    public JsonNodeBinding getBinding() {
        return binding;
    }
}