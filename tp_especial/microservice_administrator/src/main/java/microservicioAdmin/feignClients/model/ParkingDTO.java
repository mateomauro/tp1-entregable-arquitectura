package microservicioAdmin.feignClients.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDTO {
    private int id_parking;
    private Double latitude;
    private Double longitude;
}
