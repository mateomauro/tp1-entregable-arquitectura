package org.example.microservicemaintenance.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScooterResponseDTO {

    private long id_scooter;
    private Double latitude;
    private Double longitude;
    private String QR_Code;
    private boolean available;
    private boolean in_maintenance;
    private Double usage_time;
    private Double km_traveled;

}
