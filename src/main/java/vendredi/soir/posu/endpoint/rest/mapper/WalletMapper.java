package vendredi.soir.posu.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import vendredi.soir.posu.endpoint.rest.model.WalletMinimalInfo;
import vendredi.soir.posu.model.Wallet;

@Component
public class WalletMapper {

  public Wallet toDomain(WalletMinimalInfo rest) {
    return new Wallet(null, rest.getName(), rest.getReference(), toDomain(rest.getType()), null);
  }

  public vendredi.soir.posu.endpoint.rest.model.Wallet toRest(Wallet domain) {
    return new vendredi.soir.posu.endpoint.rest.model.Wallet(
        domain.getId(),
        domain.getName(),
        domain.getReference(),
        toRest(domain.getType()),
        domain.getCreatedAt());
  }

  private vendredi.soir.posu.model.WalletType toDomain(
      vendredi.soir.posu.endpoint.rest.model.WalletType type) {
    return vendredi.soir.posu.model.WalletType.valueOf(type.name());
  }

  private vendredi.soir.posu.endpoint.rest.model.WalletType toRest(
      vendredi.soir.posu.model.WalletType type) {
    return vendredi.soir.posu.endpoint.rest.model.WalletType.valueOf(type.name());
  }
}
