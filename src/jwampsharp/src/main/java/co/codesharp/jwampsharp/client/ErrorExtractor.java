package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.core.contracts.WampException;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

import java.util.Map;

/**
 * Created by Elad on 7/11/2014.
 */
public class ErrorExtractor {
    public static <TMessage> WampException error(WampFormatter<TMessage> formatter, TMessage details, String error) {
        Map<String, Object> deserializedDetails = deserializeMap(formatter, details);
        return new WampException(deserializedDetails, error);
    }

    public static <TMessage> WampException error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments) {
        Map<String, Object> deserializedDetails = deserializeMap(formatter, details);
        return new WampException(deserializedDetails, error, arguments);
    }

    public static <TMessage> WampException error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        Map<String, Object> deserializedDetails = deserializeMap(formatter, details);
        Map<String, Object> deserializedArgumentsKeywords = deserializeMap(formatter, argumentsKeywords);
        return new WampException(deserializedDetails, error, arguments, deserializedArgumentsKeywords);
    }

    private static <TMessage> Map<String, Object> deserializeMap(WampFormatter<TMessage> formatter, TMessage details) {
        Map result = formatter.deserialize(Map.class, details);
        return result;
    }
}
