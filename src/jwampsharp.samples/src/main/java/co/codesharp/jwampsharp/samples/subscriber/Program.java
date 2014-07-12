package co.codesharp.jwampsharp.samples.subscriber;

import co.codesharp.jwampsharp.api.WampRealmServiceProvider;
import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.defaultBinding.DefaultWampChannelFactory;
import rx.Subscription;
import rx.subjects.Subject;
import rx.util.functions.Action1;

import java.net.URI;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elad on 7/13/2014.
 */
public class Program {
    public static void main(String[] args) {
        try {
            DefaultWampChannelFactory factory =
                    new DefaultWampChannelFactory();

            WampChannel channel =
                    factory.createJsonChannel(new URI("ws://127.0.0.1:8080/ws"), "realm1");

            CompletionStage open = channel.open();

            open.toCompletableFuture().get(5000, TimeUnit.MILLISECONDS);

            WampRealmServiceProvider services = channel.getRealmProxy().getServices();

            Subject<Integer, Integer> subject =
                    services.getSubject(Integer.class, "com.myapp.topic1");

            Subscription disposable = subject.subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer counter) {
                    System.out.println("Got " + counter);
                }
            });

            System.in.read();

            disposable.unsubscribe();

            System.in.read();

        } catch (Exception ex) {
            // Catch everything! :(
            System.out.println(ex);
        }
    }
}
