package Client.Rpc.Caller;

import Client.Rpc.WampRawRpcOperationCallback;
import Core.Contracts.Rpc.WampCaller;
import Core.Contracts.WampServerProxy;
import Core.Serialization.WampFormatter;
import Core.WampIdMapper;
import Rpc.WampRpcOperationCallback;

/**
 * Created by Elad on 17/04/2014.
 */
public class WampClientCaller<TMessage> implements WampCaller<TMessage>, WampRpcOperationInvokerProxy {
    private final WampServerProxy proxy;
    private final WampIdMapper<CallDetails> pendingCalls = new WampIdMapper<CallDetails>();
    private final WampFormatter<TMessage> formatter;

    public WampClientCaller(WampServerProxy proxy, WampFormatter<TMessage> formatter) {
        this.proxy = proxy;
        this.formatter = formatter;
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure) {
        RawCallbackAdpater adpater = new RawCallbackAdpater(caller);
        invoke(adpater, options, procedure);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
        RawCallbackAdpater adpater = new RawCallbackAdpater(caller);
        invoke(adpater, options, procedure, arguments);
    }

    @Override
    public void invoke(WampRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        RawCallbackAdpater adpater = new RawCallbackAdpater(caller);
        invoke(adpater, options, procedure, arguments, argumentsKeywords);
    }

    @Override
    public void invoke(WampRawRpcOperationCallback caller, Object options, String procedure) {
        CallDetails callDetails = new CallDetails(caller, options, procedure);
        long requestId = registerCall(callDetails);
        proxy.call(requestId, options, procedure);
    }

    @Override
    public void invoke(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
        CallDetails callDetails = new CallDetails(caller, options, procedure, arguments);
        long requestId = registerCall(callDetails);
        proxy.call(requestId, options, procedure, arguments);
    }

    @Override
    public void invoke(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        CallDetails callDetails = new CallDetails(caller, options, procedure, arguments, argumentsKeywords);
        long requestId = registerCall(callDetails);
        proxy.call(requestId, options, procedure, arguments, argumentsKeywords);
    }

    @Override
    public void result(long requestId, TMessage details) {
        CallDetails callDetails = tryGetCallDetails(requestId);

        if (callDetails != null) {
            callDetails.getCaller().result(this.getFormatter(), details);
        }
    }

    @Override
    public void result(long requestId, TMessage details, TMessage[] arguments) {
        CallDetails callDetails = tryGetCallDetails(requestId);

        if (callDetails != null) {
            callDetails.getCaller().result(this.getFormatter(), details, arguments);
        }
    }

    @Override
    public void result(long requestId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        CallDetails callDetails = tryGetCallDetails(requestId);

        if (callDetails != null) {
            callDetails.getCaller().result(this.getFormatter(), details, arguments, argumentsKeywords);
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

    private WampFormatter<TMessage> getFormatter() {
        return formatter;
    }

    private class CallDetails {
        private final WampRawRpcOperationCallback caller;
        private final Object options;
        private final String procedure;
        private final Object[] arguments;
        private final Object argumentsKeywords;
        private long requestId;

        public CallDetails(WampRawRpcOperationCallback caller, Object options, String procedure) {
            this(caller, options, procedure, null);
        }

        private CallDetails(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments) {
            this(caller, options, procedure, arguments, null);
        }

        public CallDetails(WampRawRpcOperationCallback caller, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
            this.caller = caller;
            this.options = options;
            this.procedure = procedure;
            this.arguments = arguments;
            this.argumentsKeywords = argumentsKeywords;
        }

        public WampRawRpcOperationCallback getCaller() {
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

    private class RawCallbackAdpater implements WampRawRpcOperationCallback {
        private final WampRpcOperationCallback caller;

        public RawCallbackAdpater(WampRpcOperationCallback caller) {
            this.caller = caller;
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details) {
            this.caller.result(details);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments) {
            this.caller.result(details, arguments);
        }

        @Override
        public <TMessage> void result(WampFormatter<TMessage> formatter, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
            this.caller.result(details, arguments, argumentsKeywords);
        }

        @Override
        public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error) {
            this.caller.error(details, error);
        }

        @Override
        public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments) {
            this.caller.error(details, error, arguments);
        }

        @Override
        public <TMessage> void error(WampFormatter<TMessage> formatter, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
            this.caller.error(details, error, arguments, argumentsKeywords);
        }
    }
}