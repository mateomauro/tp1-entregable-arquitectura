package microservicioAdmin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingDTO {
  private Long id_billing;
  private LocalDate date;
  private Double totalTrip;
}
