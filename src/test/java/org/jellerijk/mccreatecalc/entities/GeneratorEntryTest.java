package org.jellerijk.mccreatecalc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeneratorEntryTest {
    private Generator generator;
    private static final int DEFAULT_AMOUNT = 4;
    private GeneratorEntryTestBuilder builder;

    @BeforeEach
    void setUp() {
        generator = mock();
        builder = new GeneratorEntryTestBuilder().withGenerator(generator).withAmount(DEFAULT_AMOUNT);
    }

    //    === FIELD - GENERATOR ===
    @ParameterizedTest
    @NullSource
    void constructor_invalidGenerator_throwsIAE(Generator invalidGenerator) {
        assertThrows(IllegalArgumentException.class, () -> builder.withGenerator(invalidGenerator).build());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void amount_invalidAmount_throwsIAE(int invalidAmount) {
        assertThrows(IllegalArgumentException.class, () -> builder.withAmount(invalidAmount).build());
    }

    @Test
    void constructor_validArgs_CreatesEntry() {
        assertDoesNotThrow(() -> builder.build());
    }

    //    === CALCULATE SU PRODUCED ===
    @Test
    void calculateSUProduced_returnsCorrectValue() {
        int genSU = 512;
        int expected = genSU * DEFAULT_AMOUNT;
        when(generator.getSuGeneration()).thenReturn(512);
        assertEquals(expected, builder.build().calculateSUProduced());
    }

    private static class GeneratorEntryTestBuilder {
        private Generator generator;
        private int amount;

        private GeneratorEntryTestBuilder() {
        }

        private GeneratorEntryTestBuilder withGenerator(Generator generator) {
            this.generator = generator;
            return this;
        }

        private GeneratorEntryTestBuilder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        private GeneratorEntry build() {
            return new GeneratorEntry(generator, amount);
        }
    }

}