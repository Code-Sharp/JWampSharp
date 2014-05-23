package co.codesharp.jwampsharp.core.dispatch;

import co.codesharp.jwampsharp.core.contracts.WampClient;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.message.WampMessageType;

/**
 * Created by Elad on 14/04/2014.
 */
public class DefaultWampClientIncomingMessageHandler<TMessage> implements WampClientIncomingMessageHandler<TMessage> {

    private WampFormatter<TMessage> formatter;
    private WampClient<TMessage> client;
    private Class<TMessage> messageClass;
    private Class<TMessage[]> messageArrayClass;

    public DefaultWampClientIncomingMessageHandler(Class<TMessage> messageClass, Class<TMessage[]> messageArrayClass, WampFormatter<TMessage> formatter, WampClient<TMessage> client) {
        this.messageClass = messageClass;
        this.messageArrayClass = messageArrayClass;
        this.formatter = formatter;
        this.client = client;
    }

    @Override
    public void handleMessage(WampMessage<TMessage> message) {
        switch (message.getMessageType())
        {
            case WampMessageType.CHALLENGE:
            {
                handleChallenge(message);
                break;
            }
            case WampMessageType.WELCOME:
            {
                handleWelcome(message);
                break;
            }
            case WampMessageType.ABORT:
            {
                handleAbort(message);
                break;
            }
            case WampMessageType.GOODBYE:
            {
                handleGoodbye(message);
                break;
            }
            case WampMessageType.HEARTBEAT:
            {
                handleHeartbeat(message);
                break;
            }
            case WampMessageType.REGISTERED:
            {
                handleRegistered(message);
                break;
            }
            case WampMessageType.UNREGISTERED:
            {
                handleUnregistered(message);
                break;
            }
            case WampMessageType.INVOCATION:
            {
                handleInvocation(message);
                break;
            }
            case WampMessageType.INTERRUPT:
            {
                handleInterrupt(message);
                break;
            }
            case WampMessageType.RESULT:
            {
                handleResult(message);
                break;
            }
            case WampMessageType.PUBLISHED:
            {
                handlePublished(message);
                break;
            }
            case WampMessageType.SUBSCRIBED:
            {
                handleSubscribed(message);
                break;
            }
            case WampMessageType.UNSUBSCRIBED:
            {
                handleUnsubscribed(message);
                break;
            }
            case WampMessageType.EVENT:
            {
                handleEvent(message);
                break;
            }
            case WampMessageType.ERROR:
            {
                handleError(message);
                break;
            }
            default:
            {
                handleMissingMessage(message);
                break;
            }
        }
    }

    protected void handleMissingMessage(WampMessage<TMessage> message) {
    }

    protected void handleInvalidMessage(WampMessage<TMessage> message) {
    }

    private void handleChallenge(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            String challenge = formatter.<String>deserialize(String.class, messageArguments[0]);
            TMessage extra = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            client.challenge(challenge, extra);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleWelcome(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long session = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            client.welcome(session, details);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleAbort(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[0]);
            String reason = formatter.<String>deserialize(String.class, messageArguments[1]);

            client.abort(details, reason);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleGoodbye(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[0]);
            String reason = formatter.<String>deserialize(String.class, messageArguments[1]);

            client.goodbye(details, reason);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleHeartbeat(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            int incomingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            int outgoingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[1]);

            client.heartbeat(incomingSeq, outgoingSeq);
        }
        else if (messageArgumentsLength == 3) {
            int incomingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            int outgoingSeq = formatter.<Integer>deserialize(Integer.class, messageArguments[1]);
            String discard = formatter.<String>deserialize(String.class, messageArguments[2]);

            client.heartbeat(incomingSeq, outgoingSeq, discard);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleRegistered(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long registrationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);

            client.registered(requestId, registrationId);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleUnregistered(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 1) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);

            client.unregistered(requestId);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleInvocation(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long registrationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);

            client.invocation(requestId, registrationId, details);
        }
        else if (messageArgumentsLength == 4) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long registrationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);

            client.invocation(requestId, registrationId, details, arguments);
        }
        else if (messageArgumentsLength == 5) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long registrationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[4]);

            client.invocation(requestId, registrationId, details, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleInterrupt(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage options = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            client.interrupt(requestId, options);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleResult(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);

            client.result(requestId, details);
        }
        else if (messageArgumentsLength == 3) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[2]);

            client.result(requestId, details, arguments);
        }
        else if (messageArgumentsLength == 4) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[1]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[2]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[3]);

            client.result(requestId, details, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handlePublished(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long publicationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);

            client.published(requestId, publicationId);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleSubscribed(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long subscriptionId = formatter.<Long>deserialize(Long.class, messageArguments[1]);

            client.subscribed(requestId, subscriptionId);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleUnsubscribed(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 2) {
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long subscriptionId = formatter.<Long>deserialize(Long.class, messageArguments[1]);

            client.unsubscribed(requestId, subscriptionId);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleEvent(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 3) {
            long subscriptionId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long publicationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);

            client.event(subscriptionId, publicationId, details);
        }
        else if (messageArgumentsLength == 4) {
            long subscriptionId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long publicationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);

            client.event(subscriptionId, publicationId, details, arguments);
        }
        else if (messageArgumentsLength == 5) {
            long subscriptionId = formatter.<Long>deserialize(Long.class, messageArguments[0]);
            long publicationId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[3]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[4]);

            client.event(subscriptionId, publicationId, details, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

    private void handleError(WampMessage<TMessage> message) {
        TMessage[] messageArguments = message.getArguments();
        int messageArgumentsLength = messageArguments.length;

        if (messageArgumentsLength == 4) {
            int requestType = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            String error = formatter.<String>deserialize(String.class, messageArguments[3]);

            client.error(requestType, requestId, details, error);
        }
        else if (messageArgumentsLength == 5) {
            int requestType = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            String error = formatter.<String>deserialize(String.class, messageArguments[3]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[4]);

            client.error(requestType, requestId, details, error, arguments);
        }
        else if (messageArgumentsLength == 6) {
            int requestType = formatter.<Integer>deserialize(Integer.class, messageArguments[0]);
            long requestId = formatter.<Long>deserialize(Long.class, messageArguments[1]);
            TMessage details = formatter.<TMessage>deserialize(messageClass, messageArguments[2]);
            String error = formatter.<String>deserialize(String.class, messageArguments[3]);
            TMessage[] arguments = formatter.<TMessage[]>deserialize(messageArrayClass, messageArguments[4]);
            TMessage argumentsKeywords = formatter.<TMessage>deserialize(messageClass, messageArguments[5]);

            client.error(requestType, requestId, details, error, arguments, argumentsKeywords);
        }
        else
        {
            handleInvalidMessage(message);
        }
    }

}
