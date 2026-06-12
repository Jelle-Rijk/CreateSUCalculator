import org.jellerijk.minecraft.model.components.ComponentType;
import org.jellerijk.minecraft.model.components.implementation.ComponentTypeImpl;
import org.jellerijk.minecraft.model.components.implementation.Windmill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindmillTest {
    private static final String VALID_NAME = "Windmill";
    private static final String VALID_IMG = "create_windmill.png";
    private static final ComponentType WINDMILL_TYPE = new ComponentTypeImpl("Windmill", "create_windmill.png", 512, true, 0, null);

    @Test
    public void windmillWith7blocks_correctOutputs() {

        Windmill w = new Windmill(WINDMILL_TYPE, 7);
        assertEquals(0, w.calculateSU());
        assertEquals(0, w.getRpm());
    }

    @Test
    public void windmillWith8blocks_correctOutputs() {
        Windmill w = new Windmill(WINDMILL_TYPE, 8);
        assertEquals(512, w.calculateSU());
        assertEquals(1, w.getRpm());
    }

    @Test
    public void windmillWith127blocks_correctOutputs() {
        Windmill w = new Windmill(WINDMILL_TYPE, 127);
        assertEquals(7680, w.calculateSU());
        assertEquals(15, w.getRpm());
    }

    @Test
    public void windmillWith128blocks_correctOutputs() {
        Windmill w = new Windmill(WINDMILL_TYPE, 128);
        assertEquals(8192, w.calculateSU());
        assertEquals(16, w.getRpm());
    }

    @Test
    public void windmillWith129blocks_correctOutputs() {
        Windmill w = new Windmill(WINDMILL_TYPE, 129);
        assertEquals(8192, w.calculateSU());
        assertEquals(16, w.getRpm());
    }

    @Test
    public void windmillWith1000blocks_correctOutputs() {
        Windmill w = new Windmill(WINDMILL_TYPE, 1000);
        assertEquals(8192, w.calculateSU());
        assertEquals(16, w.getRpm());
    }

}
