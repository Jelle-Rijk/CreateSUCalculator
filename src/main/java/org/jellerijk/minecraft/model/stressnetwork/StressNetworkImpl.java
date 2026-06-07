package org.jellerijk.minecraft.model.stressnetwork;

import java.util.List;


public class StressNetworkImpl implements StressNetwork {
    List<GeneratorEntry> generators;
    List<ConsumerEntry> consumers;

    public StressNetworkImpl(List<GeneratorEntry> generators, List<ConsumerEntry> consumers) {
        if (generators == null)
            throw new IllegalArgumentException("Generators cannot be null");
        if (consumers == null)
            throw new IllegalArgumentException("Consumers cannot be null");
        this.generators = List.copyOf(generators);
        this.consumers = List.copyOf(consumers);
    }

    @Override
    public int calculateTotalSUProduced() {
        return calculateTotalSU(generators);
    }

    @Override
    public int calculateTotalSUConsumed() {
        return calculateTotalSU(consumers);
    }


    /**
     * Calculates the total SU consumption or production from a list of ComponentEntries.
     *
     * @param entries The list to calculate the total for.
     * @return The total amount of SU produced or consumed.
     */
    private <T extends ComponentEntry<?>> int calculateTotalSU(List<T> entries) {
        return entries.stream().map(ComponentEntry::calculateTotalSU).reduce(0, Integer::sum);
    }


}
