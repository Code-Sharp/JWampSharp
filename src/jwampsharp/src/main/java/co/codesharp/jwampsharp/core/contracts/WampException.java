package co.codesharp.jwampsharp.core.contracts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elad on 24/05/2014.
 */
public class WampException extends Exception {
    private static final Map<String, Object> emptyDetails = Collections.unmodifiableMap(new HashMap<String, Object>());

    private final Map<String, Object> details;
    private final String errorUri;
    private final Object[] arguments;
    private final Map<String, Object> argumentsKeywords;

    public WampException(Map<String, Object> details, String errorUri) {
        this(details, errorUri, null);
    }

    public WampException(Map<String, Object> details, String errorUri, Object... arguments) {
        this(details, errorUri, arguments, null);
    }

    public WampException(Map<String, Object> details, String errorUri, Object[] arguments, Map<String, Object> argumentsKeywords) {
        this.details = details;
        this.errorUri = errorUri;
        this.arguments = arguments;
        this.argumentsKeywords = argumentsKeywords;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public String getErrorUri() {
        return errorUri;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Map<String, Object> getArgumentsKeywords() {
        return argumentsKeywords;
    }
}