import Client.Rpc.WampRawRpcOperationCallback;
import Client.Rpc.WampRpcOperationCatalogProxy;
import Client.WampChannel;
import Core.Serialization.WampFormatter;
import Rpc.WampRpcOperation;
import Rpc.WampRpcOperationCallback;
import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 15/04/2014.
 */
public class Program {
    public static void main(String[] args) {

        try {
            JsonNodeChannelFactory factory = new JsonNodeChannelFactory();

            WampChannel<JsonNode> channel = factory.createChannel("realm1", new WebsocketWampTextConnection<JsonNode>(new URI("ws://localhost:9090/ws"),
                    factory.getBinding()));


            CompletionStage open = channel.open();

            Object o =
                    open.toCompletableFuture().get();

            WampRpcOperationCatalogProxy rpcCatalog =
                    channel.getRealmProxy().getRpcCatalog();

            CompletionStage register = rpcCatalog.register(new WampRpcOperation() {
                                                               @Override
                                                               public String getProcedure() {
                                                                   return "com.arguments.add2";
                                                               }

                                                               @Override
                                                               public <TMessage> void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage details) {
                                                               }

                                                               @Override
                                                               public <TMessage> void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage options, TMessage[] arguments) {
                                                                   int number1 = formatter.deserialize(Integer.class, arguments[0]);
                                                                   int number2 = formatter.deserialize(Integer.class, arguments[1]);
                                                                   caller.result(new HashMap<String, String>(), new Object[]{number1 + number2});
                                                               }

                                                               @Override
                                                               public <TMessage> void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage options, TMessage[] arguments, TMessage argumentsKeywords) {

                                                               }
                                                           },
                    new HashMap<String, String>()
            );

            Object result =
                    register.toCompletableFuture().get();

            rpcCatalog.invoke(new WampRawRpcOperationCallback() {
                                  @Override
                                  public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {

                                  }

                                  @Override
                                  public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {

                                  }

                                  @Override
                                  public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {

                                  }

                                  @Override
                                  public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error) {

                                  }

                                  @Override
                                  public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments) {

                                  }

                                  @Override
                                  public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {

                                  }
                              }, new HashMap<String, String>(),
                    "com.arguments.add2",
                    new Object[]{2, 4}
            );

        } catch (Exception ex) {
            System.out.println(ex);
        }

        while (true) {
            try {
                System.in.read();
            } catch (Exception ex) {

            }
        }
    }
}