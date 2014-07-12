package co.codesharp.jwampsharp.api.rx;

import co.codesharp.jwampsharp.client.pubSub.WampTopicProxy;
import rx.subjects.Subject;

/**
 * Created by Elad on 7/13/2014.
 */
public class WampTopicExtensions {
    public static WampSubject toSubject(WampTopicProxy topicProxy) {
        return new WampClientSubject(topicProxy);
    }

    public static <TEvent> Subject<TEvent, TEvent> toSubject(Class<TEvent> eventClass, WampTopicProxy topicProxy) {
        WampSubject wampSubject = toSubject(topicProxy);
        return new WampEventSubject<TEvent>(eventClass, wampSubject);
    }
}
