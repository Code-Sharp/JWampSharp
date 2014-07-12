package co.codesharp.jwampsharp.samples.caller.manualProxy;

import co.codesharp.jwampsharp.client.ErrorExtractor;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import co.codesharp.jwampsharp.client.rpc.WampRawRpcOperationCallback;
import co.codesharp.jwampsharp.core.contracts.WampException;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.samples.caller.contracts.ArgumentsService;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * Created by Elad on 7/11/2014.
 */
public class ArgumentsServiceProxy implements ArgumentsService {
    private WampRealmProxy realmProxy;
    private HashMap<String, Object> dummy = new HashMap<String, Object>();

    public ArgumentsServiceProxy(WampRealmProxy realmProxy) {
        this.realmProxy = realmProxy;
    }

    @Override
    public void ping() {
        try {
            CompletionStage task = pingAsync();
            task.toCompletableFuture().get();
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

    @Override
    public int add2(int a, int b) {
        try {
            CompletionStage<Integer> task = add2Async(a, b);
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

    @Override
    public String stars(String nick, int stars) {
        try {
            CompletionStage<String> task = starsAsync(nick, stars);
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

    @Override
    public String[] orders(String product, int limit) {
        try {
            CompletionStage<String[]> task = ordersAsync(product, limit);
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

    @Override
    public CompletionStage pingAsync() {
        PingCallback callback = new PingCallback();
        realmProxy.getRpcCatalog().invoke(callback, dummy, "com.arguments.ping");
        return callback.getTask();
    }

    @Override
    public CompletionStage<Integer> add2Async(int a, int b) {
        Add2Callback callback = new Add2Callback();
        realmProxy.getRpcCatalog().invoke(callback, dummy, "com.arguments.add2",
                new Object[]{a, b});
        return callback.getTask();
    }

    @Override
    public CompletionStage<String> starsAsync(String nick, int stars) {
        StarsCallback callback = new StarsCallback();
        realmProxy.getRpcCatalog().invoke(callback, dummy, "com.arguments.stars",
                new Object[]{nick, stars});
        return callback.getTask();
    }

    @Override
    public CompletionStage<String[]> ordersAsync(String product, int limit) {
        OrdersCallback callback = new OrdersCallback();
        realmProxy.getRpcCatalog().invoke(callback, dummy, "com.arguments.orders",
                new Object[]{product, limit});
        return callback.getTask();
    }

    private abstract class Callback<T> implements WampRawRpcOperationCallback {
        private final CompletableFuture<T> task = new CompletableFuture<T>();

        public CompletionStage<T> getTask() {
            return task;
        }

        protected void setResult(T result) {
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

    private class PingCallback extends Callback<Void> {

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {
            this.setResult(null);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            this.setResult(null);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
            this.setResult(null);
        }
    }

    private class Add2Callback extends Callback<Integer> {

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {

        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            Integer result = formatter.deserialize(int.class, arguments[0]);
            this.setResult(result);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {

        }
    }

    private class StarsCallback extends Callback<String> {
        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {

        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            String result = formatter.deserialize(String.class, arguments[0]);
            this.setResult(result);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {

        }
    }

    private class OrdersCallback extends Callback<String[]> {
        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {

        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            String[] result = new String[arguments.length];

            for (int i = 0; i < arguments.length; i++) {
                String current = formatter.deserialize(String.class, arguments[i]);
                result[i] = current;
            }

            this.setResult(result);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {

        }
    }
}