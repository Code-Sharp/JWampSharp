package co.codesharp.jwampsharp.defaultBinding;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Elad on 15/04/2014.
 */
public class JsonNodeFormatter implements WampFormatter<JsonNode> {
    private final JsonFactory jsonFactory;
    private final ObjectMapper mapper;

    public JsonNodeFormatter() {
        jsonFactory = new JsonFactory();
        mapper = new ObjectMapper(jsonFactory);
    }

    @Override
    public <TTarget> TTarget deserialize(Class<TTarget> type, JsonNode jsonNode) {
        try {
            TTarget result = this.mapper.treeToValue(jsonNode, type);
            return result;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Override
    public Object deserializeWeak(Class type, JsonNode jsonNode) {
        return null;
    }

    @Override
    public JsonNode serialize(Object value) {
        return mapper.valueToTree(value);
    }
}
