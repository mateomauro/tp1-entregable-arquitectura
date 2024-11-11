package microservicioAdmin.feignClients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
        private long id_scooter;
        private long id_account;
        private Date start_date;
        private Date end_date;
        private int km_traveled;

}
