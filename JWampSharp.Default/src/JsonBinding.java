import Core.Listener.WampBindingBase;
import Core.Listener.WampTextBinding;
import Core.Message.WampMessage;
import Core.Serialization.WampFormatter;
import Core.Serialization.WampTextMessageParser;

/**
 * Created by Elad on 15/04/2014.
 */
public abstract class JsonBinding<TMessage> extends WampBindingBase<TMessage> implements WampTextBinding<TMessage> {
    private final WampTextMessageParser<TMessage> parser;

    protected JsonBinding(WampFormatter<TMessage> formatter, WampTextMessageParser<TMessage> parser) {
        super("wamp.2.json", formatter);
        this.parser = parser;
    }

    @Override
    public WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message) {
        TextMessage<TMessage> result;

        if (message instanceof TextMessage) {
            result = (TextMessage<TMessage>)message;
        } else {
            result = new TextMessage<TMessage>(message);
            result.setText(parser.Format(message));
        }

        return result;
    }

    @Override
    public WampMessage parse(String message) {
        return parser.Parse(message);
    }
}