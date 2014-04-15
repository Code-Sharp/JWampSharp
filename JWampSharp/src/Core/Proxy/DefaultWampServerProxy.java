package Core.Proxy;

import Core.Contracts.WampServerProxy;
import Core.Listener.WampConnection;
import Core.Message.WampMessage;

/**
 * Created by Elad on 14/04/2014.
 */
public class DefaultWampServerProxy<TMessage> implements WampServerProxy {

    private WampConnection<TMessage> connection;
    private WampProtocol<TMessage> protocol;

    public DefaultWampServerProxy(WampConnection<TMessage> connection, WampProtocol<TMessage> protocol) {
        this.connection = connection;
        this.protocol = protocol;
    }

    @Override
    public void hello(String realm, Object details) {
        WampMessage<TMessage> messageToSend = protocol.hello(realm, details);
        connection.send(messageToSend);
    }

    @Override
    public void abort(Object details, String reason) {
        WampMessage<TMessage> messageToSend = protocol.abort(details, reason);
        connection.send(messageToSend);
    }

    @Override
    public void authenticate(String signature, Object extra) {
        WampMessage<TMessage> messageToSend = protocol.authenticate(signature, extra);
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
    public void register(long requestId, Object options, String procedure) {
        WampMessage<TMessage> messageToSend = protocol.register(requestId, options, procedure);
        connection.send(messageToSend);
    }

    @Override
    public void unregister(long requestId, long registrationId) {
        WampMessage<TMessage> messageToSend = protocol.unregister(requestId, registrationId);
        connection.send(messageToSend);
    }

    @Override
    public void call(long requestId, Object options, String procedure) {
        WampMessage<TMessage> messageToSend = protocol.call(requestId, options, procedure);
        connection.send(messageToSend);
    }

    @Override
    public void call(long requestId, Object options, String procedure, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.call(requestId, options, procedure, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void call(long requestId, Object options, String procedure, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> messageToSend = protocol.call(requestId, options, procedure, arguments, argumentsKeywords);
        connection.send(messageToSend);
    }

    @Override
    public void cancel(long requestId, Object options) {
        WampMessage<TMessage> messageToSend = protocol.cancel(requestId, options);
        connection.send(messageToSend);
    }

    @Override
    public void yield(long requestId, Object options) {
        WampMessage<TMessage> messageToSend = protocol.yield(requestId, options);
        connection.send(messageToSend);
    }

    @Override
    public void yield(long requestId, Object options, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.yield(requestId, options, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void yield(long requestId, Object options, Object[] arguments, Object argumentsKeywords) {
        WampMessage<TMessage> messageToSend = protocol.yield(requestId, options, arguments, argumentsKeywords);
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
    public void publish(long requestId, Object options, String topicUri) {
        WampMessage<TMessage> messageToSend = protocol.publish(requestId, options, topicUri);
        connection.send(messageToSend);
    }

    @Override
    public void publish(long requestId, Object options, String topicUri, Object[] arguments) {
        WampMessage<TMessage> messageToSend = protocol.publish(requestId, options, topicUri, arguments);
        connection.send(messageToSend);
    }

    @Override
    public void publish(long requestId, Object options, String topicUri, Object[] arguments, Object argumentKeywords) {
        WampMessage<TMessage> messageToSend = protocol.publish(requestId, options, topicUri, arguments, argumentKeywords);
        connection.send(messageToSend);
    }

    @Override
    public void subscribe(long requestId, Object options, String topicUri) {
        WampMessage<TMessage> messageToSend = protocol.subscribe(requestId, options, topicUri);
        connection.send(messageToSend);
    }

    @Override
    public void unsubscribe(long requestId, long subscriptionId) {
        WampMessage<TMessage> messageToSend = protocol.unsubscribe(requestId, subscriptionId);
        connection.send(messageToSend);
    }
}