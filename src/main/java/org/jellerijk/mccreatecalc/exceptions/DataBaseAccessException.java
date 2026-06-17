package org.jellerijk.mccreatecalc.exceptions;

public class DataBaseAccessException extends RuntimeException {
    public DataBaseAccessException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
