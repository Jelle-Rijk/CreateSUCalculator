package org.jellerijk.mccreatecalc.entities.components;

import org.jellerijk.mccreatecalc.entities.Windmill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WindmillTest {
    private static final String DEFAULT_NAME = "Windmill";
    private static final String DEFAULT_IMAGE = "create_white_sail.png";
    private WindmillImpl.Builder builder;


    @BeforeEach
    void setUp() {
        builder = WindmillImpl.Builder.aWindmillImpl().withSails(8);
    }

    @Test
    void constructor_validArgs_setsDefaultName() {
        Windmill w = builder.build();
        assertEquals(DEFAULT_NAME, w.getName());
    }

    @Test
    void constructor_validArgs_setsDefaultImage() {
        Windmill w = builder.build();
        assertEquals(DEFAULT_IMAGE, w.getImg());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -35, Integer.MIN_VALUE})
    void constructor_invalidSails_throwsIAE(int invalidSails) {
        assertThrows(IllegalArgumentException.class, () -> builder.withSails(invalidSails).build());
    }

    //    RPM calculation
    @Test
    void getRpm_0Sails_returns0() {
        Windmill w = builder.withSails(0).build();
        assertEquals(0, w.getRPM());
    }

    @Test
    void getRpm_7Sails_returns0() {
        Windmill w = builder.withSails(7).build();
        assertEquals(0, w.getRPM());
    }

    @Test
    void getRpm_8Sails_returns1() {
        Windmill w = builder.withSails(8).build();
        assertEquals(1, w.getRPM());
    }

    @Test
    void getRpm_9Sails_returns1() {
        Windmill w = builder.withSails(9).build();
        assertEquals(1, w.getRPM());
    }

    @Test
    void getRPM_16Sails_returns2() {
        Windmill w = builder.withSails(16).build();
        assertEquals(2, w.getRPM());
    }

    @Test
    void getRPM_128_returns16() {
        Windmill w = builder.withSails(128).build();
        assertEquals(16, w.getRPM());
    }

    @ParameterizedTest
    @ValueSource(ints = {129, 300, Integer.MAX_VALUE})
    void getRpm_moreThan128Sails_returns16(int sails) {
        Windmill w = builder.withSails(sails).build();
        assertEquals(16, w.getRPM());
    }

//    === SU Production ===
    @Test
    void getSuProduction_0Sails_returns0() {
        Windmill w = builder.withSails(0).build();
        assertEquals(0, w.getSuProduction());
    }

    @Test
    void getSuProduction_7Sails_returns0() {
        Windmill w = builder.withSails(7).build();
        assertEquals(0, w.getSuProduction());
    }

    @Test
    void getSuProduction_8Sails_returns512() {
        Windmill w = builder.withSails(8).build();
        assertEquals(512, w.getSuProduction());
    }

    @ParameterizedTest
    @ValueSource(ints = {128, 129, 300, Integer.MAX_VALUE})
    void getSuProduction_MaxSails_returns8192(int sails) {
        Windmill w = builder.withSails(sails).build();
        assertEquals(8192, w.getSuProduction());
    }
}