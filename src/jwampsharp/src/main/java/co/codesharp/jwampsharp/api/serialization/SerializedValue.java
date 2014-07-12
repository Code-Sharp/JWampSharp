package co.codesharp.jwampsharp.api.serialization;

/**
 * Created by Elad on 7/11/2014.
 */
public interface SerializedValue {
    <T> T deserialize(Class<T> type);
}
