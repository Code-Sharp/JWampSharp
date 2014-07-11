package co.codesharp.jwampsharp.defaultBinding;

import co.codesharp.jwampsharp.client.WampChannelBuilder;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Elad on 16/04/2014.
 */
class JsonNodeChannelBuilder extends WampChannelBuilder<JsonNode> {
    private JsonNodeBinding binding;

    private JsonNodeChannelBuilder(JsonNodeBinding binding) {
        super(binding, JsonNode.class, JsonNode[].class);
        this.binding = binding;
    }

    public JsonNodeChannelBuilder() {
        this(new JsonNodeBinding());
    }

    public JsonNodeBinding getBinding() {
        return binding;
    }
}