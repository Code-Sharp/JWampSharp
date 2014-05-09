package com.wampsharp.jwampsharp.client.rpc;

import com.wampsharp.jwampsharp.client.rpc.Callee.WampClientCallee;
import com.wampsharp.jwampsharp.client.rpc.Caller.WampClientCaller;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCallee;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCaller;
import com.wampsharp.jwampsharp.core.contracts.WampServerProxy;
import com.wampsharp.jwampsharp.core.serialization.WampFormatter;
import com.wampsharp.jwampsharp.rpc.WampRpcOperation;
import com.wampsharp.jwampsharp.rpc.WampRpcOperationCallback;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampRpcOperationCatalogProxy<TMessage> implements WampRpcOperationCatalogProxy, WampCallee<TMessage>, WampCaller<TMessage> {
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

    private WampClientCallee<TMessage> callee;

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

    private WampClientCaller<TMessage> caller;

    public DefaultWampRpcOperationCatalogProxy(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.callee = new WampClientCallee<TMessage>(proxy, formatter);
        this.caller = new WampClientCaller<TMessage>(proxy, formatter);
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
}