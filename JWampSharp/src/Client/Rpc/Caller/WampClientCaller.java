package Client.Rpc.Caller;

import Core.Contracts.Rpc.WampCaller;
import Core.Contracts.WampServerProxy;
import Core.WampIdMapper;
import Rpc.WampRpcOperationCallback;

/**
 * Created by Elad on 17/04/2014.
 */
public class WampClientCaller<TMessage> implements WampCaller<TMessage>, WampRpcOperationInvokerProxy {
    private final WampServerProxy proxy;
    private final WampIdMapper<CallDetails> pendingCalls = new WampIdMapper<CallDetails>();

    public WampClientCaller(WampServerProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure) {
        CallDetails callDetails = new CallDetails(caller, options, procedure);
        long requestId = registerCall(callDetails);
        proxy.call(requestId, options, procedure);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
        CallDetails callDetails = new CallDetails(caller, options, procedure, arguments);
        long requestId = registerCall(callDetails);
        proxy.call(requestId, options, procedure, arguments);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        CallDetails callDetails = new CallDetails(caller, options, procedure, arguments, argumentsKeywords);
        long requestId = registerCall(callDetails);
        proxy.call(requestId, options, procedure, arguments, argumentsKeywords);
    }

    @Override
    public void result(long requestId, TMessage details) {
        CallDetails callDetails = tryGetCallDetails(requestId);

        if (callDetails != null) {
            callDetails.getCaller().result(details);
        }
    }

    @Override
    public void result(long requestId, TMessage details, TMessage[] arguments) {
        CallDetails callDetails = tryGetCallDetails(requestId);

        if (callDetails != null) {
            callDetails.getCaller().result(details, arguments);
        }
    }

    @Override
    public void result(long requestId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        CallDetails callDetails = tryGetCallDetails(requestId);

        if (callDetails != null) {
            callDetails.getCaller().result(details, arguments, argumentsKeywords);
        }
    }

    private long registerCall(CallDetails callDetails) {
        long requestId = pendingCalls.add(callDetails);
        callDetails.setRequestId(requestId);
        return requestId;
    }

    private CallDetails tryGetCallDetails(long requestId) {
        return pendingCalls.remove(requestId);
    }

    private class CallDetails {
        private final WampRpcOperationCallback caller;
        private final Object options;
        private final String procedure;
        private final Object[] arguments;
        private final Object argumentsKeywords;
        private long requestId;

        public CallDetails(WampRpcOperationCallback caller, Object options, String procedure) {
            this(caller, options, procedure, null);
        }

        private CallDetails(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
            this(caller, options, procedure, arguments, null);
        }

        public CallDetails(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
            this.caller = caller;
            this.options = options;
            this.procedure = procedure;
            this.arguments = arguments;
            this.argumentsKeywords = argumentsKeywords;
        }

        public WampRpcOperationCallback getCaller() {
            return caller;
        }

        public Object getOptions() {
            return options;
        }

        public String getProcedure() {
            return procedure;
        }

        public Object[] getArguments() {
            return arguments;
        }

        public Object getArgumentsKeywords() {
            return argumentsKeywords;
        }

        public long getRequestId() {
            return requestId;
        }

        public void setRequestId(long requestId) {
            this.requestId = requestId;
        }
    }
}