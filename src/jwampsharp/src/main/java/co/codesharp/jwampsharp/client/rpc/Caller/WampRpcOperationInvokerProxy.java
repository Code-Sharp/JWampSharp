package co.codesharp.jwampsharp.client.rpc.Caller;

import co.codesharp.jwampsharp.client.rpc.WampRawRpcOperationCallback;
import co.codesharp.jwampsharp.rpc.WampRpcOperationCallback;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampRpcOperationInvokerProxy {
    void invoke(WampRpcOperationCallback caller, Object options, String procedure);

    void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments);

    void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords);

    void invoke(WampRawRpcOperationCallback caller, Object options, String procedure);

    void invoke(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments);

    void invoke(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords);

}
