package com.project.bootcamp.exceptions;

import com.project.bootcamp.util.eMessageException;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super(eMessageException.NO_RECORDS_FOUND.getMessage());
    }

    public NotFoundException(String message) {
        super(message);
    }
}
