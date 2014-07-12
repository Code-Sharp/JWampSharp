package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.client.pubSub.WampRawTopicSubscriber;
import co.codesharp.jwampsharp.core.serialization.WampFormatter;
import rx.Observer;

class RawSubscriber implements WampRawTopicSubscriber {

    private final Observer<? super WampSerializedEvent> observer;

    public RawSubscriber(Observer<? super WampSerializedEvent> observer) {
        this.observer = observer;
    }

    @Override
    public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details) {
        observer.onNext(new WampSerializedEventRawImpl<TMessage>(formatter, publicationId, details));
    }

    @Override
    public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments) {
        observer.onNext(new WampSerializedEventRawImpl<TMessage>(formatter, publicationId, details, arguments));
    }

    @Override
    public <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords) {
        observer.onNext(new WampSerializedEventRawImpl<TMessage>(formatter, publicationId, details, arguments, argumentsKeywords));
    }
}