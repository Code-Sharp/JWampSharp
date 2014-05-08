package com.wampsharp.jwampsharp.client.session;

import com.wampsharp.jwampsharp.client.realm.WampRealmProxy;
import com.wampsharp.jwampsharp.client.WampSessionClientExtended;
import com.wampsharp.jwampsharp.core.contracts.WampServerProxy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Elad on 16/04/2014.
 */
public class SessionClient<TMessage> implements WampSessionClientExtended<TMessage> {
    private final WampRealmProxy realm;
    private WampServerProxy serverProxy;
    private long session;
    private final CompletableFuture<Boolean> openTask = new CompletableFuture<Boolean>();

    public SessionClient(WampRealmProxy realm) {
        this.realm = realm;
        this.serverProxy = realm.getProxy();
    }

    @Override
    public long getSession() {
        return session;
    }

    @Override
    public WampRealmProxy getRealm() {
        return realm;
    }

    @Override
    public java.util.concurrent.CompletionStage getOpenTask() {
        return openTask;
    }

    @Override
    public void onConnectionOpen() {
        Map<String, Object> details = new HashMap<String, Object>();
        Map<String, Object> roles = new HashMap<String, Object>();

        roles.put("caller", new HashMap<String, String>());
        roles.put("callee", new HashMap<String, String>());
        roles.put("publisher", new HashMap<String, String>());
        roles.put("subscriber", new HashMap<String, String>());
        details.put("roles", roles);

        serverProxy.hello(realm.getName(), details);
    }

    @Override
    public void onConnectionClosed() {

    }

    @Override
    public void challenge(String challenge, TMessage extra) {

    }

    @Override
    public void welcome(long session, TMessage details) {
        this.session = session;
        openTask.complete(true);
    }

    @Override
    public void abort(TMessage details, String reason) {

    }

    @Override
    public void goodbye(TMessage details, String reason) {

    }

    @Override
    public void heartbeat(int incomingSeq, int outgoingSeq) {

    }

    @Override
    public void heartbeat(int incomingSeq, int outgoingSeq, String discard) {

    }
}
