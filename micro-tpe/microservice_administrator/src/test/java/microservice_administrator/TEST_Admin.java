package microservice_administrator;

import microservicioAdmin.feignClients.model.TripDTO;
import microservicioAdmin.services.AdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AdminService.class)
public class TEST_Admin {

  @Autowired
  private AdminService adminService;

  @Test
  public void TEST_calculateCost(){
    TripDTO tripDTO = new TripDTO();

  }
}
