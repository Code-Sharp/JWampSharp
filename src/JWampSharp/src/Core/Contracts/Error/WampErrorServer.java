package Core.Contracts.Error;

import Core.Contracts.WampClientProxy;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampErrorServer<TMessage> {
    void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error);

    void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error, TMessage[] arguments);

    void error(WampClientProxy client, int requestType, long requestId, TMessage details, String error, TMessage[] arguments, TMessage argumentsKeywords);
}
