package co.codesharp.jwampsharp.client;

import co.codesharp.jwampsharp.core.contracts.error.WampError;
import co.codesharp.jwampsharp.core.message.WampMessageType;

/**
 * Created by Elad on 16/05/2014.
 */
class ErrorForwarder<TMessage> implements WampError<TMessage> {
    private final DefaultWampClient client;

    public ErrorForwarder(DefaultWampClient client) {
        this.client = client;
    }

    @Override
    public void error(int requestType, long requestId, TMessage details, String error) {
        switch (requestType) {
            case WampMessageType.REGISTER:
            {
                client.registerError(requestId, details, error);
                break;
            }
            case WampMessageType.UNREGISTER:
            {
                client.unregisterError(requestId, details, error);
                break;
            }
            case WampMessageType.CALL:
            {
                client.callError(requestId, details, error);
                break;
            }
            case WampMessageType.SUBSCRIBE:
            {
                client.subscribeError(requestId, details, error);
                break;
            }
            case WampMessageType.UNSUBSCRIBE:
            {
                client.unsubscribeError(requestId, details, error);
                break;
            }
            case WampMessageType.PUBLISH:
            {
                client.publishError(requestId, details, error);
                break;
            }
        }
    }

    @Override
    public void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments) {
        switch (requestType) {
            case WampMessageType.REGISTER:
            {
                client.registerError(requestId, details, error, arguments);
                break;
            }
            case WampMessageType.UNREGISTER:
            {
                client.unregisterError(requestId, details, error, arguments);
                break;
            }
            case WampMessageType.CALL:
            {
                client.callError(requestId, details, error, arguments);
                break;
            }
            case WampMessageType.SUBSCRIBE:
            {
                client.subscribeError(requestId, details, error, arguments);
                break;
            }
            case WampMessageType.UNSUBSCRIBE:
            {
                client.unsubscribeError(requestId, details, error, arguments);
                break;
            }
            case WampMessageType.PUBLISH:
            {
                client.publishError(requestId, details, error, arguments);
                break;
            }
        }
    }

    @Override
    public void error(int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords) {
        switch (requestType) {
            case WampMessageType.REGISTER:
            {
                client.registerError(requestId, details, error, arguments, argumentsKeywords);
                break;
            }
            case WampMessageType.UNREGISTER:
            {
                client.unregisterError(requestId, details, error, arguments, argumentsKeywords);
                break;
            }
            case WampMessageType.CALL:
            {
                client.callError(requestId, details, error, arguments, argumentsKeywords);
                break;
            }
            case WampMessageType.SUBSCRIBE:
            {
                client.subscribeError(requestId, details, error, arguments, argumentsKeywords);
                break;
            }
            case WampMessageType.UNSUBSCRIBE:
            {
                client.unsubscribeError(requestId, details, error, arguments, argumentsKeywords);
                break;
            }
            case WampMessageType.PUBLISH:
            {
                client.publishError(requestId, details, error, arguments, argumentsKeywords);
                break;
            }
        }
    }
}