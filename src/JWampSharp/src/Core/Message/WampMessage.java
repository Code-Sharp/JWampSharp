package Core.Message;

/**
 * Created by Elad on 13/04/2014.
 */
public class WampMessage<TMessage> {

    private int messageType;
    private TMessage[] arguments;

    public WampMessage() {
    }

    protected WampMessage(WampMessage<TMessage> other) {
        this.messageType = other.messageType;
        this.arguments = other.arguments;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public TMessage[] getArguments() {
        return arguments;
    }

    public void setArguments(TMessage[] arguments) {
        this.arguments = arguments;
    }
}