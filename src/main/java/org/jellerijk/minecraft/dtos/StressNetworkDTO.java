package org.jellerijk.minecraft.dtos;

import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.network.StressNetwork;

import java.util.HashMap;
import java.util.Map;

public record StressNetworkDTO(String id, String name, Map<NetworkComponentDTO, Integer> generators,
                               Map<NetworkComponentDTO, Integer> consumers, int suConsumed, int suProduced,
                               int suBalance, boolean overstressed) {

    public static StressNetworkDTO from(StressNetwork s) {
        Map<NetworkComponentDTO, Integer> generators = mapComponents(s.getGenerators());
        Map<NetworkComponentDTO, Integer> consumers = mapComponents(s.getConsumers());
        return new StressNetworkDTO(s.getId(), s.getName(), generators, consumers, s.calculateTotalSUConsumed(), s.calculateTotalSUProduced(), s.calculateSUBalance(), s.isOverstressed());
    }

    private static Map<NetworkComponentDTO, Integer> mapComponents(Map<Component, Integer> components) {
        Map<NetworkComponentDTO, Integer> mappedGenerators = new HashMap<>(components.size());
        for (Map.Entry<Component, Integer> entry : components.entrySet())
            mappedGenerators.put(NetworkComponentDTO.from(entry.getKey()), entry.getValue());
        return mappedGenerators;
    }
}
