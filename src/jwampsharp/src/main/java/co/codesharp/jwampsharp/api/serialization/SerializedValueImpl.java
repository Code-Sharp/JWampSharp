package co.codesharp.jwampsharp.api.serialization;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 7/12/2014.
 */
public class SerializedValueImpl<TMessage> implements SerializedValue {
    private WampFormatter<TMessage> formatter;
    private TMessage value;

    public SerializedValueImpl(WampFormatter<TMessage> formatter, TMessage value) {
        this.formatter = formatter;
        this.value = value;
    }

    @Override
    public <T> T deserialize(Class<T> type) {
        return formatter.deserialize(type, value);
    }
}