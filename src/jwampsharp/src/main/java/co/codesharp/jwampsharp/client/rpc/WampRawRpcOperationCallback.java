package co.codesharp.jwampsharp.client.rpc;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 18/04/2014.
 */
public interface WampRawRpcOperationCallback {
    <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details);

    <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments);

    <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords);

    <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error);

    <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments);

    <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
