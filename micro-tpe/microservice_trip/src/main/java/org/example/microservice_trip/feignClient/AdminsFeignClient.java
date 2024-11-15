package org.example.microservice_trip.feignClient;

import org.example.microservice_trip.feignClient.model.BillingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-admin")
public interface AdminsFeignClient {

  @GetMapping("/api/admins/calculateCost/{id_trip}/{km_traveled}")
  BillingDTO calculateCost(@PathVariable Long id_trip, @PathVariable Double km_traveled);
}
