package com.ericsson.oss.services.api;

/**
 * To indicate that no custom implementation exists.
 */
public class NoImplementationFoundException extends RuntimeException {

    public NoImplementationFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
