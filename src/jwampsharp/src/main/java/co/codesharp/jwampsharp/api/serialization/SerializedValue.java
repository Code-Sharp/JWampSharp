package co.codesharp.jwampsharp.api.serialization;

/**
 * Created by Elad on 7/11/2014.
 */
public interface SerializedValue {
    <T> T Deserialize(Class<T> type);
}
