package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.api.serialization.SerializedValue;
import co.codesharp.jwampsharp.api.serialization.SerializedValueImpl;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 7/12/2014.
 */
class WampSerializedEventRawImpl<TMessage> extends WampSerializedEventImpl {
    public WampSerializedEventRawImpl(WampFormatter<TMessage> formatter, long publicationId, TMessage details) {
        super(publicationId, getSerializedArgument(formatter, details));
    }

    public WampSerializedEventRawImpl(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments) {
        super(publicationId,
                getSerializedArgument(formatter, details),
                getSerializedArguments(formatter, arguments));
    }

    public WampSerializedEventRawImpl(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        super(publicationId,
                getSerializedArgument(formatter, details),
                getSerializedArguments(formatter, arguments),
                getSerializedArgument(formatter, argumentsKeywords));
    }

    private static <TOther> SerializedValue getSerializedArgument(WampFormatter<TOther> formatter, TOther argument) {
        if (argument == null) {
            return null;
        } else {
            return new SerializedValueImpl<TOther>(formatter, argument);
        }
    }

    private static <TOther> SerializedValue[] getSerializedArguments(WampFormatter<TOther> formatter, TOther[] arguments) {
        if (arguments == null) {
            return null;
        } else {
            SerializedValue[] result = new SerializedValue[arguments.length];

            for (int i = 0; i < arguments.length; i++) {
                TOther currentArgument = arguments[i];
                result[i] = new SerializedValueImpl<TOther>(formatter, currentArgument);
            }

            return result;
        }
    }
}