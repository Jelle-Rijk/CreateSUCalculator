package org.jellerijk.mccreatecalc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    private static final String VALID_IMG = GeneratorTestBuilder.DEFAULT_IMG;
    private static final String VALID_NAME = GeneratorTestBuilder.DEFAULT_NAME;
    private static final int VALID_SU = GeneratorTestBuilder.DEFAULT_SU;
    private Generator defaultGenerator;

    @BeforeEach
    void setup() {
        defaultGenerator = GeneratorTestBuilder.defaultGenerator().build();
    }

    //    ==================== FIELD - IMG ====================
    @Test
    void getImg_Valid_ReturnsNonEmptyOptional() {
        assertTrue(defaultGenerator.getImg().isPresent());
    }

    @Test
    void getImg_Valid_ReturnsCorrectString() {
        assertEquals(VALID_IMG, defaultGenerator.getImg().orElseThrow());
    }


    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"  ", "\t", "\r", "\n", "test", ".png", "test,png"})
    void constructor_InvalidImg_ThrowsIAE(String invalidImg) {
        assertThrows(IllegalArgumentException.class, () -> GeneratorTestBuilder.defaultGenerator()
                .withImg(invalidImg)
                .build());
    }

    // ==================== FIELD - NAME ====================

    @Test
    void getName_Valid_Returns() {
        assertEquals(VALID_NAME, defaultGenerator.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\r", "\n"})
    void constructor_InvalidName_ThrowsIAE(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> GeneratorTestBuilder.defaultGenerator()
                .withName(invalidName)
                .build());
    }

//    ==================== FIELD - SU ====================

    @ParameterizedTest
    @ValueSource(ints = {0, 1, VALID_SU, Integer.MAX_VALUE})
    void getSuGeneration_Valid_Returns(int validSU) {
        Generator gen = GeneratorTestBuilder.defaultGenerator().withSuGeneration(validSU).build();
        assertEquals(validSU, gen.getSuGeneration());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1})
    void constructor_invalidSuGeneration_ThrowsIAE(int invalidSU) {
        assertThrows(IllegalArgumentException.class, () -> GeneratorTestBuilder.defaultGenerator()
                .withSuGeneration(invalidSU).build());
    }

    //    === CalculateSUProduced() ===
    @Test
    void calculateSUProduced_matchesSuGeneration() {
        assertEquals(defaultGenerator.getSuGeneration(), defaultGenerator.calculateSUProduced());
    }


}