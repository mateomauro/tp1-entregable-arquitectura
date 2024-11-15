package microservicioAdmin.feignClients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDTO {
    private long id_scooter;
    private boolean repair;
}
