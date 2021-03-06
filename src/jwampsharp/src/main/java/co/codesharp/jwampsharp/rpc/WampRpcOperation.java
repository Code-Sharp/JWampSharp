package co.codesharp.jwampsharp.rpc;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampRpcOperation {
    String getProcedure();

    <TMessage>void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage details);

    <TMessage>void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage options, TMessage[] arguments);

    <TMessage>void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage options, TMessage[] arguments, TMessage argumentsKeywords);
}