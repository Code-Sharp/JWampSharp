package co.codesharp.jwampsharp.core.dispatch;

import co.codesharp.jwampsharp.core.contracts.WampServer;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.core.contracts.WampClientProxy;
import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.message.WampMessageType;

/**
 * Created by Elad on 14/04/2014.
 */
public class DefaultWampServerIncomingMessageHandler<TMessage> implements WampServerIncomingMessageHandler<TMessage> {

    private Class<TMessage> messageClass;
    private Class<TMessage[]> messageArrayClass;
    private WampFormatter<TMessage> formatter;
    private WampServer<TMessage> server;

    public DefaultWampServerIncomingMessageHandler(Class<TMessage> messageClass, Class<TMessage[]> messageArrayClass, WampFormatter<TMessage> formatter, WampServer<TMessage> server) {
        this.messageClass = messageClass;
        this.messageArrayClass = messageArrayClass;
        this.formatter = formatter;
        this.server = server;
    }

    @Override
    public void handleMessage(WampClientProxy client, WampMessage<TMessage> message) {
        switch (message.getMessageType())
        {
            case WampMessageType.HELLO:
            {
                handleHello(client, message);
                break;
            }
            case WampMessageType.ABORT:
            {
                handleAbort(client, message);
                break;
            }
            case WampMessageType.AUTHENTICATE:
            {
                handleAuthenticate(client, message);
                break;
            }
            case WampMessageType.GOODBYE:
            {
                handleGoodbye(client, message);
                break;
            }
            case WampMessageType.HEARTBEAT:
            {
                handleHeartbeat(client, message);
                break;
            }
            case WampMessageType.REGISTER:
            {
                handleRegister(client, message);
                break;
            }
            case WampMessageType.UNREGISTER:
            {
                handleUnregister(client, message);
                break;
            }
            case WampMessageType.CALL:
            {
                handleCall(client, message);
                break;
            }
            case WampMessageType.CANCEL:
            {
                handleCancel(client, message);
                break;
            }
            case WampMessageType.YIELD:
            {
                handleYield(client, message);
                break;
            }
            case WampMessageType.ERROR:
            {
                handleError(client, message);
                break;
            }
            case WampMessageType.PUBLISH:
            {
                handlePublish(client, message);
                break;
            }
            case WampMessageType.SUBSCRIBE:
            {
                handleSubscribe(client, message);
                break;
            }
            case WampMessageType.UNSUBSCRIBE:
            {
                handleUnsubscribe(client, message);
                break;
            }
            default:
            {
                handleMissingMessage(client, message);
                break;
            }
        }
    }

    protected void handleMissingMessage(WampClientProxy client, WampMessage<TMessage> message) {
    }

    protected void handleInvalidMessage(WampClientProxy client, WampMessage<TMessage> message) {
    }

    private void handleHello(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            String realm = formatter.<String>deserialize(String.class, messageArguments[0]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            server.hello(client, realm, details);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleAbort(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[0]);
            String reason = formatter.<String>deserialize(String.class, messageArguments[1]);

            server.abort(client, details, reason);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleAuthenticate(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            String signature = formatter.<String>deserialize(String.class, messageArguments[0]);
            TMessage extra = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            server.authenticate(client, signature, extra);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleGoodbye(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[0]);
            String reason = formatter.<String>deserialize(String.class, messageArguments[1]);

            server.goodbye(client, details, reason);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleHeartbeat(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            int incomingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            int outgoingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[1]);

            server.heartbeat(client, incomingSeq, outgoingSeq);
        }
        else if (messageArgumentsLength == 3) {
            int incomingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            int outgoingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[1]);
            String discard = formatter.<String>deserialize(String.class, messageArguments[2]);

            server.heartbeat(client, incomingSeq, outgoingSeq, discard);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleRegister(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String procedure = formatter.<String>deserialize(String.class, messageArguments[2]);

            server.register(client, requestId, options, procedure);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleUnregister(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long registrationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);

            server.unregister(client, requestId, registrationId);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleCall(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String procedure = formatter.<String>deserialize(String.class, messageArguments[2]);

            server.call(client, requestId, options, procedure);
        }
        else if (messageArgumentsLength == 4) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String procedure = formatter.<String>deserialize(String.class, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);

            server.call(client, requestId, options, procedure, arguments);
        }
        else if (messageArgumentsLength == 5) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String procedure = formatter.<String>deserialize(String.class, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[4]);

            server.call(client, requestId, options, procedure, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleCancel(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            server.cancel(client, requestId, options);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleYield(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            server.yield(client, requestId, options);
        }
        else if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[2]);

            server.yield(client, requestId, options, arguments);
        }
        else if (messageArgumentsLength == 4) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[2]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[3]);

            server.yield(client, requestId, options, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleError(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 4) {
            int requestType = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            String error = formatter.<String>deserialize(String.class, messageArguments[3]);

            server.error(client, requestType, requestId, details, error);
        }
        else if (messageArgumentsLength == 5) {
            int requestType = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            String error = formatter.<String>deserialize(String.class, messageArguments[3]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[4]);

            server.error(client, requestType, requestId, details, error, arguments);
        }
        else if (messageArgumentsLength == 6) {
            int requestType = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            String error = formatter.<String>deserialize(String.class, messageArguments[3]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[4]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[5]);

            server.error(client, requestType, requestId, details, error, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handlePublish(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String topicUri = formatter.<String>deserialize(String.class, messageArguments[2]);

            server.publish(client, requestId, options, topicUri);
        }
        else if (messageArgumentsLength == 4) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String topicUri = formatter.<String>deserialize(String.class, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);

            server.publish(client, requestId, options, topicUri, arguments);
        }
        else if (messageArgumentsLength == 5) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String topicUri = formatter.<String>deserialize(String.class, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);
            TMessage argumentKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[4]);

            server.publish(client, requestId, options, topicUri, arguments, argumentKeywords);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleSubscribe(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            String topicUri = formatter.<String>deserialize(String.class, messageArguments[2]);

            server.subscribe(client, requestId, options, topicUri);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

    private void handleUnsubscribe(WampClientProxy client, WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long subscriptionId = formatter.<Long>deserialize(Long.class, messageArguments[1]);

            server.unsubscribe(client, requestId, subscriptionId);
        }
        else
        {
            handleInvalidMessage(client, message);
        }
    }

}
