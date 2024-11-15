package com.tpe.micro.scooters.Services.Exceptions;

public class NoScootersFoundException extends RuntimeException {
    public NoScootersFoundException(String message) {
        super("No scooters found");
    }
}
