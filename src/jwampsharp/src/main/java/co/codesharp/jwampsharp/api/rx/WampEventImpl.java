package co.codesharp.jwampsharp.api.rx;

import java.util.Map;

/**
 * Created by Elad on 7/12/2014.
 */
public class WampEventImpl implements WampEvent {
    private Map<String, Object> options;
    private Object[] arguments;
    private Map<String, Object> argumentsKeywords;

    @Override
    public Map<String, Object> getOptions() {
        return options;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Map<String, Object> getArgumentsKeywords() {
        return argumentsKeywords;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    public void setArgumentsKeywords(Map<String, Object> argumentsKeywords) {
        this.argumentsKeywords = argumentsKeywords;
    }
}