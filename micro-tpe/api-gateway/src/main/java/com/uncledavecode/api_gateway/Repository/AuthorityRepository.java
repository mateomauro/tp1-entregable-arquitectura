package com.uncledavecode.api_gateway.Repository;

import com.uncledavecode.api_gateway.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
