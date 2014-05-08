package com.wampsharp.jwampsharp.client;

import com.wampsharp.jwampsharp.client.realm.WampRealmProxy;
import com.wampsharp.jwampsharp.client.session.SessionClient;
import com.wampsharp.jwampsharp.core.contracts.error.WampError;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampPublisher;
import com.wampsharp.jwampsharp.core.contracts.pubSub.WampSubscriber;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCallee;
import com.wampsharp.jwampsharp.core.contracts.rpc.WampCaller;
import com.wampsharp.jwampsharp.core.contracts.WampClient;

import java.util.concurrent.CompletionStage;

/**
 * Created by Elad on 16/04/2014.
 */
public class DefaultWampClient<TMessage> implements WampClient<TMessage>, WampSessionClientExtended<TMessage> {

    private final WampRealmProxy realmProxy;
    private WampSessionClientExtended<TMessage> sessionClient;
    private WampCaller<TMessage> caller;
    private WampError<TMessage> error;

    public DefaultWampClient(WampRealmProxyFactory<TMessage> realmProxyFactory) {
        realmProxy = realmProxyFactory.build(this);
        sessionClient = new SessionClient<TMessage>(realmProxy);
    }

    // Properties
    private WampSessionClientExtended<TMessage> getSessionClient() {
        return sessionClient;
    }

    private WampCallee<TMessage> getCallee() {
        return (WampCallee<TMessage>)realmProxy.getRpcCatalog();
    }

    private WampCaller<TMessage> getCaller() {
        return (WampCaller<TMessage>)realmProxy.getRpcCatalog();
    }

    private WampPublisher<TMessage> getPublisher() {
        return (WampPublisher<TMessage>)realmProxy.getTopicContainer();
    }

    private WampSubscriber<TMessage> getSubscriber() {
        return (WampSubscriber<TMessage>)realmProxy.getTopicContainer();
    }

    private WampError<TMessage> getError() {
        return error;
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
        this.getError().error(requestType, requestId, details, error);
    }

    public void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments) {
        this.getError().error(requestType, requestId, details, error, arguments);
    }

    public void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        this.getError().error(requestType, requestId, details, error, arguments, argumentsKeywords);
    }
}