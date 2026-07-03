package org.jellerijk.mccreatecalc.entities.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ComponentTypeTest {
    @Test
    void of_WaterWheel_ReturnsWaterWheel() {
        WaterWheel w = mock();
        assertEquals(ComponentType.WATER_WHEEL, ComponentType.of(w));
    }

    @Test
    void of_Windmill_ReturnsWindmill() {
        Windmill w = mock();
        assertEquals(ComponentType.WINDMILL, ComponentType.of(w));
    }

    @Test
    void of_Consumer_ReturnsConsumer() {
        Consumer c = mock();
        assertEquals(ComponentType.CONSUMER, ComponentType.of(c));
    }
}