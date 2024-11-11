package microservicioAdmin.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRate;
    @Column
    private Double price;
    @Column
    private Double priceForPause;
    @Column
    private LocalDate date; //ver otro nombre q quede mejor q solo fecha

}
