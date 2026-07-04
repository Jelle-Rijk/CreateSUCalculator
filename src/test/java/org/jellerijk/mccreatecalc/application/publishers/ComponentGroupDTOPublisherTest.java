package org.jellerijk.mccreatecalc.application.publishers;

import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComponentGroupDTOPublisherTest {
    @Mock
    private ComponentGroup cg;

    @Mock
    private Observer<ComponentGroupDTO> observer;
    private ComponentGroupDTOPublisher cgPublisher;

    @BeforeEach
    void setUp() {
        cgPublisher = new ComponentGroupDTOPublisher();
    }

    @Test
    void publish_notifiesSubscriber() {
        ComponentGroup cg = new ComponentGroup("test", new WaterWheel(WaterWheelType.SMALL), 5);
        cgPublisher.subscribe(new Subscription<>(observer, "test"));
        cgPublisher.publish(cg);
        verify(observer).update(any());
    }

    @Test
    void publish_subscribedToDifferentCG_NotUpdated() {
        ComponentGroup cg = new ComponentGroup("differentId", new WaterWheel(WaterWheelType.SMALL), 5);
        cgPublisher.subscribe(new Subscription<>(mock(), "differentId"));
        cgPublisher.subscribe(new Subscription<>(observer, "anId"));
        cgPublisher.publish(cg);
        verifyNoInteractions(observer);
    }

    @Test
    void publish_NobodySubscribed_DoesNotThrow() {
        assertDoesNotThrow(() -> cgPublisher.publish(cg));
    }

    @Test
    void unsubscribe_ValidRequest_UnsubscribesObserver() {
        cgPublisher.subscribe(new Subscription<>(observer, "id"));
        cgPublisher.unsubscribe(new Subscription<>(observer, "id"));
        cgPublisher.publish(cg);
        verifyNoInteractions(observer);
    }

    @Test
    void unsubscribe_GroupIdNotInSubscriptions_DoesNotThrow() {
        assertDoesNotThrow(() -> cgPublisher.unsubscribe(new Subscription<>(observer, "id")));
    }

    @Test
    void unsubscribe_ObserverNotSubscribedToGroupId_DoesNotThrow() {
        cgPublisher.subscribe(new Subscription<>(mock(), "id"));
        assertDoesNotThrow(() -> cgPublisher.unsubscribe(new Subscription<>(observer, "id")));
    }

    @Test
    void containsKey_containsKey_ReturnsTrue() {
        cgPublisher.subscribe(new Subscription<>(mock(), "key"));
        assertTrue(() -> cgPublisher.containsKey("key"));
    }

    @Test
    void containsKey_DoesNotContainKey_ReturnsFalse() {
        assertFalse(() -> cgPublisher.containsKey("non-existant-key"));
    }

    @Test
    void unsubscribe_LastObserver_RemovesGroupIdFromMap() {
        cgPublisher.subscribe(new Subscription<>(observer, "id"));
        cgPublisher.unsubscribe(new Subscription<>(observer, "id"));
        assertFalse(cgPublisher.containsKey("id"));
    }
}