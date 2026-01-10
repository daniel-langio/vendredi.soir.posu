package vendredi.soir.posu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendredi.soir.posu.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {}
