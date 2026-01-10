package vendredi.soir.posu.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.model.Transaction;
import vendredi.soir.posu.repository.TransactionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> recordTransactions(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
