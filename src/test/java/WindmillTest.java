import org.jellerijk.minecraft.model.components.implementation.Windmill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindmillTest {
    private static final String VALID_NAME = "Windmill";
    private static final String VALID_IMG = "create_windmill.png";

    @Test
    public void windmillWith7blocks_correctOutputs() {
        Windmill w = new Windmill(VALID_NAME, VALID_IMG, 7);
        assertEquals(0, w.calculateSU());
        assertEquals(0, w.getRPM());
    }

    @Test
    public void windmillWith8blocks_correctOutputs() {
        Windmill w = new Windmill(VALID_NAME, VALID_IMG, 8);
        assertEquals(512, w.calculateSU());
        assertEquals(1, w.getRPM());
    }

    @Test
    public void windmillWith127blocks_correctOutputs() {
        Windmill w = new Windmill(VALID_NAME, VALID_IMG, 127);
        assertEquals(7680, w.calculateSU());
        assertEquals(15, w.getRPM());
    }

    @Test
    public void windmillWith128blocks_correctOutputs() {
        Windmill w = new Windmill(VALID_NAME, VALID_IMG, 128);
        assertEquals(8192, w.calculateSU());
        assertEquals(16, w.getRPM());
    }

    @Test
    public void windmillWith129blocks_correctOutputs() {
        Windmill w = new Windmill(VALID_NAME, VALID_IMG, 129);
        assertEquals(8192, w.calculateSU());
        assertEquals(16, w.getRPM());
    }
    @Test
    public void windmillWith1000blocks_correctOutputs() {
        Windmill w = new Windmill(VALID_NAME, VALID_IMG, 1000);
        assertEquals(8192, w.calculateSU());
        assertEquals(16, w.getRPM());
    }

}
