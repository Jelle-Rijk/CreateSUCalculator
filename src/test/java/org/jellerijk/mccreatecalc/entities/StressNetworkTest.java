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


//    === SU CALCULATION ===

    @Test
    void calculateSUConsumed() {
    }

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

}