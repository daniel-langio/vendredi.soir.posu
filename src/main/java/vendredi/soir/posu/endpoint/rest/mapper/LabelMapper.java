package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.Label;
import vendredi.soir.posu.endpoint.rest.model.LabelMinimalInfo;

@Component
public class LabelMapper {
  public vendredi.soir.posu.model.Label toDomain(LabelMinimalInfo rest) {
    return new vendredi.soir.posu.model.Label(
        null, rest.getName(), rest.getReference(), null, null);
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
