package org.example.microservicemaintenance;

import org.example.microservicemaintenance.DTOs.MaintenanceDTO;
import org.example.microservicemaintenance.Services.MaintenanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class MicroServiceMaintenanceApplicationTests {

    @Autowired
    private MaintenanceService maintenanceService;

    @Test
    void testExistMaintenance() throws Exception {
        MaintenanceDTO maintenanceDTO = maintenanceService.getOne(-1);
        Assert.isNull(maintenanceDTO, "Este id no existe");
    }

    @Test
    void testMaintenanceInRepair() throws Exception {
        List<MaintenanceDTO> maintenanceDTOS = maintenanceService.checkMaintenance();
        Boolean existRepairs = !maintenanceDTOS.isEmpty();
        if(existRepairs) {
            Assert.isTrue(existRepairs, "Hay monopatines en matenimiento");
        }
        else {
            fail("No hay monopatines en mantenimiento");
        }
    }
}
