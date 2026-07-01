package com.rokiasamake.trackingservice.cycle.exception;

public class CycleAlreadyOngoingException
        extends RuntimeException {

    public CycleAlreadyOngoingException(String message) {
        super(message);
    }

}