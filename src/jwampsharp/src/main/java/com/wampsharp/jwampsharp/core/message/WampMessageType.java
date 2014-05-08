package com.wampsharp.jwampsharp.core.message;

/**
 * Created by Elad on 13/04/2014.
 */
public class WampMessageType {
    public static final int HELLO = 1;
    public static final int WELCOME = 2;
    public static final int ABORT = 3;
    public static final int CHALLENGE = 4;
    public static final int AUTHENTICATE = 5;
    public static final int GOODBYE = 6;
    public static final int HEARTBEAT = 7;
    public static final int ERROR = 8;
    public static final int PUBLISH = 16;
    public static final int PUBLISHED = 17;
    public static final int SUBSCRIBE = 32;
    public static final int SUBSCRIBED = 33;
    public static final int UNSUBSCRIBE = 34;
    public static final int UNSUBSCRIBED = 35;
    public static final int EVENT = 36;
    public static final int CALL = 48;
    public static final int CANCEL = 49;
    public static final int RESULT = 50;
    public static final int REGISTER = 64;
    public static final int REGISTERED = 65;
    public static final int UNREGISTER = 66;
    public static final int UNREGISTERED = 67;
    public static final int INVOCATION = 68;
    public static final int INTERRUPT = 69;
    public static final int YIELD = 70;
}