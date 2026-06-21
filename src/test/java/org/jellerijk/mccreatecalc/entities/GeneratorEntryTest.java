package org.jellerijk.mccreatecalc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorEntryTest {
    private GeneratorEntry defaultEntry;

    @BeforeEach
    void setUp() {
        defaultEntry = GeneratorEntryTestBuilder.defaultGeneratorEntry().build();
    }

    //    === FIELD - GENERATOR ===
    @ParameterizedTest
    @NullSource
    void generator_invalidGenerator_throwsIAE(Generator invalidGenerator) {
        assertThrows(IllegalArgumentException.class, () -> GeneratorEntryTestBuilder.defaultGeneratorEntry()
                .withGenerator(invalidGenerator)
                .build());
    }

    @Test
    void generator_valid_returnsCorrectValue() {
        assertEquals(GeneratorEntryTestBuilder.DEFAULT_GENERATOR, defaultEntry.getGenerator());
    }

    //    === FIELD - AMOUNT ===
    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void amount_invalidAmount_throwsIAE(int invalidAmount) {
        assertThrows(IllegalArgumentException.class, () -> GeneratorEntryTestBuilder.defaultGeneratorEntry()
                .withAmount(invalidAmount).build());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, GeneratorEntryTestBuilder.DEFAULT_AMOUNT, Integer.MAX_VALUE})
    void amount_valid_returnsCorrectValue(int amount) {
        GeneratorEntry entry = GeneratorEntryTestBuilder.defaultGeneratorEntry().withAmount(amount).build();
        assertEquals(amount, entry.getAmount());
    }

    //    === CALCULATE SU PRODUCED ===
    @Test
    void calculateSUProduced_returnsCorrectValue() {
        int genSU = 3000;
        int amount = 5;
        int expected = genSU * amount;

        Generator gen = GeneratorTestBuilder.defaultGenerator().withSuGeneration(genSU).build();
        GeneratorEntry entry = GeneratorEntryTestBuilder.defaultGeneratorEntry()
                .withGenerator(gen)
                .withAmount(amount)
                .build();
        assertEquals(expected, entry.calculateSUProduced());
    }

}