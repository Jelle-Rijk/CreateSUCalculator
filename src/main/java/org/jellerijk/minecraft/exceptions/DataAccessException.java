package org.jellerijk.minecraft.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
