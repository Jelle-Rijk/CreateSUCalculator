package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ConstantGeneratorTest {
    private static final String VALID_IMG = "test.png";
    private static final String VALID_NAME = "Test-ConstantGenerator";
    private static final int VALID_SU = 512;
    private ConstantGenerator.Builder builder;

    @BeforeEach
    void setup() {
        builder = ConstantGenerator.Builder.aConstantGenerator()
                .withName(VALID_NAME)
                .withImg(VALID_IMG)
                .withSuGeneration(VALID_SU)
                .withRpm(8);
    }

    //    ==================== FIELD - IMG ====================
    @Test
    void getImg_Valid_ReturnsNonEmptyOptional() {
        assertTrue(builder.build().getImg().isPresent());
    }

    @Test
    void getImg_Valid_ReturnsCorrectString() {
        assertEquals(VALID_IMG, builder.build().getImg().orElseThrow());
    }


    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"  ", "\t", "\r", "\n", "test", ".png", "test,png"})
    void constructor_InvalidImg_ThrowsIAE(String invalidImg) {
        assertThrows(IllegalArgumentException.class,
                () -> builder.withImg(invalidImg).build());
    }

    // ==================== FIELD - NAME ====================

    @Test
    void getName_Valid_Returns() {
        assertEquals(VALID_NAME, builder.build().getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\r", "\n"})
    void constructor_InvalidName_ThrowsIAE(String invalidName) {
        assertThrows(IllegalArgumentException.class,
                () -> builder.withName(invalidName).build());
    }

//    ==================== FIELD - SU ====================

    @ParameterizedTest
    @ValueSource(ints = {0, 1, VALID_SU, Integer.MAX_VALUE})
    void getSuGeneration_Valid_Returns(int validSU) {
        ConstantGenerator gen = builder.withSuGeneration(validSU).build();
        assertEquals(validSU, gen.getSuGeneration());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1})
    void constructor_invalidSuGeneration_ThrowsIAE(int invalidSU) {
        assertThrows(IllegalArgumentException.class,
                () -> builder.withSuGeneration(invalidSU).build());
    }

    //    === CalculateSUProduced() ===
    @Test
    void calculateSUProduced_matchesSuGeneration() {
        assertEquals(builder.build().getSuGeneration(), builder.build().calculateSuProduced());
    }
}