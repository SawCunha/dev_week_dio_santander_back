package com.project.bootcamp.util;

public enum eMessageException {

    STOCK_ALREADY_EXISTS("Stock already exists in the database"),
    NO_RECORDS_FOUND("No records found");

    private final String message;

    eMessageException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
