package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.core.contracts.WampException;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 24/05/2014.
 */
public class WampPendingRequest<TMessage, TResult> {
    private final CompletableFuture<TResult> completionStage = new CompletableFuture<TResult>();
    private long requestId;
    private final WampFormatter<TMessage> formatter;

    public WampPendingRequest(WampFormatter<TMessage> formatter) {
        this.formatter = formatter;
    }

    public CompletionStage<TResult> getCompletionStage() {
        return completionStage;
    }

    public void complete(TResult result){
        this.completionStage.complete(result);
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    // TODO: Don't repeat yourself
    public void error(TMessage details, String error) {
        setError(ErrorExtractor.error(formatter, details, error));
    }

    public void error(TMessage details, String error, TMessage[] arguments) {
        setError(ErrorExtractor.error(formatter, details, error, arguments));
    }

    public void error(TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        setError(ErrorExtractor.error(formatter, details, error, arguments, argumentsKeywords));
    }

    private Map<String, Object> deserializeMap(TMessage details) {
        Map result = formatter.deserialize(Map.class, details);
        return result;
    }

    private void setError(WampException exception) {
        this.completionStage.completeExceptionally(exception);
    }
}