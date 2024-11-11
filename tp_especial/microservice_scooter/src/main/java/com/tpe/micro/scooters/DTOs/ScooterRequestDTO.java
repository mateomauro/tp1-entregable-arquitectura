package com.tpe.micro.scooters.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScooterRequestDTO {

    @NotEmpty(message = "The field 'latitude' cannot be empty")
    @NotNull(message = "The field 'latitude' is required")
    private Double latitude;

    @NotEmpty(message = "The field 'longitude' cannot be empty")
    @NotNull(message = "The field 'longitude' is required")
    private Double longitude;

    @NotEmpty(message = "The field 'QR_Code' cannot be empty")
    @NotNull(message = "The field 'QR_Code' is required")
    private String QR_Code;

    @NotEmpty(message = "The field 'available' cannot be empty")
    @NotNull(message = "The field 'available' is required")
    private boolean available;

    @NotEmpty(message = "The field 'in_maintenance' cannot be empty")
    @NotNull(message = "The field 'in_maintenance' is required")
    private boolean in_maintenance;

    @NotEmpty(message = "The field 'usage_time' cannot be empty")
    @NotNull(message = "The field 'usage_time' is required")
    private Double usage_time;

    @NotEmpty(message = "The field 'km_traveled' cannot be empty")
    @NotNull(message = "The field 'km_traveled' is required")
    private Double km_traveled;
}
