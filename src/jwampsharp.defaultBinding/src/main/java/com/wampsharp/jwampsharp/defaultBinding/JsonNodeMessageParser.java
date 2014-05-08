package com.wampsharp.jwampsharp.defaultBinding;

import com.wampsharp.jwampsharp.core.binding.WampTextMessageParser;
import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.StringWriter;

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
            JsonNode[] array = mapper.treeToValue(node, JsonNode[].class);

            int messageType = array[0].asInt();

            JsonNode[] arguments = new JsonNode[array.length - 1];

            System.arraycopy(array, 1, arguments, 0, array.length - 1);

            WampMessage<JsonNode> result = new WampMessage<JsonNode>();

            result.setMessageType(messageType);
            result.setArguments(arguments);

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String format(WampMessage<JsonNode> message) {

        try {
            JsonNode[] arguments = message.getArguments();
            JsonNode[] array = new JsonNode[arguments.length + 1];
            System.arraycopy(arguments, 0, array, 1, arguments.length);
            array[0] = new IntNode(message.getMessageType());

            JsonNode node = mapper.valueToTree(array);

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
