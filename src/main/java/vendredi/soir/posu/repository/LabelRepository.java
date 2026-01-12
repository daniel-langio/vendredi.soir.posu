package vendredi.soir.posu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {
  Optional<Label> findByReference(String reference);

  boolean existsByReference(String reference);
}
