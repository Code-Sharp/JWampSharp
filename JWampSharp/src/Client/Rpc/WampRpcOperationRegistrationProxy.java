package Client.Rpc;

import Rpc.WampRpcOperation;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampRpcOperationRegistrationProxy {
    CompletionStage register(WampRpcOperation operation, Object options);

    CompletionStage unregister(WampRpcOperation operation);
}
