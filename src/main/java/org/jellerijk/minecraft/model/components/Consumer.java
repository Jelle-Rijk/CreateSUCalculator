package org.jellerijk.minecraft.model.components;

public interface Consumer extends Component {
    int getStressImpact();
    int getMinRPM();
    boolean hasMinRPMRequired();
}
