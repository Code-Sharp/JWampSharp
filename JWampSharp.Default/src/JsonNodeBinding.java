import Core.Binding.Contracts.JsonBinding;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Elad on 15/04/2014.
 */
public class JsonNodeBinding extends JsonBinding<JsonNode> {
    protected JsonNodeBinding() {
        super(new JsonNodeFormatter(), new JsonNodeMessageParser());
    }
}