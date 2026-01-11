package vendredi.soir.posu.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionCategoryMinimalInfo {
  private TransactionType transactionType;
  private String name;
  private String reference;
  private String description;
}
