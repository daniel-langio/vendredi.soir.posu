package vendredi.soir.posu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
  Optional<Wallet> findByReference(String reference);

  boolean existsByReference(String reference);
}
