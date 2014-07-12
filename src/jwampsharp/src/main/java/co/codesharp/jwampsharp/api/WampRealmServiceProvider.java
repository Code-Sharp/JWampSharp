package co.codesharp.jwampsharp.api;

import co.codesharp.jwampsharp.api.rx.WampSubject;
import rx.subjects.Subject;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/11/2014.
 */
public interface WampRealmServiceProvider {
    // TODO: Add overloads for all options
    CompletionStage registerCallee(Object instance);

    <TProxy> TProxy getCalleeProxy(Class<TProxy> proxyClass);

    <TEvent> Subject<TEvent, TEvent> getSubject(Class<TEvent> eventClass, String topicUri);

    WampSubject getSubject(String topicUri);
}
