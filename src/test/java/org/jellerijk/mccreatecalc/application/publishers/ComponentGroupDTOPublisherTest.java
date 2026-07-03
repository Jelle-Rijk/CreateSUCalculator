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
}