package org.example.microservicemaintenance.exception;

public class MaintenanceNotFoundException extends RuntimeException{

    public MaintenanceNotFoundException(String message) {
        super(message);
    }
}
