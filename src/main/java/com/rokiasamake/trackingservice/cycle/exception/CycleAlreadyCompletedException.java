package com.rokiasamake.trackingservice.cycle.exception;

public class CycleAlreadyCompletedException
        extends RuntimeException {

    public CycleAlreadyCompletedException(String message) {
        super(message);
    }

}