package co.codesharp.jwampsharp.api;

import co.codesharp.jwampsharp.api.calleeProxy.CalleeProxyFactory;
import co.codesharp.jwampsharp.api.rx.WampSubject;
import co.codesharp.jwampsharp.api.rx.WampTopicExtensions;
import co.codesharp.jwampsharp.client.pubSub.WampTopicProxy;
import co.codesharp.jwampsharp.client.realm.WampRealmProxy;
import rx.subjects.Subject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/12/2014.
 */
public class WampRealmProxyServiceProvider implements co.codesharp.jwampsharp.api.WampRealmServiceProvider {
    private WampRealmProxy proxy;
    private CalleeProxyFactory proxyFactory;

    public WampRealmProxyServiceProvider(WampRealmProxy realmProxy) {
        this.proxy = realmProxy;
        this.proxyFactory = new CalleeProxyFactory(realmProxy.getRpcCatalog());
    }

    @Override
    public CompletionStage registerCallee(Object instance) {
        throw new NotImplementedException();
    }

    @Override
    public <TProxy> TProxy getCalleeProxy(Class<TProxy> proxyClass) {
        return this.proxyFactory.getCalleeProxy(proxyClass);
    }

    @Override
    public <TEvent> Subject<TEvent, TEvent> GetSubject(Class<TEvent> eventClass, String topicUri) {
        WampTopicProxy topic = this.proxy.getTopicContainer().getTopic(topicUri);
        Subject<TEvent, TEvent> result = WampTopicExtensions.toSubject(eventClass, topic);
        return result;
    }

    @Override
    public WampSubject GetSubject(String topicUri) {
        WampTopicProxy topic = this.proxy.getTopicContainer().getTopic(topicUri);
        WampSubject subject = WampTopicExtensions.toSubject(topic);
        return subject;
    }
}