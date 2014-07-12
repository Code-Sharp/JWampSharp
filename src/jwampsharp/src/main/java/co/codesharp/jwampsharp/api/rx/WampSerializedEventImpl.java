package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.api.serialization.SerializedValue;

/**
 * Created by Elad on 7/12/2014.
 */
class WampSerializedEventImpl implements WampSerializedEvent {

    private static final SerializedValue[] EmptyArguments = new SerializedValue[0];
    private long publicationId;
    private SerializedValue details;
    private SerializedValue[] arguments;
    private SerializedValue argumentsKeywords;

    public WampSerializedEventImpl(long publicationId, SerializedValue details) {
        this(publicationId, details, EmptyArguments);
    }

    public WampSerializedEventImpl(long publicationId, SerializedValue details, SerializedValue[] arguments) {
        this(publicationId, details, arguments, null);
    }

    public WampSerializedEventImpl(long publicationId, SerializedValue details, SerializedValue[] arguments, SerializedValue argumentsKeywords) {
        this.publicationId = publicationId;
        this.details = details;
        this.arguments = arguments;
        this.argumentsKeywords = argumentsKeywords;
    }

    @Override
    public long getPublicationId() {
        return publicationId;
    }

    @Override
    public SerializedValue getDetails() {
        return details;
    }

    @Override
    public SerializedValue[] getArguments() {
        return arguments;
    }

    @Override
    public SerializedValue getArgumentsKeywords() {
        return argumentsKeywords;
    }
}