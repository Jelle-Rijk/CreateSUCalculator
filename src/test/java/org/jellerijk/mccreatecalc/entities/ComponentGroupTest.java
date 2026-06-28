package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentGroupTest {
    private static final String VALID_ID = "pbkdf";
    private static final int VALID_AMOUNT = 12;
    private ComponentGroup.Builder builder;

    @BeforeEach
    void setUp() {
        builder = ComponentGroup.Builder.aComponentGroup()
                .withComponent(mock())
                .withId(VALID_ID)
                .withAmount(VALID_AMOUNT);
    }

    //    === CONSTRUCTION ===
    @Test
    void constructor_validArgs_createsComponentGroup() {
        assertDoesNotThrow(() -> builder.build());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "    ", "\t", "\r", "\n"})
    void constructor_invalidId_throwsIAE(String invalidId) {
        assertThrows(IllegalArgumentException.class, () -> builder.withId(invalidId).build());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, Integer.MIN_VALUE})
    void constructor_invalidAmount_throwsIAE(int invalidAmount) {
        assertThrows(IllegalArgumentException.class, () -> builder.withAmount(invalidAmount).build());
    }

    @Test
    void constructor_minValidAmount_createsComponentGroup() {
        assertDoesNotThrow(() -> builder.withAmount(0).build());
    }

    @Test
    void constructor_ComponentIsNull_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> builder.withComponent(null).build());
    }

    //   === Calculate SU ===
    @Test
    void calculateSu_WithConsumer_ReturnsCorrectSuConsumption() {
        Consumer c = mock();
        when(c.getSuConsumption()).thenReturn(20);
        ComponentGroup group = builder.withComponent(c).withAmount(10).build();

        assertEquals(200, group.calculateSu());
    }

    @Test
    void calculateSu_WithGenerator_ReturnsCorrectSuConsumption() {
        Generator g = mock();
        when(g.getSuProduction()).thenReturn(20);
        ComponentGroup group = builder.withComponent(g).withAmount(10).build();
        assertEquals(200, group.calculateSu());
    }

    //    === GET COMPONENT TYPE ===
    @Test
    void getComponentType_WaterWheel_ReturnsWaterWheel() {
        WaterWheel w = mock();
        ComponentGroup group = builder.withComponent(w).build();
        assertEquals(ComponentType.WATER_WHEEL, group.getComponentType());
    }

    @Test
    void getComponentType_Windmill_ReturnsWindmill() {
        Windmill w = mock();
        ComponentGroup group = builder.withComponent(w).build();
        assertEquals(ComponentType.WINDMILL, group.getComponentType());
    }

    @Test
    void getComponentType_Consumer_ReturnsConsumer() {
        Consumer c = mock();
        ComponentGroup group = builder.withComponent(c).build();
        assertEquals(ComponentType.CONSUMER, group.getComponentType());
    }

}