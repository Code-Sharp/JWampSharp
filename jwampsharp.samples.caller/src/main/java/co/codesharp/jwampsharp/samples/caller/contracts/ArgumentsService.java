package co.codesharp.jwampsharp.samples.caller.contracts;

import co.codesharp.jwampsharp.rpc.WampProcedure;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/11/2014.
 */
public interface ArgumentsService {
    @WampProcedure("com.arguments.ping")
    void ping();

    @WampProcedure("com.arguments.add2")
    int add2(int a, int b);

    @WampProcedure("com.arguments.stars")
    String stars(String nick, int stars);

    @WampProcedure("com.arguments.orders")
    String[] orders(String product, int limit);

    @WampProcedure("com.arguments.ping")
    CompletionStage pingAsync();

    @WampProcedure("com.arguments.add2")
    CompletionStage<Integer> add2Async(int a, int b);

    @WampProcedure("com.arguments.stars")
    CompletionStage<String> starsAsync(String nick, int stars);

    @WampProcedure("com.arguments.orders")
    CompletionStage<String[]> ordersAsync(String product, int limit);
}