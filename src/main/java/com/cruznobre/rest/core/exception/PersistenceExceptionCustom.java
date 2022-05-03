package com.cruznobre.rest.core.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class PersistenceExceptionCustom extends Exception {
    public PersistenceExceptionCustom(String message, Throwable cause) {
        super(message, cause);
    }
}
