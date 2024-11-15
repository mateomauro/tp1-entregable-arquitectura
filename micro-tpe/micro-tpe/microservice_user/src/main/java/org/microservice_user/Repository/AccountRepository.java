package org.microservice_user.Repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.microservice_user.Entities.Account;

import java.time.LocalDate;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.dateHigh = :dateHigh, a.balance = :balance WHERE a.id_account = :id_account")
    void updateAccount(@Param("id_account") Long id, @Param("dateHigh") LocalDate dateHigh, @Param("balance") Double balance);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.annulled = :annulled WHERE a.id_account = :id_account")
    void updateAnnulledAccount(@Param("id_account") Long id, @Param("annulled") Boolean annulled);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.id_account = :id_account")
    void updateBalance(@Param("id_account") Long id, @Param("balance") Double balance);

    @Query("SELECT a FROM Account a ORDER BY a.id_account DESC LIMIT 1")
    Account getLastAccount();

}
