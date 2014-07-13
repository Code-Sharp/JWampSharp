package co.codesharp.jwampsharp.client.callee;

import co.codesharp.jwampsharp.api.client.WampChannel;
import co.codesharp.jwampsharp.api.serialization.SerializedValue;
import co.codesharp.jwampsharp.api.serialization.SerializedValueImpl;
import co.codesharp.jwampsharp.core.binding.WampBinding;
import co.codesharp.jwampsharp.core.contracts.WampClientProxy;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.defaultBinding.JsonNodeBinding;
import co.codesharp.jwampsharp.rpc.WampRpcOperation;
import co.codesharp.jwampsharp.rpc.WampRpcOperationCallback;
import co.codesharp.jwampsharp.testhelpers.ServerMockBase;
import co.codesharp.jwampsharp.testhelpers.WampClientPlayground;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 7/13/2014.
 */
public class CalleeTests {
    @Test
    public void testRegisterOperation() throws Exception {
        WampClientPlayground playground =
                new WampClientPlayground();

        JsonNodeBinding binding = new JsonNodeBinding();
        DealerMock serverMock = new DealerMock(10879, binding);

        WampChannel channel =
                playground.getChannel(serverMock, "realm1", binding);

        channel.open().toCompletableFuture().get();

        String procedure = "my.test.procedure";

        CompletionStage registration = channel.getRealmProxy().getRpcCatalog()
                .register(new WampOperationMock(procedure),
                        new HashMap<String, Object>());

        Assert.assertEquals(procedure,
                serverMock.getRegisterProcedure());

        Assert.assertEquals(false,
                registration.toCompletableFuture().isDone());

        WampClientProxy client = serverMock.getClient();

        client.registered(serverMock.getRegisterRequestId(), 163);

        Assert.assertEquals(true,
                registration.toCompletableFuture().isDone());
    }

    @Test
    public void testInvokeOperation() throws Exception {
        WampClientPlayground playground =
                new WampClientPlayground();

        JsonNodeBinding binding = new JsonNodeBinding();
        DealerMock serverMock = new DealerMock(10879, binding);

        WampChannel channel =
                playground.getChannel(serverMock, "realm1", binding);

        channel.open().toCompletableFuture().get();

        WampOperationMock operation = new WampOperationMock("my.test.procedure");

        CompletionStage registration = channel.getRealmProxy().getRpcCatalog()
                .register(operation,
                        new HashMap<String, Object>());

        WampClientProxy client = serverMock.getClient();

        client.registered(serverMock.getRegisterRequestId(), 163);

        HashMap<String, Object> argumentsKeywords = new HashMap<String, Object>();
        argumentsKeywords.put("nick","Homer");
        argumentsKeywords.put("stars",5);

        client.invocation(2574161, 163, new HashMap<String, Object>(),
                new Object[]{"this is a string", 89},
                argumentsKeywords);

        SerializedValue[] arguments = operation.getArguments();
        Assert.assertEquals(2, arguments.length);
        Assert.assertEquals("this is a string", arguments[0].deserialize(String.class));
        Assert.assertEquals(89, (int)arguments[1].deserialize(Integer.class));

        Map<String, Object> recievedArgumentsKeywords =
                operation.getArgumentsKeywords().deserialize(Map.class);

        Assert.assertEquals("Homer", recievedArgumentsKeywords.get("nick"));
        Assert.assertEquals(5, recievedArgumentsKeywords.get("stars"));
    }

    @Test
    public void testInvokeOperationYield() throws Exception {
        WampClientPlayground playground =
                new WampClientPlayground();

        JsonNodeBinding binding = new JsonNodeBinding();
        DealerMock serverMock = new DealerMock(10879, binding);

        WampChannel channel =
                playground.getChannel(serverMock, "realm1", binding);

        channel.open().toCompletableFuture().get();

        WampOperationMock operation = new WampOperationMock("my.test.procedure");

        CompletionStage registration = channel.getRealmProxy().getRpcCatalog()
                .register(operation,
                        new HashMap<String, Object>());

        WampClientProxy client = serverMock.getClient();

        client.registered(serverMock.getRegisterRequestId(), 163);

        HashMap<String, Object> argumentsKeywords = new HashMap<String, Object>();
        argumentsKeywords.put("nick","Homer");
        argumentsKeywords.put("stars",5);

        client.invocation(2574161, 163, new HashMap<String, Object>(),
                new Object[]{"this is a string", 89},
                argumentsKeywords);

        WampRpcOperationCallback caller = operation.getCaller();

        HashMap<String, Object> resultArgumentsKeywords = new HashMap<String, Object>();
        resultArgumentsKeywords.put("term", "yo yo yo");

        caller.result(new HashMap<String, Object>(),
                new Object[]{"Homer starred x5", 5},
                resultArgumentsKeywords);

        Assert.assertEquals(2574161, serverMock.getYieldRequestId());

        SerializedValue[] yieldArguments = serverMock.getYieldArguments();
        Assert.assertEquals("Homer starred x5", yieldArguments[0].deserialize(String.class));
        Assert.assertEquals(5, (int)yieldArguments[1].deserialize(Integer.class));

        Map<String, Object> deserializedYieldArguments =
                serverMock.getYieldArgumentsKeywords().deserialize(Map.class);
        Assert.assertEquals("yo yo yo", deserializedYieldArguments.get("term"));
    }

    class DealerMock<TMessage> extends ServerMockBase<TMessage>{
        private final WampFormatter<TMessage> formatter;
        private WampClientProxy client;
        private long registerRequestId;
        private SerializedValue yieldOptions;
        private SerializedValue[] yieldArguments;
        private SerializedValue yieldArgumentsKeywords;
        private String regiserProcedure;
        private long yieldRequestId;

        protected DealerMock(long sessionId, WampBinding<TMessage> binding) {
            super(sessionId);
            this.formatter = binding.getFormatter();
        }

        @Override
        public void publish(WampClientProxy client, long requestId, TMessage options, String topicUri) {

        }

        @Override
        public void publish(WampClientProxy client, long requestId, TMessage options, String topicUri, TMessage[] arguments) {

        }

        @Override
        public void publish(WampClientProxy client, long requestId, TMessage options, String topicUri, TMessage[] arguments, TMessage argumentKeywords) {

        }

        @Override
        public void subscribe(WampClientProxy client, long requestId, TMessage options, String topicUri) {

        }

        @Override
        public void unsubscribe(WampClientProxy client, long requestId, long subscriptionId) {

        }

        @Override
        public void register(WampClientProxy client, long requestId, TMessage options, String procedure) {

            this.client = client;
            this.registerRequestId = requestId;
            this.regiserProcedure = procedure;
        }

        @Override
        public void unregister(WampClientProxy client, long requestId, long registrationId) {

        }

        @Override
        public void call(WampClientProxy client, long requestId, TMessage options, String procedure) {

        }

        @Override
        public void call(WampClientProxy client, long requestId, TMessage options, String procedure, TMessage[] arguments) {

        }

        @Override
        public void call(WampClientProxy client, long requestId, TMessage options, String procedure, TMessage[] arguments, TMessage argumentsKeywords) {

        }

        @Override
        public void cancel(WampClientProxy client, long requestId, TMessage options) {

        }

        @Override
        public void yield(WampClientProxy client, long requestId, TMessage options) {
            this.client = client;
            this.yieldRequestId = requestId;
            this.yieldOptions = new SerializedValueImpl<TMessage>(formatter, options);
            this.yieldArguments = new SerializedValue[yieldArguments.length];
        }

        @Override
        public void yield(WampClientProxy client, long requestId, TMessage options, TMessage[] arguments) {
            this.client = client;
            this.yieldRequestId = requestId;
            this.yieldOptions = new SerializedValueImpl<TMessage>(formatter, options);
            this.yieldArguments = new SerializedValue[arguments.length];

            for (int i=0; i< arguments.length; i++){
                this.yieldArguments[i] = new SerializedValueImpl<TMessage>(formatter, arguments[i]);
            }
        }

        @Override
        public void yield(WampClientProxy client, long requestId, TMessage options, TMessage[] arguments, TMessage argumentsKeywords) {

            this.client = client;
            this.yieldRequestId = requestId;
            this.yieldOptions = new SerializedValueImpl<TMessage>(formatter, options);
            this.yieldArguments = new SerializedValue[arguments.length];

            for (int i=0; i<arguments.length; i++){
                this.yieldArguments[i] = new SerializedValueImpl<TMessage>(formatter, arguments[i]);
            }

            this.yieldArgumentsKeywords = new SerializedValueImpl<TMessage>(formatter, argumentsKeywords);
        }

        @Override
        public void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error) {

        }

        @Override
        public void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error, TMessage[] arguments) {

        }

        @Override
        public void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {

        }

        @Override
        public void abort(WampClientProxy client, TMessage details, String reason) {

        }

        @Override
        public void authenticate(WampClientProxy client, String signature, TMessage extra) {

        }

        @Override
        public void goodbye(WampClientProxy client, TMessage details, String reason) {

        }

        @Override
        public void heartbeat(WampClientProxy client, int incomingSeq, int outgoingSeq) {

        }

        @Override
        public void heartbeat(WampClientProxy client, int incomingSeq, int outgoingSeq, String discard) {

        }

        public WampClientProxy getClient() {
            return client;
        }

        public long getRegisterRequestId() {
            return registerRequestId;
        }

        public String getRegisterProcedure() {
            return regiserProcedure;
        }

        public SerializedValue getYieldOptions() {
            return yieldOptions;
        }

        public SerializedValue[] getYieldArguments() {
            return yieldArguments;
        }

        public SerializedValue getYieldArgumentsKeywords() {
            return yieldArgumentsKeywords;
        }

        public long getYieldRequestId() {
            return yieldRequestId;
        }
    }

    class WampOperationMock implements WampRpcOperation{

        private String procedure;
        private WampRpcOperationCallback caller;
        private SerializedValue details;
        private SerializedValue[] arguments;
        private SerializedValue argumentsKeywords;

        WampOperationMock(String procedure) {
            this.procedure = procedure;
        }

        @Override
        public String getProcedure() {
            return procedure;
        }

        @Override
        public <TMessage> void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage details) {
            this.caller = caller;
            this.details = new SerializedValueImpl<TMessage>(formatter,details);
        }

        @Override
        public <TMessage> void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage options, TMessage[] arguments) {
            this.caller = caller;
            this.details = new SerializedValueImpl<TMessage>(formatter, options);
            this.arguments = new SerializedValue[arguments.length];

            for (int i=0; i<arguments.length; i++){
                this.arguments[i] = new SerializedValueImpl<TMessage>(formatter, arguments[i]);
            }
        }

        @Override
        public <TMessage> void invoke(WampRpcOperationCallback caller, WampFormatter<TMessage> formatter, TMessage options, TMessage[] arguments, TMessage argumentsKeywords) {
            this.caller = caller;
            this.details = new SerializedValueImpl<TMessage>(formatter, options);
            this.arguments = new SerializedValue[arguments.length];

            for (int i=0; i<arguments.length; i++){
                this.arguments[i] = new SerializedValueImpl<TMessage>(formatter, arguments[i]);
            }

            this.argumentsKeywords = new SerializedValueImpl<TMessage>(formatter, argumentsKeywords);
        }

        public WampRpcOperationCallback getCaller() {
            return caller;
        }

        public SerializedValue getDetails() {
            return details;
        }

        public SerializedValue getArgumentsKeywords() {
            return argumentsKeywords;
        }

        public SerializedValue[] getArguments() {
            return arguments;
        }
    }
}