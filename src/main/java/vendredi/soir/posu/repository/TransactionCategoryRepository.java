package vendredi.soir.posu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.TransactionCategory;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, String> {
  Optional<TransactionCategory> findByReference(String reference);

  boolean existsByReference(String reference);
}
