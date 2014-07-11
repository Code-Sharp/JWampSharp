package co.codesharp.jwampsharp.core.binding;

public interface WampTransportBinding<TMessage, TRaw> extends WampBinding<TMessage>,
        WampMessageParser<TMessage, TRaw> {
}
