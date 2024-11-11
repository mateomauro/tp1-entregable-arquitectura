package org.example.microservice_trip.dtos;

import lombok.Data;

import java.time.Duration;

@Data
public class PauseOfScooterDto {
    private long id_scooter;
    private int hours;
    private int minutes;

    public PauseOfScooterDto(long id_scooter, int hours, int minutes){
        this.id_scooter = id_scooter;
        this.hours = hours;
        this.minutes = minutes;
    }
}
