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
public class TransactionSubCategory {
  private String id;
  private String categoryReference;
  private String name;
  private String reference;
  private String description;
  private Instant createdAt;
  private Instant updatedAt;
}
