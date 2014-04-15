import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;

/**
 * Created by Elad on 15/04/2014.
 */
public class JsonNodeFormatter implements Core.Serialization.WampFormatter<com.fasterxml.jackson.databind.JsonNode> {
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
            return null;
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
