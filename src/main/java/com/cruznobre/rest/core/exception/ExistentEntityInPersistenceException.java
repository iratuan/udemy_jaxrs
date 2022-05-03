package com.cruznobre.rest.core.exception;

import jakarta.ejb.ApplicationException;
import jakarta.persistence.PersistenceException;

@ApplicationException(rollback = true)
public class ExistentEntityInPersistenceException extends PersistenceException {
    public ExistentEntityInPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistentEntityInPersistenceException(String message) {
        super(message);
    }
}
