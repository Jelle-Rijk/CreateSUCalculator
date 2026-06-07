package org.jellerijk.minecraft.model.component.data;

public class ComponentData {
    private final String name;
    private final int stressImpact;

    public ComponentData(String name, int stressImpact) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank.");
        if (stressImpact <= 0)
            throw new IllegalArgumentException("Stress Impact needs to be a positive integer.");
        this.name = name;
        this.stressImpact = stressImpact;
    }

    /**
     * @return The in-game name of this type of component.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The amount of stress to be used or generated per RPM.
     */
    public int getStressImpact() {
        return stressImpact;
    }

}
