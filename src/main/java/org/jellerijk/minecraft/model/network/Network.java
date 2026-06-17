package org.jellerijk.minecraft.model.network;

import org.jellerijk.minecraft.model.component.NetworkComponent;

import java.util.Map;

public interface Network {
    String id();

    String name();

    Map<NetworkComponent, Integer> generators();

    Map<NetworkComponent, Integer> consumers();

    int calculateProducedSU();

    int calculateConsumedSU();

    int calculateSUBalance();

    boolean isOverstressed();
}
