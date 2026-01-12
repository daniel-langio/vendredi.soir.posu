package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.TransactionMinimalInfo;
import vendredi.soir.posu.model.Transaction;

@Component
public class TransactionMapper {
  public Transaction toDomain(TransactionMinimalInfo rest) {
    return new Transaction(
        null,
        rest.getDate(),
        toDomain(rest.getTransactionType()),
        rest.getWalletReference(),
        rest.getAmount(),
        rest.getCategoryReference(),
        rest.getSubCategoryReference(),
        rest.getDescription(),
        null,
        null);
  }

  public vendredi.soir.posu.endpoint.rest.model.Transaction toRest(Transaction domain) {
    return new vendredi.soir.posu.endpoint.rest.model.Transaction(
        domain.getId(),
        domain.getDate(),
        toRest(domain.getTransactionType()),
        domain.getWalletReference(),
        domain.getAmount(),
        domain.getCategoryReference(),
        domain.getSubCategoryReference(),
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
