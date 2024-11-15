package com.tpe.micro.scooters.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScooterResponseDTO {

    @JsonProperty("id_scooter")
    private long id_scooter;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("qr_Code")
    private String QR_Code;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("in_maintenance")
    private boolean in_maintenance;

    @JsonProperty("usage_time")
    private Double usage_time;

    @JsonProperty("km_traveled")
    private Double km_traveled;
}
