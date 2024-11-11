package microservicioAdmin.feignClients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id_account;
    private LocalDate dateHigh;
    private Double balance;
}