package org.jellerijk.mccreatecalc.exceptions;

public class NetworkNotFoundException extends RuntimeException {
    public NetworkNotFoundException(String id) {
        super(String.format("Network with id %s was not found.", id));
    }
}
