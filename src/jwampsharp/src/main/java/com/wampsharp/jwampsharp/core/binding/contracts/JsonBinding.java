package com.wampsharp.jwampsharp.core.binding.contracts;

import com.wampsharp.jwampsharp.core.binding.WampBindingBase;
import com.wampsharp.jwampsharp.core.binding.WampTextBinding;
import com.wampsharp.jwampsharp.core.message.WampMessage;
import com.wampsharp.jwampsharp.core.serialization.WampFormatter;
import com.wampsharp.jwampsharp.core.binding.WampTextMessageParser;

/**
 * Created by Elad on 15/04/2014.
 */
public class JsonBinding<TMessage> extends WampBindingBase<TMessage> implements WampTextBinding<TMessage> {
    private final WampTextMessageParser<TMessage> parser;

    protected JsonBinding(WampFormatter<TMessage> formatter, WampTextMessageParser<TMessage> parser) {
        super("wamp.2.json", formatter);
        this.parser = parser;
    }

    @Override
    public WampMessage<TMessage> getRawMessage(WampMessage<TMessage> message) {
        return getRawTextMessage(message);
    }

    private TextMessage<TMessage> getRawTextMessage(WampMessage<TMessage> message) {
        TextMessage<TMessage> result;

        if (message instanceof TextMessage) {
            result = (TextMessage<TMessage>)message;
        } else {
            result = new TextMessage<TMessage>(message);
            result.setText(parser.format(message));
        }

        return result;
    }

    @Override
    public WampMessage<TMessage> parse(String message) {
        return parser.parse(message);
    }

    @Override
    public String format(WampMessage<TMessage> message) {
        TextMessage<TMessage> rawMessage = getRawTextMessage(message);
        return rawMessage.getText();
    }

    private class TextMessage<TMessage> extends WampMessage<TMessage> {
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
}