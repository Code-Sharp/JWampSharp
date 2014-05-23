package co.codesharp.jwampsharp.core.proxy;

import co.codesharp.jwampsharp.core.message.WampMessage;
import co.codesharp.jwampsharp.core.message.WampMessageType;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;

import java.lang.reflect.Array;

/**
 * Created by Elad on 14/04/2014.
 */
public class WampProtocol<TMessage> {

    private Class<TMessage> underlyingMessageType;
    private WampFormatter<TMessage> formatter;

    public WampProtocol(Class<TMessage> underlyingMessageType,
                        WampFormatter<TMessage> formatter) {
        this.underlyingMessageType = underlyingMessageType;
        this.formatter = formatter;
    }

    public WampMessage<TMessage> challenge(String challenge, Object extra) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.CHALLENGE);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(challenge);
        array[1] = formatter.serialize(extra);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> welcome(long session, Object details) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.WELCOME);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(session);
        array[1] = formatter.serialize(details);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> abort(Object details, String reason) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.ABORT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(details);
        array[1] = formatter.serialize(reason);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> goodbye(Object details, String reason) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.GOODBYE);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(details);
        array[1] = formatter.serialize(reason);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> heartbeat(int incomingSeq, int outgoingSeq) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.HEARTBEAT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(incomingSeq);
        array[1] = formatter.serialize(outgoingSeq);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> heartbeat(int incomingSeq, int outgoingSeq, String discard) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.HEARTBEAT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(incomingSeq);
        array[1] = formatter.serialize(outgoingSeq);
        array[2] = formatter.serialize(discard);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> error(int requestType, long requestId, Object details, String error) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.ERROR);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(requestType);
        array[1] = formatter.serialize(requestId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(error);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> error(int requestType, long requestId, Object details, String error, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.ERROR);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 5);

        array[0] = formatter.serialize(requestType);
        array[1] = formatter.serialize(requestId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(error);
        array[4] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> error(int requestType, long requestId, Object details, String error, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.ERROR);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 6);

        array[0] = formatter.serialize(requestType);
        array[1] = formatter.serialize(requestId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(error);
        array[4] = formatter.serialize(arguments);
        array[5] = formatter.serialize(argumentsKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> registered(long requestId, long registrationId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.REGISTERED);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(registrationId);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> unregistered(long requestId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.UNREGISTERED);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 1);

        array[0] = formatter.serialize(requestId);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> invocation(long requestId, long registrationId, Object details) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.INVOCATION);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(registrationId);
        array[2] = formatter.serialize(details);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> invocation(long requestId, long registrationId, Object details, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.INVOCATION);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(registrationId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> invocation(long requestId, long registrationId, Object details, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.INVOCATION);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 5);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(registrationId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(arguments);
        array[4] = formatter.serialize(argumentsKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> interrupt(long requestId, Object options) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.INTERRUPT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> result(long requestId, Object details) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.RESULT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(details);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> result(long requestId, Object details, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.RESULT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(details);
        array[2] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> result(long requestId, Object details, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.RESULT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(details);
        array[2] = formatter.serialize(arguments);
        array[3] = formatter.serialize(argumentsKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> published(long requestId, long publicationId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.PUBLISHED);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(publicationId);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> subscribed(long requestId, long subscriptionId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.SUBSCRIBED);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(subscriptionId);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> unsubscribed(long requestId, long subscriptionId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.UNSUBSCRIBED);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(subscriptionId);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> event(long subscriptionId, long publicationId, Object details) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.EVENT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(subscriptionId);
        array[1] = formatter.serialize(publicationId);
        array[2] = formatter.serialize(details);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> event(long subscriptionId, long publicationId, Object details, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.EVENT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(subscriptionId);
        array[1] = formatter.serialize(publicationId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> event(long subscriptionId, long publicationId, Object details, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.EVENT);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 5);

        array[0] = formatter.serialize(subscriptionId);
        array[1] = formatter.serialize(publicationId);
        array[2] = formatter.serialize(details);
        array[3] = formatter.serialize(arguments);
        array[4] = formatter.serialize(argumentsKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> hello(String realm, Object details) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.HELLO);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(realm);
        array[1] = formatter.serialize(details);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> authenticate(String signature, Object extra) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.AUTHENTICATE);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(signature);
        array[1] = formatter.serialize(extra);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> register(long requestId, Object options, String procedure) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.REGISTER);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(procedure);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> unregister(long requestId, long registrationId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.UNREGISTER);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(registrationId);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> call(long requestId, Object options, String procedure) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.CALL);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(procedure);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> call(long requestId, Object options, String procedure, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.CALL);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(procedure);
        array[3] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> call(long requestId, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.CALL);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 5);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(procedure);
        array[3] = formatter.serialize(arguments);
        array[4] = formatter.serialize(argumentsKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> cancel(long requestId, Object options) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.CANCEL);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> yield(long requestId, Object options) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.YIELD);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> yield(long requestId, Object options, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.YIELD);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> yield(long requestId, Object options, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.YIELD);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(arguments);
        array[3] = formatter.serialize(argumentsKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> publish(long requestId, Object options, String topicUri) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.PUBLISH);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(topicUri);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> publish(long requestId, Object options, String topicUri, Object[] arguments) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.PUBLISH);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 4);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(topicUri);
        array[3] = formatter.serialize(arguments);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> publish(long requestId, Object options, String topicUri, Object[] arguments, Object argumentKeywords) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.PUBLISH);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 5);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(topicUri);
        array[3] = formatter.serialize(arguments);
        array[4] = formatter.serialize(argumentKeywords);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> subscribe(long requestId, Object options, String topicUri) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.SUBSCRIBE);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 3);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(options);
        array[2] = formatter.serialize(topicUri);

        result.setArguments(array);
        return result;
    }

    public WampMessage<TMessage> unsubscribe(long requestId, long subscriptionId) {
        WampMessage<TMessage> result = new WampMessage<TMessage>();
        result.setMessageType(WampMessageType.UNSUBSCRIBE);

        TMessage[] array = (TMessage[]) Array.newInstance(underlyingMessageType, 2);

        array[0] = formatter.serialize(requestId);
        array[1] = formatter.serialize(subscriptionId);

        result.setArguments(array);
        return result;
    }
}