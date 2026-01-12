package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.TransactionSubCategoryMinimalInfo;
import vendredi.soir.posu.model.TransactionCategory;
import vendredi.soir.posu.model.TransactionSubCategory;

@Component
public class TransactionSubCategoryMapper {

  public TransactionSubCategory toDomain(
      TransactionSubCategoryMinimalInfo rest, TransactionCategory category) {
    return new TransactionSubCategory(
        null, category, rest.getName(), rest.getReference(), rest.getDescription(), null, null);
  }

  public vendredi.soir.posu.endpoint.rest.model.TransactionSubCategory toRest(
      TransactionSubCategory domain) {
    return new vendredi.soir.posu.endpoint.rest.model.TransactionSubCategory(
        domain.getId(),
        domain.getCategoryReference(),
        domain.getName(),
        domain.getReference(),
        domain.getDescription(),
        domain.getCreatedAt(),
        domain.getUpdatedAt());
  }
}
