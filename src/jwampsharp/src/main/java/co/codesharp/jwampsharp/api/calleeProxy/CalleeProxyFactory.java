package co.codesharp.jwampsharp.api.calleeProxy;

import co.codesharp.jwampsharp.client.realm.WampRealmProxy;

import java.lang.reflect.Proxy;

/**
 * Created by Elad on 7/11/2014.
 */
public class CalleeProxyFactory {
    private final WampRealmProxy realmProxy;

    public CalleeProxyFactory(WampRealmProxy realmProxy){
        this.realmProxy = realmProxy;
    }


    public <TProxy> TProxy getCalleeProxy(Class<TProxy> proxyClass) {
        // TODO: check the current type is valid for proxy.
        TProxy result =
                (TProxy)
                Proxy.newProxyInstance(proxyClass.getClassLoader(),
                        new Class[]{proxyClass},
                        new CalleeInterceptor(this.realmProxy));

        return result;
    }
}