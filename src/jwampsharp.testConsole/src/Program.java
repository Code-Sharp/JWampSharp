import com.wampsharp.jwampsharp.client.rpc.WampRawRpcOperationCallback;
import com.wampsharp.jwampsharp.client.rpc.WampRpcOperationCatalogProxy;
import com.wampsharp.jwampsharp.defaultBinding.JsonNodeChannelFactory;
import com.wampsharp.jwampsharp.client.pubSub.WampRawTopicSubscriber;
import com.wampsharp.jwampsharp.client.realm.WampRealmProxy;
import com.wampsharp.jwampsharp.client.WampChannel;
import com.wampsharp.jwampsharp.core.serialization.WampFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import com.wampsharp.jwampsharp.defaultBinding.jsr.WebsocketWampTextConnection;
import com.wampsharp.jwampsharp.rpc.WampRpcOperation;
import com.wampsharp.jwampsharp.rpc.WampRpcOperationCallback;

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

            WampChannel<JsonNode> channel =
                    factory.createChannel("realm1", new WebsocketWampTextConnection<JsonNode>(new URI("ws://127.0.0.1:9090/ws"),
                    factory.getBinding()));


            CompletionStage open = channel.open();

            Object o =
                    open.toCompletableFuture().get();

            WampRealmProxy realmProxy = channel.getRealmProxy();

            /*
            realmProxy.getTopicContainer().getTopic("com.myapp.topic2")
                    .subscribe(new WampRawTopicSubscriber() {
                        @Override
                        public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details) {

                        }

                        @Override
                        public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments) {

                        }

                        @Override
                        public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {

                        }
                    },
                   new HashMap<String,String>());
*/
            WampRpcOperationCatalogProxy rpcCatalog =
                    realmProxy.getRpcCatalog();

            /*
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
                    */

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
                    "com.timeservice.now");


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