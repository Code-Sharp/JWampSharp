package co.codesharp.jwampsharp.client.rpc.Callee;

import co.codesharp.jwampsharp.rpc.WampRpcOperation;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampRpcOperationRegistrationProxy {
    CompletionStage register(WampRpcOperation operation, Object options);

    CompletionStage unregister(WampRpcOperation operation);
}
