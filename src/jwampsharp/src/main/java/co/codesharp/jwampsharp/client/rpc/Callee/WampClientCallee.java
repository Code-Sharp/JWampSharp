package co.codesharp.jwampsharp.client.rpc.Callee;

import co.codesharp.jwampsharp.core.WampIdMapper;
import co.codesharp.jwampsharp.core.contracts.WampServerProxy;
import co.codesharp.jwampsharp.core.contracts.error.WampCalleeError;
import co.codesharp.jwampsharp.core.contracts.rpc.WampCallee;
import co.codesharp.jwampsharp.core.message.WampMessageType;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.rpc.WampRpcOperation;
import co.codesharp.jwampsharp.rpc.WampRpcOperationCallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Elad on 16/04/2014.
 */
public class WampClientCallee<TMessage> implements WampCallee<TMessage>, WampCalleeError<TMessage>, WampRpcOperationRegistrationProxy {

    private final WampServerProxy proxy;
    private final WampFormatter<TMessage> formatter;

    private final WampIdMapper<Request> pendingRegistrations = new WampIdMapper<Request>();
    private final WampIdMapper<Request> pendingUnregistrations = new WampIdMapper<Request>();

    private final ConcurrentHashMap<Long, WampRpcOperation> registrations =
            new ConcurrentHashMap<Long, WampRpcOperation>();

    private final ConcurrentHashMap<WampRpcOperation, Long> operationToRegistrationId =
            new ConcurrentHashMap<WampRpcOperation, Long>();


    public WampClientCallee(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.proxy = proxy;
        this.formatter = formatter;
    }

    @Override
    public CompletionStage register(WampRpcOperation operation, Object options) {
        Request request = new Request(operation);
        long id = pendingRegistrations.add(request);
        request.setRequestId(id);
        proxy.register(id, options, operation.getProcedure());
        return request.getCompletionStage();
    }

    @Override
    public void registered(long requestId, long registrationId) {
        Request request = pendingRegistrations.remove(requestId);

        if (request != null)
        {
            this.registrations.put(registrationId, request.getOperation());
            this.operationToRegistrationId.put(request.getOperation(), registrationId);
            request.complete();
        }
    }

    @Override
    public CompletionStage unregister(WampRpcOperation operation) {
        Request request = new Request(operation);

        Long registrationId =
                this.operationToRegistrationId.getOrDefault(operation, null);

        if (registrationId == null) {
            return null;
        } else {
            long requestId = pendingRegistrations.add(request);
            request.setRequestId(requestId);
            proxy.unregister(requestId, registrationId);
            return request.getCompletionStage();
        }
    }

    @Override
    public void unregistered(long requestId) {
        Request request = pendingUnregistrations.remove(requestId);

        if (request != null) {
            registrations.remove(requestId);
            operationToRegistrationId.remove(request.getOperation());
            request.complete();
        }
    }

    @Override
    public void invocation(long requestId, long registrationId, TMessage details) {
        WampRpcOperation operation = tryGetOperation(registrationId);

        if (operation != null){
            WampRpcOperationCallback callback = getCallback(requestId);
            operation.invoke(callback, this.formatter, details);
        }
    }

    @Override
    public void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments) {
        WampRpcOperation operation = tryGetOperation(registrationId);

        if (operation != null){
            WampRpcOperationCallback callback = getCallback(requestId);
            operation.invoke(callback, this.formatter, details, arguments);
        }
    }

    @Override
    public void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        WampRpcOperation operation = tryGetOperation(registrationId);

        if (operation != null){
            WampRpcOperationCallback callback = getCallback(requestId);
            operation.invoke(callback, this.formatter, details, arguments, argumentsKeywords);
        }
    }

    private WampRpcOperationCallback getCallback(long requestId) {
        return new ServerProxyCallback(proxy, requestId);
    }

    private WampRpcOperation tryGetOperation(long registrationId) {
        return registrations.getOrDefault(registrationId, null);
    }


    @Override
    public void interrupt(long requestId, TMessage options) {

    }

    @Override
    public void registerError(long requestId, TMessage details, String error) {

    }

    @Override
    public void registerError(long requestId, TMessage details, String error, TMessage[] arguments) {

    }

    @Override
    public void registerError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {

    }

    @Override
    public void unregisterError(long requestId, TMessage details, String error) {

    }

    @Override
    public void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments) {

    }

    @Override
    public void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {

    }

    private class Request {
        private final CompletableFuture<Boolean> completableFuture =
                new CompletableFuture<Boolean>();

        private final WampRpcOperation operation;

        private long requestId;

        private Request(WampRpcOperation operation) {
            this.operation = operation;
        }

        public long getRequestId() {
            return requestId;
        }

        public void setRequestId(long requestId) {
            this.requestId = requestId;
        }

        public CompletionStage<Boolean> getCompletionStage() {
            return completableFuture;
        }

        public void complete() {
            completableFuture.complete(true);
        }

        public void error() {
        }

        public WampRpcOperation getOperation() {
            return operation;
        }
    }

    private class ServerProxyCallback implements WampRpcOperationCallback {
        private final WampServerProxy proxy;
        private final long requestId;

        public long getRequestId() {
            return requestId;
        }

        public WampServerProxy getProxy() {
            return proxy;
        }

        private ServerProxyCallback(WampServerProxy proxy, long requestId) {
            this.proxy = proxy;
            this.requestId = requestId;
        }

        @Override
        public void result(Object details) {
            getProxy().yield(getRequestId(), details);
        }

        @Override
        public void result(Object details, Object[] arguments) {
            getProxy().yield(getRequestId(), details, arguments);
        }

        @Override
        public void result(Object details, Object[] arguments, Object argumentsKeywords) {
            getProxy().yield(getRequestId(), details, arguments, argumentsKeywords);
        }

        @Override
        public void error(Object details, String error) {
            getProxy().error(WampMessageType.INVOCATION, getRequestId(), details, error);
        }

        @Override
        public void error(Object details, String error, Object[] arguments) {
            getProxy().error(WampMessageType.INVOCATION, getRequestId(), details, error, arguments);
        }

        @Override
        public void error(Object details, String error, Object[] arguments, Object argumentsKeywords) {
            getProxy().error(WampMessageType.INVOCATION, getRequestId(), details, error, arguments, argumentsKeywords);
        }
    }
}