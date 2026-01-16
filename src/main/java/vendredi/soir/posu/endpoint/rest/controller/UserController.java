package vendredi.soir.posu.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vendredi.soir.posu.endpoint.rest.mapper.UserMapper;
import vendredi.soir.posu.endpoint.rest.model.LoginRequest;
import vendredi.soir.posu.endpoint.rest.model.RegisterRequest;
import vendredi.soir.posu.endpoint.rest.model.UserWithApiKey;
import vendredi.soir.posu.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping("/register")
  public UserWithApiKey register(@RequestBody RegisterRequest request) {
    return userMapper.toRestWithApiKey(userService.register(request));
  }

  @PostMapping("/login")
  public UserWithApiKey login(@RequestBody LoginRequest request) {
    return userMapper.toRestWithApiKey(userService.login(request));
  }
}
