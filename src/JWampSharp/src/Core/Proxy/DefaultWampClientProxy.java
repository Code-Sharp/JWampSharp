package Core.Proxy;

import Core.Contracts.WampClientProxy;
import Core.Listener.WampConnection;
import Core.Message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public class DefaultWampClientProxy<TMessage> implements WampClientProxy {

    private WampConnection<TMessage> connection;
    private WampProtocol<TMessage> protocol;

    public DefaultWampClientProxy(WampConnection<TMessage> connection, WampProtocol<TMessage> protocol) {
        this.connection = connection;
        this.protocol = protocol;
    }

    @Override
    public void challenge(String challenge, Object extra) {
        WampMessage<TMessage> messageToSend = protocol.challenge(challenge, extra);
        connection.send(messageToSend);
    }

    @Override
    public void welcome(long session, Object details) {
        WampMessage<TMessage> messageToSend = protocol.welcome(session, details);
        connection.send(messageToSend);
    }

    @Override
    public void abort(Object details, String reason) {
        WampMessage<TMessage> messageToSend = protocol.abort(details, reason);
        connection.send(messageToSend);
    }

    @Override
    public void goodbye(Object details, String reason) {
        WampMessage<TMessage> messageToSend = protocol.goodbye(details, reason);
        connection.send(messageToSend);
    }

    @Override
    public void heartbeat(int incomingSeq, int outgoingSeq) {
        WampMessage<TMessage> messageToSend = protocol.heartbeat(incomingSeq, outgoingSeq);
        connection.send(messageToSend);
    }

    @Override
    public void heartbeat(int incomingSeq, int outgoingSeq, String discard) {
        WampMessage<TMessage> messageToSend = protocol.heartbeat(incomingSeq, outgoingSeq, discard);
        connection.send(messageToSend);
    }

    @Override
    public void error(int requestType, long requestId, Object details, String error) {
        WampMessage<TMessage> messageToSend = protocol.error(requestType, requestId, details, error);
        connection.send(messageToSend);
    }

    @Override
    public void error(int requestType, long requestId, Object details, String error, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.error(requestType, requestId, details, error, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void error(int requestType, long requestId, Object details, String error, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> messageToSend = protocol.error(requestType, requestId, details, error, arguments, argumentsKeywords);
        connection.send(messageToSend);
    }

    @Override
    public void registered(long requestId, long registrationId) {
        WampMessage<TMessage> messageToSend = protocol.registered(requestId, registrationId);
        connection.send(messageToSend);
    }

    @Override
    public void unregistered(long requestId) {
        WampMessage<TMessage> messageToSend = protocol.unregistered(requestId);
        connection.send(messageToSend);
    }

    @Override
    public void invocation(long requestId, long registrationId, Object details) {
        WampMessage<TMessage> messageToSend = protocol.invocation(requestId, registrationId, details);
        connection.send(messageToSend);
    }

    @Override
    public void invocation(long requestId, long registrationId, Object details, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.invocation(requestId, registrationId, details, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void invocation(long requestId, long registrationId, Object details, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> messageToSend = protocol.invocation(requestId, registrationId, details, arguments, argumentsKeywords);
        connection.send(messageToSend);
    }

    @Override
    public void interrupt(long requestId, Object options) {
        WampMessage<TMessage> messageToSend = protocol.interrupt(requestId, options);
        connection.send(messageToSend);
    }

    @Override
    public void result(long requestId, Object details) {
        WampMessage<TMessage> messageToSend = protocol.result(requestId, details);
        connection.send(messageToSend);
    }

    @Override
    public void result(long requestId, Object details, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.result(requestId, details, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void result(long requestId, Object details, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> messageToSend = protocol.result(requestId, details, arguments, argumentsKeywords);
        connection.send(messageToSend);
    }

    @Override
    public void published(long requestId, long publicationId) {
        WampMessage<TMessage> messageToSend = protocol.published(requestId, publicationId);
        connection.send(messageToSend);
    }

    @Override
    public void subscribed(long requestId, long subscriptionId) {
        WampMessage<TMessage> messageToSend = protocol.subscribed(requestId, subscriptionId);
        connection.send(messageToSend);
    }

    @Override
    public void unsubscribed(long requestId, long subscriptionId) {
        WampMessage<TMessage> messageToSend = protocol.unsubscribed(requestId, subscriptionId);
        connection.send(messageToSend);
    }

    @Override
    public void event(long subscriptionId, long publicationId, Object details) {
        WampMessage<TMessage> messageToSend = protocol.event(subscriptionId, publicationId, details);
        connection.send(messageToSend);
    }

    @Override
    public void event(long subscriptionId, long publicationId, Object details, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.event(subscriptionId, publicationId, details, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void event(long subscriptionId, long publicationId, Object details, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> messageToSend = protocol.event(subscriptionId, publicationId, details, arguments, argumentsKeywords);
        connection.send(messageToSend);
    }
}