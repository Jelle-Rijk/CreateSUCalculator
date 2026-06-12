package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.components.ComponentType;

public class Windmill extends ComponentImpl implements Component {
    private static final int BLOCKS_PER_RPM = 8; // Blocks needed for a 1 RPM increase.
    private static final int MAX_BLOCKS = 128; // Maximum number of sails that count on the windmill.

    public Windmill(ComponentType type, int amountOfSails) {
        super(type, calculateRPM(amountOfSails));
    }

    private static int calculateRPM(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot have a negative number of windmill sails.");
        return amount > MAX_BLOCKS ? MAX_BLOCKS / BLOCKS_PER_RPM : amount / BLOCKS_PER_RPM;
    }
}
