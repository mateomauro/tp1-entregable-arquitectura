package microservicioAdmin.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Data
@ToString
@NoArgsConstructor
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

    public Rate(Double price, Double priceForPause){
        this.price = price;
        this.priceForPause = priceForPause;
        this.date = LocalDate.now();
    }

}
