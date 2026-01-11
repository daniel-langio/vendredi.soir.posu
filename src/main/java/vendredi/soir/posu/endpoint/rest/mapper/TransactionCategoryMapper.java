package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.TransactionCategoryMinimalInfo;
import vendredi.soir.posu.model.TransactionCategory;

@Component
public class TransactionCategoryMapper {

  public TransactionCategory toDomain(TransactionCategoryMinimalInfo rest) {
    return new TransactionCategory(
        null,
        toDomain(rest.getTransactionType()),
        rest.getName(),
        rest.getReference(),
        rest.getDescription(),
        null,
        null);
  }

  public vendredi.soir.posu.endpoint.rest.model.TransactionCategory toRest(
      TransactionCategory domain) {
    return new vendredi.soir.posu.endpoint.rest.model.TransactionCategory(
        domain.getId(),
        toRest(domain.getTransactionType()),
        domain.getName(),
        domain.getReference(),
        domain.getDescription(),
        domain.getCreatedAt(),
        domain.getUpdatedAt());
  }

  private vendredi.soir.posu.model.TransactionType toDomain(
      vendredi.soir.posu.endpoint.rest.model.TransactionType type) {
    return vendredi.soir.posu.model.TransactionType.valueOf(type.name());
  }

  private vendredi.soir.posu.endpoint.rest.model.TransactionType toRest(
      vendredi.soir.posu.model.TransactionType type) {
    return vendredi.soir.posu.endpoint.rest.model.TransactionType.valueOf(type.name());
  }
}
