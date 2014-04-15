package Core.Listener;

import Core.Message.WampMessage;
import Core.Serialization.WampFormatter;

/**
 * Created by Elad on 15/04/2014.
 */
public abstract class WampBindingBase<TMessage> implements WampBinding<TMessage> {
    private final String name;
    private final WampFormatter<TMessage> formatter;

    protected WampBindingBase(String name, WampFormatter<TMessage> formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public abstract WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message);

    @Override
    public WampFormatter<TMessage> getFormatter() {
        return formatter;
    }
}