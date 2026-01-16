package vendredi.soir.posu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);

  Optional<User> findByApiKey(String apiKey);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
