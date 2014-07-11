package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.api.serialization.SerializedValue;

/**
 * Created by Elad on 7/11/2014.
 */
public interface WampSerializedEvent {
    long getPublicationId();
    SerializedValue getDetails();
    SerializedValue[] getArguments();
    SerializedValue getArgumentsKeywords();
}