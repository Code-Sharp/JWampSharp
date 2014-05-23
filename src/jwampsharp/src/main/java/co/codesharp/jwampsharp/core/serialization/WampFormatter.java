package co.codesharp.jwampsharp.core.serialization;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampFormatter<TMessage> {

    <TTarget> TTarget deserialize(Class<TTarget> type, TMessage message);

    Object deserializeWeak(Class type, TMessage message);

    TMessage serialize(Object value);
}