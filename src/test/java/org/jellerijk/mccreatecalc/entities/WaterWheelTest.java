package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class WaterWheelTest {
    private static final String VALID_IMG = "test.png";
    private static final String VALID_NAME = "Test-WaterWheel";
    private static final int VALID_SU = 512;

    private static final String DEFAULT_IMAGE = "create_cuckoo_clock.png";
    private WaterWheel smallWaterWheel;
    private WaterWheel largeWaterWheel;

    @BeforeEach
    void setup() {
        smallWaterWheel = new WaterWheel(WaterWheelType.SMALL);
        largeWaterWheel = new WaterWheel(WaterWheelType.LARGE);
    }

    @Test
    void constructor_smallWaterWheel_SetsCorrectValues() {
        assertEquals("Water Wheel", smallWaterWheel.getName());
        assertEquals(WaterWheelType.SMALL, smallWaterWheel.getSize());
        assertEquals(256, smallWaterWheel.getSuProduction());
        assertEquals(8, smallWaterWheel.getRpm());
        assertEquals("create_water_wheel.png", smallWaterWheel.getImg());
    }

    @Test
    void constructor_largeWaterWheel_SetsCorrectValues() {
        assertEquals("Large Water Wheel", largeWaterWheel.getName());
        assertEquals(WaterWheelType.LARGE, largeWaterWheel.getSize());
        assertEquals(512, largeWaterWheel.getSuProduction());
        assertEquals(4, largeWaterWheel.getRpm());
        assertEquals("create_large_water_wheel.png", largeWaterWheel.getImg());
    }
}