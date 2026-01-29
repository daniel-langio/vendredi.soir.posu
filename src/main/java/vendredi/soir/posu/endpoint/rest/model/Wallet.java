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
public class Wallet {
  private String id;
  private String name;
  private String reference;
  private WalletType type;
  private Instant createdAt;
}
