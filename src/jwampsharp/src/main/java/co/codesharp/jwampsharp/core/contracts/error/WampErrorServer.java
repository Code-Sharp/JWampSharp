package co.codesharp.jwampsharp.core.contracts.error;

import co.codesharp.jwampsharp.core.contracts.WampClientProxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampErrorServer<TMessage> {
    void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error);

    void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error, TMessage[] arguments);

    void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
