package vendredi.soir.posu.endpoint.rest.mapper;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.TransactionMinimalInfo;
import vendredi.soir.posu.model.Transaction;
import vendredi.soir.posu.repository.LabelRepository;

import static java.util.UUID.randomUUID;

@Component
@AllArgsConstructor
public class TransactionMapper {
  private final LabelRepository labelRepository;
  private final LabelMapper labelMapper;

  public Transaction toDomain(TransactionMinimalInfo rest) {
    return new Transaction(
        null,
        rest.getDate(),
        toDomain(rest.getTransactionType()),
        rest.getWalletReference() + "-" + randomUUID(),
        rest.getAmount(),
        rest.getLabels().stream()
            .map(
                label ->
                    labelRepository
                        .findByReference(label)
                        .orElseThrow(
                            () -> new IllegalArgumentException("Label not found: " + label)))
            .collect(Collectors.toList()),
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
        domain.getLabels().stream().map(labelMapper::toRest).collect(Collectors.toList()),
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
