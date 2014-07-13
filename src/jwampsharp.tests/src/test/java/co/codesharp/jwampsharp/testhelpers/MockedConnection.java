package co.codesharp.jwampsharp.testhelpers;

import co.codesharp.jwampsharp.core.listener.ControlledWampConnection;
import co.codesharp.jwampsharp.core.listener.WampConnection;
import co.codesharp.jwampsharp.core.message.WampMessage;
import rx.subjects.PublishSubject;

/**
 * Created by Elad on 7/13/2014.
 */
public class MockedConnection<TMessage> {
    private final DirectedConnection<TMessage> sideAToSideB;
    private final DirectedConnection<TMessage> sideBToSideA;

    public MockedConnection(){
        PublishSubject<WampMessage<TMessage>> sideAToSideBSubject =
                PublishSubject.create();

        PublishSubject<WampMessage<TMessage>> sideBToSideASubject =
                PublishSubject.create();

        this.sideAToSideB =
                new DirectedConnection<TMessage>(sideAToSideBSubject, sideBToSideASubject);

        this.sideBToSideA =
                new DirectedConnection<TMessage>(sideBToSideASubject, sideAToSideBSubject);
    }

    public ControlledWampConnection<TMessage> getSideAToSideB() {
        return sideAToSideB;
    }

    public ControlledWampConnection<TMessage> getSideBToSideA() {
        return sideBToSideA;
    }
}