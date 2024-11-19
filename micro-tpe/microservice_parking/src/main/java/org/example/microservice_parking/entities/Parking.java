package org.example.microservice_parking.entities;

import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parkings")
public class Parking {
    @Id
    private Long id_parking;
    private Double latitude;
    private Double longitude;

    public Parking(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
