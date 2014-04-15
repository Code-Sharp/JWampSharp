import Core.Message.WampMessage;

/**
 * Created by Elad on 15/04/2014.
 */
class TextMessage<TMessage> extends WampMessage<TMessage> {
    private String text;

    public TextMessage(WampMessage<TMessage> other) {
        super(other);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}