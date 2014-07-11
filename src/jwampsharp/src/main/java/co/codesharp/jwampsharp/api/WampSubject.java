package co.codesharp.jwampsharp.api;

import co.codesharp.jwampsharp.api.rx.WampEvent;
import co.codesharp.jwampsharp.api.rx.WampSerializedEvent;
import rx.subjects.Subject;


/**
 * Created by Elad on 7/11/2014.
 */
public abstract class WampSubject extends Subject<WampEvent, WampSerializedEvent> {
    protected WampSubject(OnSubscribeFunc<WampSerializedEvent> onSubscribe) {
        super(onSubscribe);
    }
}