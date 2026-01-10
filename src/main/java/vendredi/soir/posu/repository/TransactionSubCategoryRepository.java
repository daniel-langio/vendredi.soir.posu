package vendredi.soir.posu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.TransactionSubCategory;

import java.util.Optional;

@Repository
public interface TransactionSubCategoryRepository extends JpaRepository<TransactionSubCategory, String> {
    Optional<TransactionSubCategory> findByReference(String reference);
    boolean existsByReference(String reference);
}
