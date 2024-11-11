package com.tpe.micro.scooters.Services.Exceptions;

public class ScooterNotFoundException extends RuntimeException {
    public ScooterNotFoundException(String message, long id) {
        super("No scooter found with id = " + id + ".");
    }
}
