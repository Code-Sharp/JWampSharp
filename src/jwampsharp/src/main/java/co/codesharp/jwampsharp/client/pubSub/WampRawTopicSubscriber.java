package co.codesharp.jwampsharp.client.pubSub;

import co.codesharp.jwampsharp.core.serialization.WampFormatter;

/**
 * Created by Elad on 18/04/2014.
 */
public interface WampRawTopicSubscriber {
    <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details);
    <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments);
    <TMessage> void event(WampFormatter<TMessage> formatter, long publicationId, TMessage details, TMessage[] arguments, TMessage argumentsKeywords);
}
