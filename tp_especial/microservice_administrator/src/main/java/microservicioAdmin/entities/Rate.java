package microservicioAdmin.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Data
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
    private LocalDate date;

    public Rate(){
        this.price = 1500.0;
        this.priceForPause = 3000.0;
        this.date = LocalDate.now();
    }
}
