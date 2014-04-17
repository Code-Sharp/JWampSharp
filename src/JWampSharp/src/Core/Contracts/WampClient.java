package Core.Contracts;

import Core.Contracts.Error.WampError;
import Core.Contracts.PubSub.WampPublisher;
import Core.Contracts.PubSub.WampSubscriber;
import Core.Contracts.Rpc.WampCallee;
import Core.Contracts.Rpc.WampCaller;
import Core.Contracts.Session.WampSessionClient;

/**
 * Created by Elad on 14/04/2014.
 */
public interface WampClient<TMessage> extends WampSessionClient<TMessage>,WampCallee<TMessage>,WampCaller<TMessage>,WampPublisher, WampSubscriber<TMessage>,WampError<TMessage> {


}