package microservicioAdmin.feignClients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScooterDTO implements Serializable {
    private long id_scooter;
    private Double latitude;
    private Double length;
    private String QR_Code;
    private boolean available;
    private boolean in_maintenance;
    private Double usage_time;
    private Double km_traveled;

    @Override
    public String toString() {
        return "Scooter{" +
                "latitude=" + latitude +
                ", length=" + length +
                ", QR_Code='" + QR_Code + '\'' +
                ", available=" + available +
                ", usage_time=" + usage_time +
                ", km_traveled=" + km_traveled +
                ", id_scooter=" + id_scooter +
                '}';
    }
}
