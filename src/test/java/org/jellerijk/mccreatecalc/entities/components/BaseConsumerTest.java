package org.jellerijk.mccreatecalc.entities.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BaseConsumerTest {
    private static final String VALID_NAME = "Consumer";
    private static final int VALID_RPM = 64;
    private static final int VALID_STRESS_IMPACT = 4;
    private BaseConsumer.Builder builder;

    @BeforeEach
    void setUp() {
        builder = BaseConsumer.Builder.aBaseConsumer()
                .withName(VALID_NAME)
                .withRpm(VALID_RPM)
                .withStressImpact(VALID_STRESS_IMPACT);
    }

    //    === CONSTRUCTOR ===
    @Test
    void constructor_ValidArgs_CreatesBaseConsumer() {
        BaseConsumer c = builder.build();
        assertEquals(BaseConsumer.class, c.getClass());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100, Integer.MIN_VALUE})
    void constructor_invalidRpm_throwsIAE(int invalidRpm) {
        assertThrows(IllegalArgumentException.class, () -> builder.withRpm(invalidRpm).build());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100, Integer.MIN_VALUE})
    void constructor_invalidStressImpact_throwsIAE(int invalidStressImpact) {
        assertThrows(IllegalArgumentException.class, () -> builder.withStressImpact(invalidStressImpact).build());
    }

    //    === Consumption Calculation
    @Test
    void getSuConsumption_64RPM_4Impact_Returns256() {
        BaseConsumer c = builder.withRpm(64).withStressImpact(4).build();
        assertEquals(256, c.getSuConsumption());
    }

    @Test
    void getSuConsumption_0RPM_Returns0() {
        BaseConsumer c = builder.withRpm(0).build();
        assertEquals(0, c.getSuConsumption());
    }

}