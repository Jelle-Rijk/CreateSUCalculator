package org.jellerijk.mccreatecalc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StressNetworkTest {
    private static final String VALID_NAME = "Test-Network";
    private static final String VALID_ID = "Test123";
    private StressNetwork.Builder builder;

    @BeforeEach
    void setUp() {
        builder = StressNetwork.Builder.aStressNetwork()
                .withId(VALID_ID)
                .withName(VALID_NAME)
                .withGenerators(new ArrayList<>())
                .withConsumers(new ArrayList<>());
    }

//    === CONSTRUCTOR ===

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "    ", "\t", "\r", "\n"})
    void constructor_invalidId_throwsIAE(String invalidId) {
        assertThrows(IllegalArgumentException.class, () -> builder.withId(invalidId).build());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "    ", "\t", "\r", "\n"})
    void constructor_invalidName_throwsIAE(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> builder.withName(invalidName).build());
    }

    @Test
    void constructor_generatorsIsNull_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> builder.withGenerators(null).build());
    }

    @Test
    void constructor_generatorsContainsConsumer_ThrowsIAE() {
        ComponentGroup consumer = mock();
        when(consumer.isConsumer()).thenReturn(true);
        List<ComponentGroup> generators = List.of(consumer);
        assertThrows(IllegalArgumentException.class, () -> builder.withGenerators(generators).build());
    }

    @Test
    void constructor_consumersIsNull_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> builder.withConsumers(null).build());
    }

    @Test
    void construct_consumersContainsNonConsumers_throwsIAE() {
        ComponentGroup group = mock();
        when(group.isConsumer()).thenReturn(false);
        List<ComponentGroup> consumers = List.of(group);
        assertThrows(IllegalArgumentException.class, () -> builder.withConsumers(consumers).build());
    }

//    === SU CALCULATION ===

    //    === calculateSUProduced() ===
    @Test
    void calculateSUProduced_returnsCorrectValue() {
        ComponentGroup group1 = mock();
        ComponentGroup group2 = mock();
        when(group1.calculateSu()).thenReturn(100);
        when(group2.calculateSu()).thenReturn(200);
        StressNetwork network = builder.withGenerators(List.of(group1, group2)).build();

        assertEquals(300, network.calculateSUProduced());
    }

    // === calculateSUConsumed ===
    @Test
    void calculateSUConsumed_returnsCorrectValue() {
        ComponentGroup group1 = mock();
        ComponentGroup group2 = mock();
        when(group1.calculateSu()).thenReturn(600);
        when(group2.calculateSu()).thenReturn(900);
        when(group1.isConsumer()).thenReturn(true);
        when(group2.isConsumer()).thenReturn(true);
        List<ComponentGroup> consumers = List.of(group1, group2);
        StressNetwork network = builder.withConsumers(consumers).build();
        assertEquals(1500, network.calculateSUConsumed());
    }

    @Test
    void calculateSUConsumed_NoConsumers_Returns0() {
        StressNetwork network = builder.withConsumers(new ArrayList<>()).build();
        assertEquals(0, network.calculateSUConsumed());
    }

    // === Calculate SU Balance ===
    @Test
    void calculateSuBalance_NoComponents_Returns0() {
        assertEquals(0, builder.build().calculateSUConsumed());
    }

    @Test
    void calculateSuBalance_OnlyConsumers_ReturnsCorrectValue() {
        ComponentGroup group1 = mock();
        ComponentGroup group2 = mock();
        when(group1.calculateSu()).thenReturn(100);
        when(group2.calculateSu()).thenReturn(200);
        when(group1.isConsumer()).thenReturn(true);
        when(group2.isConsumer()).thenReturn(true);
        List<ComponentGroup> consumers = List.of(group1, group2);

        assertEquals(-300, builder.withConsumers(consumers).build().calculateSUBalance());
    }

    @Test
    void calculateSuBalance_OnlyProducers_ReturnsCorrectValue() {
        ComponentGroup group1 = mock();
        ComponentGroup group2 = mock();
        when(group1.calculateSu()).thenReturn(200);
        when(group2.calculateSu()).thenReturn(300);
        List<ComponentGroup> generators = List.of(group1, group2);

        assertEquals(500, builder.withGenerators(generators).build().calculateSUBalance());
    }

    @Test
    void calculateSuBalance_ConsumersAndProducers_ReturnsCorrectValue() {
        ComponentGroup consumer1 = mock();
        ComponentGroup consumer2 = mock();
        ComponentGroup generator1 = mock();
        ComponentGroup generator2 = mock();

        when(consumer1.calculateSu()).thenReturn(100);
        when(consumer2.calculateSu()).thenReturn(250);
        when(consumer1.isConsumer()).thenReturn(true);
        when(consumer2.isConsumer()).thenReturn(true);
        when(generator1.calculateSu()).thenReturn(300);
        when(generator2.calculateSu()).thenReturn(200);

        List<ComponentGroup> consumers = List.of(consumer1, consumer2);
        List<ComponentGroup> generators = List.of(generator1, generator2);
        StressNetwork network = builder.withConsumers(consumers).withGenerators(generators).build();

        int expected = 300 + 200 - 100 - 250;
        assertEquals(expected, network.calculateSUBalance());
    }
}