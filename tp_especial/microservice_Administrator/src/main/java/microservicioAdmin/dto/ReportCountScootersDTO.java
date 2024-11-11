package microservicioAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCountScootersDTO {
    private int countActivs;
    private int countMaintenance;
}
