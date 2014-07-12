package co.codesharp.jwampsharp.api.calleeProxy;

import co.codesharp.jwampsharp.client.ErrorExtractor;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import co.codesharp.jwampsharp.client.rpc.WampRawRpcOperationCallback;
import co.codesharp.jwampsharp.core.contracts.WampException;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.rpc.CollectionResultTreatment;
import co.codesharp.jwampsharp.rpc.WampProcedure;
import co.codesharp.jwampsharp.rpc.WampResult;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * Created by Elad on 7/11/2014.
 */
public class CalleeInterceptor implements InvocationHandler {

    private final WampRealmProxy proxy;
    private HashMap<String, Object> emptyOptions = new HashMap<String, Object>();

    public CalleeInterceptor(WampRealmProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getReturnType() == CompletionStage.class) {
            return handleAsync(method, args);
        } else {
            return handle(method, args);
        }
    }

    public Object handle(Method method, Object[] args) {
        try {
            CompletionStage task =
                    innerHandleAsync(method,
                            args,
                            method.getReturnType());

            return task.toCompletableFuture().get();
        } catch (ExecutionException ex) {
            Throwable cause = ex.getCause();

            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new RuntimeException(cause.getMessage(), cause);
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Object handleAsync(Method method, Object[] args) {
        Type taskType = method.getGenericReturnType();
        Class<?> returnType = getTaskGenericParameterType(taskType);

        return innerHandleAsync(method, args, returnType);
    }

    private Class<?> getTaskGenericParameterType(Type taskType) {
        Class<?> result = (Class<?>) ((ParameterizedType) taskType).getActualTypeArguments()[0];
        return result;
    }

    private CompletionStage<?> innerHandleAsync(Method method, Object[] args, Class<?> returnType) {
        Callback callback;

        if (hasMultivaluedResult(method, returnType)) {
            callback = new MultiValueCallback(returnType);
        } else {
            callback = new SingleValueCallback(returnType);
        }

        WampProcedure annotation = method.getAnnotation(WampProcedure.class);
        String procedureUri = annotation.value();

        this.proxy.getRpcCatalog().invoke(callback,
                emptyOptions,
                procedureUri,
                args);

        return callback.getTask();
    }

    private boolean hasMultivaluedResult(Method method, Class<?> returnType) {
        if (!returnType.isArray()) {
            return false;
        }

        WampResult resultType = method.getAnnotation(WampResult.class);

        if ((resultType != null) &&
            (resultType.value() == CollectionResultTreatment.MULTIVALUED)) {
            return true;
        }

        return false;
    }

    private abstract class Callback implements WampRawRpcOperationCallback {
        private final CompletableFuture<Object> task = new CompletableFuture<Object>();

        public CompletionStage<Object> getTask() {
            return task;
        }

        protected void setResult(Object result) {
            task.complete(result);
        }

        @Override
        public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error) {
            WampException exception = ErrorExtractor.error(formatter, details, error);
            task.completeExceptionally(exception);
        }

        @Override
        public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments) {
            WampException exception = ErrorExtractor.error(formatter, details, error, arguments);
            task.completeExceptionally(exception);
        }

        @Override
        public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
            WampException exception = ErrorExtractor.error(formatter, details, error, arguments, argumentsKeywords);
            task.completeExceptionally(exception);
        }
    }

    public class SingleValueCallback extends Callback {
        private Class<?> returnType;

        public SingleValueCallback(Class<?> returnType) {
            this.returnType = returnType;
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {
            this.setResult(null);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            setResult(formatter, arguments);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
            setResult(formatter, arguments);
        }

        private <TMessage> void setResult(WampFormatter<TMessage> formatter, TMessage[] arguments) {
            Object result = formatter.deserialize(this.returnType, arguments[0]);

            this.setResult(result);
        }
    }

    public class MultiValueCallback extends Callback {
        private final Class<?> arrayType;
        private final Class<?> returnType;

        public MultiValueCallback(Class<?> returnType) {
            this.returnType = returnType;
            this.arrayType = extractCollectionType(returnType);
        }

        private Class<?> extractCollectionType(Class<?> collectionType) {
            return collectionType.getComponentType();
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {
            this.setResult(null);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            setResult(formatter, arguments);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
            setResult(formatter, arguments);
        }

        private <TMessage> void setResult(WampFormatter<TMessage> formatter, TMessage[] arguments) {
            Object result = Array.newInstance(arrayType, arguments.length);

            for (int i = 0; i < arguments.length; i++) {
                Object current = formatter.deserialize(arrayType, arguments[i]);
                Array.set(result, i ,current);
            }

            this.setResult(result);
        }
    }
}