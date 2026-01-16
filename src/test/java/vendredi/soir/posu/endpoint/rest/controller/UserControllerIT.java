package vendredi.soir.posu.endpoint.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vendredi.soir.posu.conf.FacadeIT;
import vendredi.soir.posu.endpoint.rest.model.RegisterRequest;
import vendredi.soir.posu.endpoint.rest.model.UserWithApiKey;

@Disabled("TODO: config database first")
public class UserControllerIT extends FacadeIT {
  @Autowired UserController userController;

  @Test
  void can_register() {
    String username = "testuser_" + java.util.UUID.randomUUID();
    RegisterRequest request = RegisterRequest.builder()
        .username(username)
        .email(username + "@example.com")
        .password("password")
        .build();
    UserWithApiKey response = userController.register(request);
    assertNotNull(response.getId());
    assertEquals(username, response.getUsername());
    assertEquals(username + "@example.com", response.getEmail());
    assertNotNull(response.getApiKey());
  }
}
