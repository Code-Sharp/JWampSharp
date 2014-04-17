package Rpc;

/**
 * Created by Elad on 15/04/2014.
 */

public interface WampRpcOperationCallback {
    void result(Object details);

    void result(Object details, Object[] arguments);

    void result(Object details, Object[] arguments, Object argumentsKeywords);

    void error(Object details, String error);

    void error(Object details, String error, Object[] arguments);

    void error(Object details, String error, Object[] arguments, Object argumentsKeywords);
}