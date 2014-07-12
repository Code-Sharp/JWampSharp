package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.api.serialization.SerializedValue;
import rx.Observable;
import rx.subjects.Subject;
import rx.util.functions.Func1;

/**
 * Created by Elad on 7/13/2014.
 */
class WampEventSubject<TEvent> extends Subject<TEvent, TEvent> {

    private final Observable<TEvent> observable;
    private final WampSubject subject;

    public WampEventSubject(final Class<TEvent> eventType, final WampSubject subject) {
        super(null);
        this.subject = subject;
        this.observable = this.subject.where(new Func1<WampSerializedEvent, Boolean>() {
            @Override
            public Boolean call(WampSerializedEvent serializedEvent) {
                SerializedValue[] arguments = serializedEvent.getArguments();
                return (arguments != null) && (arguments.length > 0);
            }

        }).map(new Func1<WampSerializedEvent, TEvent>() {
            @Override
            public TEvent call(WampSerializedEvent serializedEvent) {
                return serializedEvent.getArguments()[0].deserialize(eventType);
            }
        });
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(TEvent value) {
        WampEventImpl wampEvent = new WampEventImpl();
        wampEvent.setArguments(new Object[]{value});
        subject.onNext(wampEvent);
    }
}