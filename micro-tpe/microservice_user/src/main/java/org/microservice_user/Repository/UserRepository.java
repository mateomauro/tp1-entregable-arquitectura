package org.microservice_user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.microservice_user.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.lastName = :lastName, u.email = :email, u.phone_number = :phoneNumber, u.role = :role, u.latitude = :latitude, u.longitude = :longitude WHERE u.id_user = :id_user")
    void updateUser(@Param("id_user") Long id, @Param("name") String name, @Param("lastName") String lastName, @Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("role") String role, @Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
