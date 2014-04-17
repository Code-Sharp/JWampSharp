package Client.Rpc.Caller;

import Rpc.WampRpcOperationCallback;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampRpcOperationInvokerProxy {
    void invoke(WampRpcOperationCallback caller, Object options, String procedure);

    void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments);

    void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords);
}
