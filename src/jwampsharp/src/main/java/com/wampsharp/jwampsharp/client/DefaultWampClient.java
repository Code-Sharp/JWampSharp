package com.wampsharp.jwampsharp.client;

import com.wampsharp.jwampsharp.client.realm.WampRealmProxy;
import com.wampsharp.jwampsharp.client.session.SessionClient;
import com.wampsharp.jwampsharp.core.contracts.error.*;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampPublisher;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampSubscriber;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCallee;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCaller;
import com.wampsharp.jwampsharp.core.contracts.WampClient;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampClient<TMessage> implements WampClient<TMessage>, WampSessionClientExtended<TMessage>,
        WampCalleeError<TMessage>, WampCallerError<TMessage>,
        WampSubscriberError<TMessage>, WampPublisherError<TMessage> {

    private final WampRealmProxy realmProxy;
    private WampSessionClientExtended<TMessage> sessionClient;
    private WampError<TMessage> errorHandler;

    public DefaultWampClient(WampRealmProxyFactory<TMessage> realmProxyFactory) {
        realmProxy = realmProxyFactory.build(this);
        errorHandler = new ErrorForwarder<TMessage>(this);
        sessionClient = new SessionClient<TMessage>(realmProxy);
    }

    // Properties
    private WampSessionClientExtended<TMessage> getSessionClient() {
        return sessionClient;
    }

    private WampCallee<TMessage> getCallee() {
        return (WampCallee<TMessage>) realmProxy.getRpcCatalog();
    }

    private WampCaller<TMessage> getCaller() {
        return (WampCaller<TMessage>) realmProxy.getRpcCatalog();
    }

    private WampPublisher<TMessage> getPublisher() {
        return (WampPublisher<TMessage>) realmProxy.getTopicContainer();
    }

    private WampSubscriber<TMessage> getSubscriber() {
        return (WampSubscriber<TMessage>) realmProxy.getTopicContainer();
    }

    private WampError<TMessage> getErrorHandler() {
        return errorHandler;
    }

    public WampCalleeError<TMessage> getCalleeError() {
        return (WampCalleeError<TMessage>) realmProxy.getRpcCatalog();
    }

    public WampCallerError<TMessage> getCallerError() {
        return (WampCallerError<TMessage>) realmProxy.getRpcCatalog();
    }

    public WampPublisherError<TMessage> getPublisherError() {
        return (WampPublisherError<TMessage>) realmProxy.getTopicContainer();
    }

    public WampSubscriberError<TMessage> getSubscriberError() {
        return (WampSubscriberError<TMessage>) realmProxy.getTopicContainer();
    }

    public long getSession() {
        return getSessionClient().getSession();
    }

    public void challenge(String challenge, TMessage extra) {
        getSessionClient().challenge(challenge, extra);
    }

    public void onConnectionClosed() {
        getSessionClient().onConnectionClosed();
    }

    public void welcome(long session, TMessage details) {
        getSessionClient().welcome(session, details);
    }

    public void goodbye(TMessage details, String reason) {
        getSessionClient().goodbye(details, reason);
    }

    public void onConnectionOpen() {
        getSessionClient().onConnectionOpen();
    }

    public void heartbeat(int incomingSeq, int outgoingSeq, String discard) {
        getSessionClient().heartbeat(incomingSeq, outgoingSeq, discard);
    }

    public CompletionStage getOpenTask() {
        return getSessionClient().getOpenTask();
    }

    public WampRealmProxy getRealm() {
        return realmProxy;
    }

    public void heartbeat(int incomingSeq, int outgoingSeq) {
        getSessionClient().heartbeat(incomingSeq, outgoingSeq);
    }

    public void abort(TMessage details, String reason) {
        getSessionClient().abort(details, reason);
    }

    public void registered(long requestId, long registrationId) {
        getCallee().registered(requestId, registrationId);
    }

    public void interrupt(long requestId, TMessage options) {
        getCallee().interrupt(requestId, options);
    }

    public void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        getCallee().invocation(requestId, registrationId, details, arguments, argumentsKeywords);
    }

    public void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments) {
        getCallee().invocation(requestId, registrationId, details, arguments);
    }

    public void invocation(long requestId, long registrationId, TMessage details) {
        getCallee().invocation(requestId, registrationId, details);
    }

    public void unregistered(long requestId) {
        getCallee().unregistered(requestId);
    }

    public void result(long requestId, TMessage details) {
        getCaller().result(requestId, details);
    }

    public void result(long requestId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        getCaller().result(requestId, details, arguments, argumentsKeywords);
    }

    public void result(long requestId, TMessage details, TMessage[] arguments) {
        getCaller().result(requestId, details, arguments);
    }

    public void published(long requestId, long publicationId) {
        getPublisher().published(requestId, publicationId);
    }

    public void subscribed(long requestId, long subscriptionId) {
        getSubscriber().subscribed(requestId, subscriptionId);
    }

    public void event(long subscriptionId, long publicationId, TMessage details) {
        getSubscriber().event(subscriptionId, publicationId, details);
    }

    public void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        getSubscriber().event(subscriptionId, publicationId, details, arguments, argumentsKeywords);
    }

    public void event(long subscriptionId, long publicationId, TMessage details, TMessage[] arguments) {
        getSubscriber().event(subscriptionId, publicationId, details, arguments);
    }

    public void unsubscribed(long requestId, long subscriptionId) {
        getSubscriber().unsubscribed(requestId, subscriptionId);
    }

    public void error(int requestType, long requestId, TMessage details, String error) {
        this.getErrorHandler().error(requestType, requestId, details, error);
    }

    public void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments) {
        this.getErrorHandler().error(requestType, requestId, details, error, arguments);
    }

    public void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        this.getErrorHandler().error(requestType, requestId, details, error, arguments, argumentsKeywords);
    }

    public void registerError(long requestId, TMessage details, String error) {
        getCalleeError().registerError(requestId, details, error);
    }

    public void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments) {
        getCalleeError().unregisterError(requestId, details, error, arguments);
    }

    public void registerError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        getCalleeError().registerError(requestId, details, error, arguments, argumentsKeywords);
    }

    public void unregisterError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        getCalleeError().unregisterError(requestId, details, error, arguments, argumentsKeywords);
    }

    public void unregisterError(long requestId, TMessage details, String error) {
        getCalleeError().unregisterError(requestId, details, error);
    }

    public void registerError(long requestId, TMessage details, String error, TMessage[] arguments) {
        getCalleeError().registerError(requestId, details, error, arguments);
    }

    public void callError(long requestId, TMessage details, String error) {
        getCallerError().callError(requestId, details, error);
    }

    public void callError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        getCallerError().callError(requestId, details, error, arguments, argumentsKeywords);
    }

    public void callError(long requestId, TMessage details, String error, TMessage[] arguments) {
        getCallerError().callError(requestId, details, error, arguments);
    }

    public void publishError(long requestId, TMessage details, String error) {
        getPublisherError().publishError(requestId, details, error);
    }

    public void publishError(long requestId, TMessage details, String error, TMessage[] arguments) {
        getPublisherError().publishError(requestId, details, error, arguments);
    }

    public void publishError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        getPublisherError().publishError(requestId, details, error, arguments, argumentsKeywords);
    }

    public void subscribeError(long requestId, TMessage details, String error) {
        getSubscriberError().subscribeError(requestId, details, error);
    }

    public void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments) {
        getSubscriberError().unsubscribeError(requestId, details, error, arguments);
    }

    public void unsubscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        getSubscriberError().unsubscribeError(requestId, details, error, arguments, argumentsKeywords);
    }

    public void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        getSubscriberError().subscribeError(requestId, details, error, arguments, argumentsKeywords);
    }

    public void subscribeError(long requestId, TMessage details, String error, TMessage[] arguments) {
        getSubscriberError().subscribeError(requestId, details, error, arguments);
    }

    public void unsubscribeError(long requestId, TMessage details, String error) {
        getSubscriberError().unsubscribeError(requestId, details, error);
    }
}