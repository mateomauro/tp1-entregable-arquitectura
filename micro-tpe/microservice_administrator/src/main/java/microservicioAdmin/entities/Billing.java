package microservicioAdmin.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billing implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_billing;
  @Column
  private LocalDate date;
  @Column
  private Double totalTrip;

  public Billing(LocalDate date, Double totalTrip) {
    this.date = date;
    this.totalTrip = totalTrip;
  }
}
