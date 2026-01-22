package vendredi.soir.posu.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.Label;
import vendredi.soir.posu.model.User;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {
  Optional<Label> findByReferenceAndUser(String reference, User user);

  boolean existsByReferenceAndUser(String reference, User user);

  List<Label> findByUser(User user);
}
