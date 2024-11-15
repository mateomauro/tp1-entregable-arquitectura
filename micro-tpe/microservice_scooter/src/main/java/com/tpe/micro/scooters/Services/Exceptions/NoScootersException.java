package com.tpe.micro.scooters.Services.Exceptions;

public class NoScootersException extends RuntimeException {
    public NoScootersException(String message) {
        super("There are no scooters in the system.");
    }
}
