package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.components.Windmill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ComponentOptionTest {

    @Test
    void map_WaterWheel_ReturnsCorrectRecord() {
        WaterWheel w = mock();
        ComponentOption option = ComponentOption.map(w);
        assertEquals(ComponentType.WATER_WHEEL, option.type());
    }

    @Test
    void map_Windmill_ReturnsCorrectRecord() {
        Windmill w = mock();
        ComponentOption option = ComponentOption.map(w);
        assertEquals(ComponentType.WINDMILL, option.type());
    }

    @Test
    void map_Consumer_ReturnsCorrectRecord() {
        Consumer c = mock();
        ComponentOption option = ComponentOption.map(c);
        assertEquals(ComponentType.CONSUMER, option.type());
    }
}