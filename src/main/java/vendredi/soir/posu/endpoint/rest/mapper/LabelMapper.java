package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.Label;
import vendredi.soir.posu.endpoint.rest.model.LabelMinimalInfo;

import static java.util.UUID.randomUUID;

@Component
public class LabelMapper {
  public vendredi.soir.posu.model.Label toDomain(LabelMinimalInfo rest) {
    return new vendredi.soir.posu.model.Label(
        null, rest.getName(), rest.getReference() + "-" + randomUUID(), null, null);
  }

  public Label toRest(vendredi.soir.posu.model.Label domain) {
    return new Label(
        domain.getId(),
        domain.getName(),
        domain.getReference(),
        domain.getCreatedAt(),
        domain.getUpdatedAt());
  }
}
