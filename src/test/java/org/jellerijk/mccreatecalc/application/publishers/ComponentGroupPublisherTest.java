package org.jellerijk.mccreatecalc.application.publishers;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComponentGroupPublisherTest {
    @Mock
    private ComponentGroup cg;
    @Mock
    private Observer<ComponentGroup> observer;
    private ComponentGroupPublisher cgPublisher;

    @BeforeEach
    void setUp() {
        cgPublisher = new ComponentGroupPublisher();
    }

    @Test
    void publish_notifiesSubscriber() {
        when(cg.getId()).thenReturn("test");
        cgPublisher.subscribe(observer, "test");
        cgPublisher.publish(cg);
        verify(observer).update(cg);
    }

    @Test
    void publish_subscribedToDifferentCG_NotUpdated() {
        when(cg.getId()).thenReturn("differentId");
        cgPublisher.subscribe(mock(), "differentId");
        cgPublisher.subscribe(observer, "anId");
        cgPublisher.publish(cg);
        verifyNoInteractions(observer);
    }

    @Test
    void publish_NobodySubscribed_DoesNotThrow() {
        assertDoesNotThrow(() -> cgPublisher.publish(cg));
    }
}