package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "micro-user")
public interface AccountFeignClients {

    @PutMapping("/api/accounts/annulledAccount/{id_account}/{annulled}")
    AccountDTO annulledAccount(@PathVariable Long id_account, @PathVariable Boolean annulled);
}
