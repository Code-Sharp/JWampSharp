package Client.Rpc;

import Core.Contracts.Rpc.WampCallee;
import Core.Contracts.WampServerProxy;
import Core.Serialization.WampFormatter;
import Rpc.WampRpcOperation;
import Rpc.WampRpcOperationCallback;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampRpcOperationCatalogProxy<TMessage> implements WampRpcOperationCatalogProxy, WampCallee<TMessage> {

    private WampClientCallee<TMessage> callee;

    public DefaultWampRpcOperationCatalogProxy(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.callee = new WampClientCallee<TMessage>(proxy, formatter);
    }

    @Override
    public CompletionStage register(WampRpcOperation operation, Object options) {
        return callee.register(operation, options);
    }

    @Override
    public void registered(long requestId, long registrationId) {
        callee.registered(requestId, registrationId);
    }

    @Override
    public CompletionStage unregister(WampRpcOperation operation) {
        return callee.unregister(operation);
    }

    @Override
    public void unregistered(long requestId) {
        callee.unregistered(requestId);
    }

    @Override
    public void invocation(long requestId, long registrationId, TMessage details) {
        callee.invocation(requestId, registrationId, details);
    }

    @Override
    public void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments) {
        callee.invocation(requestId, registrationId, details, arguments);
    }

    @Override
    public void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        callee.invocation(requestId, registrationId, details, arguments, argumentsKeywords);
    }

    @Override
    public void interrupt(long requestId, TMessage options) {
        callee.interrupt(requestId, options);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure) {

    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {

    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {

    }
}