package microservice_administrator;

import microservicioAdmin.AdminApplication;
import microservicioAdmin.dto.BillingDTO;
import microservicioAdmin.entities.Billing;
import microservicioAdmin.feignClients.AccountFeignClients;
import microservicioAdmin.repository.BillingRepository;
import microservicioAdmin.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest(classes = AdminApplication.class)
@Transactional
public class TEST_Admin {

  @Autowired
  private AdminService adminService;
  @Autowired
  private AccountFeignClients accountFeignClients;
  @Autowired
  private BillingRepository billingRepository;

  @Test
  public void test_annulledAccount_accountNotFound() {
    boolean annul = true;
    /*Exception exception = Assert.assertThrows(Exception.class, () -> {
      accountFeignClients.annulledAccount(-1L, annul);
    });*/
    Assert.assertThrows(Exception.class, () -> {
      accountFeignClients.annulledAccount(-1L, annul);
    });
    //Assert.assertEquals("El test funciona", exception.getMessage());
  }

  @Test
  public void testSaveBilling_idBillingExists() throws Exception {
    try {
      //creo billing con id que ya existe
      Billing billing = new Billing(1L, LocalDate.now(), 100.0);
      billingRepository.save(billing);
      BillingDTO result = adminService.saveBilling(billing);
      Assert.assertNull(result);
    } catch (Exception e) {
      Assert.fail("Funciona el test porque es null o id ya existe: " + e.getMessage());
    }
  }
}