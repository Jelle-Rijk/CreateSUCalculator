package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;
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
    void constructor_consumersIsNull_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> builder.withConsumers(null).build());
    }

    @Test
    void construct_consumersContainsNonConsumers_throwsIAE() {
        ComponentGroup group = ComponentGroup.Builder.aComponentGroup().withType(ComponentType.WATER_WHEEL).build();
        List<ComponentGroup> consumers = List.of(group);
        assertThrows(IllegalArgumentException.class, () -> builder.withConsumers(consumers).build());
    }

//    === SU CALCULATION ===

    //    === calculateSUProduced() ===
    @Test
    void calculateSUProduced_returnsCorrectValue() {
        GeneratorEntry entry1 = mock();
        GeneratorEntry entry2 = mock();
        when(entry1.calculateSUProduced()).thenReturn(100);
        when(entry2.calculateSUProduced()).thenReturn(200);
        StressNetwork network = builder.withGenerators(List.of(entry1, entry2)).build();

        assertEquals(300, network.calculateSUProduced());
    }

    // === calculateSUConsumed ===
    @Test
    void calculateSUConsumed_returnsCorrectValue() {
        ComponentGroup group1 = ComponentGroup.Builder.aComponentGroup()
                .withType(ComponentType.CONSUMER)
                .withSu(600)
                .build();
        ComponentGroup group2 = ComponentGroup.Builder.aComponentGroup()
                .withType(ComponentType.CONSUMER)
                .withSu(900)
                .build();
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
        when(group1.su()).thenReturn(100);
        when(group2.su()).thenReturn(200);
        when(group1.type()).thenReturn(ComponentType.CONSUMER);
        when(group2.type()).thenReturn(ComponentType.CONSUMER);
        List<ComponentGroup> consumers = List.of(group1, group2);

        assertEquals(-300, builder.withConsumers(consumers).build().calculateSUBalance());
    }

    @Test
    void calculateSuBalance_OnlyProducers_ReturnsCorrectValue() {
        GeneratorEntry entry1 = mock();
        GeneratorEntry entry2 = mock();
        when(entry1.calculateSUProduced()).thenReturn(200);
        when(entry2.calculateSUProduced()).thenReturn(300);
        List<GeneratorEntry> generatorEntries = List.of(entry1, entry2);

        assertEquals(500, builder.withGenerators(generatorEntries).build().calculateSUBalance());
    }

    @Test
    void calculateSuBalance_ConsumersAndProducers_ReturnsCorrectValue() {
        ComponentGroup consumer1 = mock();
        ComponentGroup consumer2 = mock();
        GeneratorEntry generator1 = mock();
        GeneratorEntry generator2 = mock();

        when(consumer1.su()).thenReturn(100);
        when(consumer2.su()).thenReturn(250);
        when(consumer1.type()).thenReturn(ComponentType.CONSUMER);
        when(consumer2.type()).thenReturn(ComponentType.CONSUMER);
        when(generator1.calculateSUProduced()).thenReturn(300);
        when(generator2.calculateSUProduced()).thenReturn(200);

        List<ComponentGroup> consumers = List.of(consumer1, consumer2);
        List<GeneratorEntry> generators = List.of(generator1, generator2);
        StressNetwork network = builder.withConsumers(consumers).withGenerators(generators).build();

        int expected = 300 + 200 - 100 - 250;
        assertEquals(expected, network.calculateSUBalance());
    }
}