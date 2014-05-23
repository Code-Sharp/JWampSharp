package co.codesharp.jwampsharp.client.rpc;

import co.codesharp.jwampsharp.client.rpc.Callee.WampClientCallee;
import co.codesharp.jwampsharp.client.rpc.Caller.WampClientCaller;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.contracts.error.WampCalleeError;
import co.codesharp.jwampsharp.core.contracts.error.WampCallerError;
import co.codesharp.jwampsharp.core.contracts.rpc.WampCallee;
import co.codesharp.jwampsharp.core.contracts.rpc.WampCaller;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.rpc.WampRpcOperation;
import co.codesharp.jwampsharp.rpc.WampRpcOperationCallback;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampRpcOperationCatalogProxy<TMessage> implements WampRpcOperationCatalogProxy, WampCallee<TMessage>, WampCaller<TMessage>,
        WampCalleeError<TMessage>, WampCallerError<TMessage> {
    private WampClientCallee<TMessage> callee;
    private WampClientCaller<TMessage> caller;

    public DefaultWampRpcOperationCatalogProxy(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.callee = new WampClientCallee<TMessage>(proxy, formatter);
        this.caller = new WampClientCaller<TMessage>(proxy, formatter);
    }

    @Override
    public void invoke(WampRawRpcOperationCallback caller, Object options, String procedure) {
        this.caller.invoke(caller, options, procedure);
    }

    @Override
    public void invoke(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
        this.caller.invoke(caller, options, procedure, arguments);
    }

    @Override
    public void invoke(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        this.caller.invoke(caller, options, procedure, arguments, argumentsKeywords);
    }

    @Override
    public void result(long requestId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        caller.result(requestId, details, arguments, argumentsKeywords);
    }

    @Override
    public void result(long requestId, TMessage details, TMessage[] arguments) {
        caller.result(requestId, details, arguments);
    }

    @Override
    public void result(long requestId, TMessage details) {
        caller.result(requestId, details);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        this.caller.invoke(caller, options, procedure, arguments, argumentsKeywords);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
        this.caller.invoke(caller, options, procedure, arguments);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure) {
        this.caller.invoke(caller, options, procedure);
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
    public void registerError(long requestId, TMessage details, String error) {
        callee.registerError(requestId, details, error);
    }

    @Override
    public void registerError(long requestId, TMessage details, String error, TMessage[] arguments) {
        callee.registerError(requestId, details, error, arguments);
    }

    @Override
    public void registerError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        callee.registerError(requestId, details, error, arguments, argumentsKeywords);
    }

    @Override
    public void unregisterError(long requestId, TMessage details, String error) {
        callee.unregisterError(requestId, details, error);
    }

    @Override
    public void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments) {
        callee.unregisterError(requestId, details, error, arguments);
    }

    @Override
    public void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        callee.unregisterError(requestId, details, error, arguments, argumentsKeywords);
    }

    @Override
    public void callError(long requestId, TMessage details, String error) {
        caller.callError(requestId, details, error);
    }

    @Override
    public void callError(long requestId, TMessage details, String error, TMessage[] arguments) {
        caller.callError(requestId, details, error, arguments);
    }

    @Override
    public void callError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        caller.callError(requestId, details, error, arguments, argumentsKeywords);
    }
}