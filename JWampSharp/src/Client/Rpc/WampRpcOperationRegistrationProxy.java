package Client.Rpc;

import java.util.concurrent.Future;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampRpcOperationRegistrationProxy {
    Future register(WampRpcOperation operation, Object options);

    Future unregister(WampRpcOperation operation);
}
