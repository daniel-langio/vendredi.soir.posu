package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.User;
import vendredi.soir.posu.endpoint.rest.model.UserWithApiKey;

@Component
public class UserMapper {
  public User toRest(vendredi.soir.posu.model.User domain) {
    return User.builder()
        .id(domain.getId())
        .username(domain.getUsername())
        .email(domain.getEmail())
        .creationDatetime(domain.getCreationDatetime())
        .build();
  }

  public UserWithApiKey toRestWithApiKey(vendredi.soir.posu.model.User domain) {
    return UserWithApiKey.builder()
        .id(domain.getId())
        .username(domain.getUsername())
        .email(domain.getEmail())
        .apiKey(domain.getApiKey())
        .creationDatetime(domain.getCreationDatetime())
        .build();
  }
}
