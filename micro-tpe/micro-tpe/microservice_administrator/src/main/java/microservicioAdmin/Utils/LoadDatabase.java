package microservicioAdmin.Utils;

import microservicioAdmin.dto.RateDTO;
import microservicioAdmin.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoadDatabase {
    @Autowired
    private AdminService adminService;
    // dar de alta Rate
    public void loadAdminRate() throws Exception {
        RateDTO rate1 = new RateDTO(1000.0, 2500.0, LocalDate.now());
        try {
            if(adminService.findAll().isEmpty())
                adminService.saveRate(rate1);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
