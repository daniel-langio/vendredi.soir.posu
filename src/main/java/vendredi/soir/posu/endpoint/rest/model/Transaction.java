package vendredi.soir.posu.endpoint.rest.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction {
  private String id;
  private Instant date;
  private TransactionType transactionType;
  private String walletReference;
  private double amount;
  private java.util.List<Label> labels;
  private String description;
  private Instant createdAt;
  private Instant updatedAt;
}
