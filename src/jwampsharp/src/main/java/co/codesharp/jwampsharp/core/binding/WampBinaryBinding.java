package co.codesharp.jwampsharp.core.binding;

/**
 * Created by Elad on 17/04/2014.
 */
public interface WampBinaryBinding<TMessage> extends WampBinding<TMessage>, WampBinaryMessageParser<TMessage> {
}
