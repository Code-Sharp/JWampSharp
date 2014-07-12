package co.codesharp.jwampsharp.samples.caller;

import co.codesharp.jwampsharp.api.WampRealmServiceProvider;
import co.codesharp.jwampsharp.api.calleeProxy.CalleeProxyFactory;
import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.defaultBinding.DefaultWampChannelFactory;
import co.codesharp.jwampsharp.samples.caller.contracts.ArgumentsService;
import co.codesharp.jwampsharp.samples.caller.manualProxy.ArgumentsServiceProxy;

import java.net.URI;
import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/11/2014.
 */
public class Program {
    public static void main(String[] args) {
        try {
            DefaultWampChannelFactory factory =
                    new DefaultWampChannelFactory();

            WampChannel channel =
                    factory.createJsonChannel(new URI("ws://127.0.0.1:8080/ws"), "realm1");

            CompletionStage open = channel.open();

            WampRealmServiceProvider services = channel.getRealmProxy().getServices();

            ArgumentsService proxy = services.getCalleeProxy(ArgumentsService.class);
            // Manual version:
            // ArgumentsServiceProxy proxy =
            //        new ArgumentsServiceProxy(channel.getRealmProxy());

            proxy.ping();
            System.out.println("Pinged!");

            int result = proxy.add2(2, 3);
            System.out.println(String.format("Add2: %s", result));

            String starred = proxy.stars("somebody", 0);
            System.out.println(String.format("Starred 1: %s", starred));

            starred = proxy.stars("Homer", 0);
            System.out.println(String.format("Starred 2: %s", starred));

            starred = proxy.stars("somebody", 5);
            System.out.println(String.format("Starred 3: %s", starred));

            starred = proxy.stars("Homer", 5);
            System.out.println(String.format("Starred 4: %s", starred));

            String[] orders = proxy.orders("coffee", 5);
            System.out.println(String.format("Orders 1: %s", String.join(", ", orders)));

            orders = proxy.orders("coffee", 10);
            System.out.println(String.format("Orders 2: %s", String.join(", ", orders)));

            System.in.read();

        } catch (Exception ex) {
            // Catch everything! :(
            System.out.println(ex);
        }
    }
}