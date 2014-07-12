package co.codesharp.jwampsharp.api.calleeProxy;

import co.codesharp.jwampsharp.client.rpc.WampRpcOperationCatalogProxy;

import java.lang.reflect.Proxy;

/**
 * Created by Elad on 7/11/2014.
 */
public class CalleeProxyFactory {
    private final WampRpcOperationCatalogProxy catalogProxy;

    public CalleeProxyFactory(WampRpcOperationCatalogProxy catalogProxy){
        this.catalogProxy = catalogProxy;
    }


    public <TProxy> TProxy getCalleeProxy(Class<TProxy> proxyClass) {
        // TODO: check the current type is valid for proxy.
        TProxy result =
                (TProxy)
                Proxy.newProxyInstance(proxyClass.getClassLoader(),
                        new Class[]{proxyClass},
                        new CalleeInterceptor(this.catalogProxy));

        return result;
    }
}