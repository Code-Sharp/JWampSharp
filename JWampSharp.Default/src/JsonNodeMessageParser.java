import Core.Binding.WampTextMessageParser;
import Core.Message.WampMessage;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Elad on 15/04/2014.
 */
public class JsonNodeMessageParser implements WampTextMessageParser<JsonNode> {
    private final JsonFactory jsonFactory;
    private ObjectMapper mapper;

    public JsonNodeMessageParser() {
        jsonFactory = new JsonFactory();
        mapper = new ObjectMapper(jsonFactory);
    }

    @Override
    public WampMessage<JsonNode> parse(String text) {
        try {
            JsonNode node = mapper.readTree(text);

            Iterator<JsonNode> iterator = node.elements();

            JsonNode messageTypeNode = iterator.next();

            int messageType = messageTypeNode.asInt();

            JsonNode[] arguments = getArguments(iterator);

            WampMessage<JsonNode> result =
                    new WampMessage<JsonNode>();

            result.setMessageType(messageType);
            result.setArguments(arguments);

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private JsonNode[] getArguments(Iterator<JsonNode> iterator) {
        ArrayList<JsonNode> arguments = new ArrayList<JsonNode>();

        while (iterator.hasNext()) {
            JsonNode currentNode = iterator.next();
            arguments.add(currentNode);
        }

        JsonNode[] result = new JsonNode[arguments.size()];

        return arguments.toArray(result);
    }

    @Override
    public String format(WampMessage<JsonNode> message) {

        try {
            JsonNode[] arguments = message.getArguments();
            ArrayList<JsonNode> array = new ArrayList<JsonNode>(arguments.length + 1);
            array.add(new IntNode(message.getMessageType()));
            Collections.addAll(array, arguments);

            ArrayNode node = mapper.createArrayNode();
            node.addAll(array);

            StringWriter stringWriter = new StringWriter();
            mapper.writeTree(jsonFactory.createGenerator(stringWriter), node);

            return stringWriter.toString();
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
