package org.example.microservice_user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.microservice_user.Dtos.UserResponseDTO;
import org.microservice_user.MicroserviceUserAplication;
import org.microservice_user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = MicroserviceUserAplication.class)
@Transactional
public class TEST_User {
  @Autowired
  private UserService userService;

  @Test
  public void TEST_DeleteUserNotExists() throws Exception {
    UserResponseDTO test = userService.deleteById(-10L);
    Assertions.assertNull(test);
  }
}
