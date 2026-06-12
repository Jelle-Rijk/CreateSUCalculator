import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.components.implementation.ComponentImpl;
import org.jellerijk.minecraft.model.components.implementation.ComponentTypeImpl;
import org.jellerijk.minecraft.model.network.StressNetwork;
import org.jellerijk.minecraft.model.network.implementations.StressNetworkImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StressNetworkTest {
    private static final String VALID_NAME = "Andesite factory";
    private static final String VALID_ID = "123456";
    private static final Component WATER_WHEEL = new ComponentImpl(new ComponentTypeImpl("Water wheel", "waterwheel.png", 32, true, 0, 8));
    private static final Component MILLSTONE_16RPM = new ComponentImpl(new ComponentTypeImpl("Millstone", "mill_stone.png", 4, false, 0, null), 16);
    private static final Map<Component, Integer> VALID_GENERATORS = new HashMap<>();
    private static final Map<Component, Integer> VALID_CONSUMERS = new HashMap<>();

    @BeforeAll
    static void setUpGeneratorsAndConsumers() {
        VALID_GENERATORS.put(WATER_WHEEL, 4);
        VALID_CONSUMERS.put(MILLSTONE_16RPM, 2);
    }

    @Test
    public void constructor_validArgs_gettersReturnCorrectValues() {
        StressNetwork s = new StressNetworkImpl(VALID_ID, VALID_NAME, VALID_GENERATORS, VALID_CONSUMERS);
        assertEquals(VALID_ID, s.getId());
        assertEquals(VALID_NAME, s.getName());
        assertEquals(VALID_GENERATORS, s.getGenerators());
        assertEquals(VALID_CONSUMERS, s.getConsumers());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\n", "\t\t"})
    public void constructor_invalidId_throwsIAE(String invalidId) {
        assertThrows(IllegalArgumentException.class,
                () -> new StressNetworkImpl(invalidId, VALID_NAME, VALID_GENERATORS, VALID_CONSUMERS));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\n", "\t\t"})
    public void constructor_invalidName_throwsIAE(String invalidName) {
        assertThrows(IllegalArgumentException.class,
                () -> new StressNetworkImpl(VALID_ID, invalidName, VALID_GENERATORS, VALID_CONSUMERS));
    }

    @Test
    public void constructor_invalidGenerators_throwsIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new StressNetworkImpl(VALID_ID, VALID_NAME, null, VALID_CONSUMERS));
    }

    @Test
    public void constructor_invalidConsumers_throwsIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new StressNetworkImpl(VALID_ID, VALID_NAME, VALID_GENERATORS, null));
    }

    @Test
    public void constructor_emptyGenerators_createsObject() {
        assertDoesNotThrow(() -> new StressNetworkImpl(VALID_ID, VALID_NAME, new HashMap<>(), VALID_CONSUMERS));
    }

    @Test
    public void constructor_emptyConsumers_createsObject() {
        assertDoesNotThrow(() -> new StressNetworkImpl(VALID_ID, VALID_NAME, VALID_GENERATORS, new HashMap<>()));
    }

    @Test
    public void calculateSUProduction_returnsCorrectValue() {
        StressNetwork s = new StressNetworkImpl(VALID_ID, VALID_NAME, VALID_GENERATORS, VALID_CONSUMERS);
        int totalSU = 256 * 4;
        assertEquals(totalSU, s.calculateTotalSUProduced());
    }

    @Test
    public void calculateSUConsumption_returnsCorrectValue() {
        StressNetwork s = new StressNetworkImpl(VALID_ID, VALID_NAME, VALID_GENERATORS, VALID_CONSUMERS);
        int totalSU = 16 * 4 * 2;
        assertEquals(totalSU, s.calculateTotalSUConsumed());
    }


}
