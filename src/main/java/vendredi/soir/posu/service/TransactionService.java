package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionMinimalInfo;
import vendredi.soir.posu.model.Transaction;
import vendredi.soir.posu.repository.TransactionRepository;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  public List<Transaction> recordTransactions(List<TransactionMinimalInfo> transactions) {
    List<Transaction> toSave =
        transactions.stream().map(transactionMapper::toDomain).collect(Collectors.toList());
    return transactionRepository.saveAll(toSave);
  }

  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }
}
