package microservice_administrator;

import microservicioAdmin.AdminApplication;
import microservicioAdmin.dto.BillingDTO;
import microservicioAdmin.feignClients.AccountFeignClients;
import microservicioAdmin.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = AdminApplication.class)
@Transactional
public class TEST_Admin {

  @Autowired
  private AdminService adminService;
  @Autowired
  private AccountFeignClients accountFeignClients;

  @Test
  public void test_calculateRateOfTrip_tripNull() {
    Double kmTraveled = 10.0;
    try {
      BillingDTO result = adminService.calculateRateOfTrip(null, kmTraveled);
      Assert.assertNull(result);
    } catch (Exception e) {
      Assert.fail("Fallo el test: " + e.getMessage());
    }
  }

  @Test
  public void test_annulledAccount_accountNotFound() {
    boolean annul = true;

    Exception exception = Assert.assertThrows(Exception.class, () -> {
      accountFeignClients.annulledAccount(-1L, annul);
    });

    Assert.assertEquals("La cuenta no existe", exception.getMessage());
  }
}