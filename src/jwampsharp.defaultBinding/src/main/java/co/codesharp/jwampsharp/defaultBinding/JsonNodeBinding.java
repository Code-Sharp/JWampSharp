package co.codesharp.jwampsharp.defaultBinding;

import co.codesharp.jwampsharp.core.binding.contracts.JsonBinding;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Elad on 15/04/2014.
 */
public class JsonNodeBinding extends JsonBinding<JsonNode> {
    protected JsonNodeBinding() {
        super(new JsonNodeFormatter(), new JsonNodeMessageParser());
    }

    @Override
    public Class<JsonNode> getUnderlyingMessageType() {
        return JsonNode.class;
    }

    @Override
    public Class<JsonNode[]> getUnderlyingMessageTypeArray() {
        return JsonNode[].class;
    }
}