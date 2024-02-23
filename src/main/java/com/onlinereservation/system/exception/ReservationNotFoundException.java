package com.onlinereservation.system.exception;

// ReservationNotFoundException.java
public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
