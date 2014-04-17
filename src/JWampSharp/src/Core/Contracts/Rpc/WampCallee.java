package Core.Contracts.Rpc;

/**
 * Created by Elad on 15/04/2014.
 */
public interface WampCallee<TMessage> {
    void registered(long requestId, long registrationId);

    void unregistered(long requestId);

    void invocation(long requestId, long registrationId, TMessage details);

    void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments);

    void invocation(long requestId, long registrationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords);

    void interrupt(long requestId, TMessage options);
}
