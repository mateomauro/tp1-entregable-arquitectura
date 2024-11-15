package org.example.microservice_trip.feignClient;

import org.example.microservice_trip.feignClient.model.AccountResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "micro-user")
public interface AccountFeignClient {

    @PutMapping("/api/accounts/discountBalance/account/{id_account}/balance/{balance}")
    void discountBalance(@PathVariable Long id_account, @PathVariable Double balance);
}
