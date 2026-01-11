package vendredi.soir.posu.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WalletMinimalInfo {
  private String name;
  private String reference;
  private WalletType type;
}
