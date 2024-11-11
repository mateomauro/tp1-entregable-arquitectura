package org.example.microservice_trip.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PauseOfScooterDto {
    private long id_scooter;
    private int hours;
    private int minutes;
}
