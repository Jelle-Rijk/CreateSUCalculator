package org.jellerijk.minecraft.services.usecases.viewNetwork;

public class NetworkNotFoundException extends RuntimeException {
    public NetworkNotFoundException(String id) {
        super(String.format("Network with id %s could not be found.", id));
    }
}
