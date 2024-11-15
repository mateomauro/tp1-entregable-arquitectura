package microservicioAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservicioAdmin.entities.Rate;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDTO {
    private Long idRate;
    private Double price;
    private Double priceForPause;
    private LocalDate date;

    public RateDTO(Double price, Double priceForPause, LocalDate date) {
        this.price = price;
        this.priceForPause = priceForPause;
        this.date = date;
    }
}
