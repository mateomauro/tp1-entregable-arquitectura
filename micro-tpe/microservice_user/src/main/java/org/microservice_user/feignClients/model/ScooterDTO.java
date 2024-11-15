package org.microservice_user.feignClients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScooterDTO {

    private long id_scooter;
    private Double latitude;
    private Double longitude;
    private String QR_Code;
    private boolean available;
    private boolean in_maintenance;
    private Double usage_time;
    private Double km_traveled;
}
