package microservicioAdmin.utils;

import jakarta.annotation.PostConstruct;
import microservicioAdmin.entities.Rate;
import microservicioAdmin.repository.AdminRepository;
import org.springframework.stereotype.Component;

@Component
public class LoadDataBase {
    public final AdminRepository adminRepository;

    public LoadDataBase(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    //------------- esto iria aca?
    @PostConstruct
    public void loadData() {
        Rate rate = new Rate();
        //rate.setRate(26.1);
        //rate.setRateForPause(7.1);
        adminRepository.save(rate);
    }
}