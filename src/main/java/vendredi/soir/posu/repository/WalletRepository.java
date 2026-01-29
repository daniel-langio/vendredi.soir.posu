package vendredi.soir.posu.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
  Optional<Wallet> findByReferenceAndUsersContaining(String reference, User user);

  boolean existsByReferenceAndUsersContaining(String reference, User user);

  List<Wallet> findByUsersContaining(User user);
}
