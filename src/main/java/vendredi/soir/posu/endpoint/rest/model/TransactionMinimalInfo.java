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
public class TransactionMinimalInfo {
  private Instant date;
  private TransactionType transactionType;
  private String walletReference;
  private double amount;
  private java.util.List<String> labels;
  private String description;
}
