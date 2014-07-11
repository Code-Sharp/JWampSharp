package co.codesharp.jwampsharp.samples.caller;

import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.defaultBinding.DefaultWampChannelFactory;
import co.codesharp.jwampsharp.samples.caller.maunalProxy.ArgumentsServiceProxy;

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

            open.toCompletableFuture().get();

            ArgumentsServiceProxy proxy =
                    new ArgumentsServiceProxy(channel.getRealmProxy());

            proxy.ping();
            int number = proxy.add2(9, 10);
            String stars = proxy.stars("Homer", 5);
            String[] books = proxy.orders("Book", 4);

        } catch (Exception ex) {
            // Catch everything! :(
            System.out.println(ex);
        }
    }
}