package org.jellerijk.mccreatecalc.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    private static final String VALID_IMG = "test.png";
    private static final String VALID_NAME = "Test-Generator";
    private static final int VALID_SU = 512;
    private GeneratorTestBuilder builder;

    @BeforeEach
    void setup() {
        builder = new GeneratorTestBuilder().withName(VALID_NAME).withImage(VALID_IMG).withSu(VALID_SU);
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
                () -> builder.withImage(invalidImg).build());
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
        Generator gen = builder.withSu(validSU).build();
        assertEquals(validSU, gen.getSuGeneration());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1})
    void constructor_invalidSuGeneration_ThrowsIAE(int invalidSU) {
        assertThrows(IllegalArgumentException.class,
                () -> builder.withSu(invalidSU).build());
    }

    //    === CalculateSUProduced() ===
    @Test
    void calculateSUProduced_matchesSuGeneration() {
        assertEquals(builder.build().getSuGeneration(), builder.build().calculateSUProduced());
    }

    private static class GeneratorTestBuilder {
        private String name;
        private String image;
        private int su;

        private GeneratorTestBuilder() {
        }

        private GeneratorTestBuilder withName(String name) {
            this.name = name;
            return this;
        }

        private GeneratorTestBuilder withImage(String image) {
            this.image = image;
            return this;
        }


        private GeneratorTestBuilder withSu(int su) {
            this.su = su;
            return this;
        }

        private Generator build() {
            return new Generator(name, image, su);
        }
    }


}